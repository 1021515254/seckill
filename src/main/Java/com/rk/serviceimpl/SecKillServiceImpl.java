package com.rk.serviceimpl;

import com.rk.Dao.SeckillMapper;
import com.rk.Dao.SuccessKilledMapper;
import com.rk.dto.Exposer;
import com.rk.dto.SecKillExecution;
import com.rk.entity.Seckill;
import com.rk.entity.SuccessKilled;
import com.rk.enums.SecKillStatEnum;
import com.rk.exception.RepeatKillException;
import com.rk.exception.SecKillCloseException;
import com.rk.exception.SecKillExecption;
import com.rk.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Timer;

//@Component,当不明确组件类型的时候，使用
@Service
public class SecKillServiceImpl implements SecKillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SuccessKilledMapper successKilledMapper;

    //避免用户算出MD5加密规则，md5盐值字符串，用于混淆MD
    private final String slat = "sdnvoslk767y8&89u878$%^r7899ujsvnk&^in8521";

    public List<Seckill> getSecKillList() {
        return seckillMapper.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillMapper.queryById(seckillId);
    }

    public Exposer exportSecKillUrl(long seckillId) {
        Seckill seckill = seckillMapper.queryById(seckillId);
        //判断是否有当前秒杀商品
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTimer = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        //秒杀是否开启，或者已经关闭
        if (startTimer.getTime() > nowTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTimer.getTime(), endTime.getTime());
        }
        //转化特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    //MD5加密
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String MD5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return MD5;
    }

    @Transactional
    /**
     * 使用注解控制事务的优点
     * 1:开发团队明确标注事务方法的编程风格
     * 2:保证事务执行时间，尽可能短，不要穿插其他的网络操作。或剥离到事务方法之外
     * 3:不是所有的方法都需要事务控制
     */
    public SecKillExecution executeSecKill(long seckillId, long userPhone, String md5)
            throws SecKillExecption, SecKillCloseException, RepeatKillException {
        //判断用户秒杀到商品凭证
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SecKillExecption("seckill data rewrite");
        }
        //执行秒杀逻辑：减库存 + 购买记录行为
        Date nowTime = new Date();
        try {
            int updateCount = seckillMapper.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新到记录，秒杀结束
                throw new SecKillCloseException("seckill is close");
            } else {
                //记录购买行为
                int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
                //唯一：seckillId,userPhone
                if (insertCount <= 0) {
                    //阻止重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);
                    return new SecKillExecution(seckillId, SecKillStatEnum.SUCCESS, successKilled);
                }
            }
        } catch (SecKillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所有编译期异常转换为运行时异常
            throw new SecKillExecption("seckill inner error:" + e.getMessage());
        }
    }
}
