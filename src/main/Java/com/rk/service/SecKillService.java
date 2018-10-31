package com.rk.service;

import com.rk.dto.Exposer;
import com.rk.dto.SecKillExecution;
import com.rk.entity.Seckill;
import com.rk.exception.RepeatKillException;
import com.rk.exception.SecKillCloseException;
import com.rk.exception.SecKillExecption;

import java.util.List;

/**
 * 业务接口 ：站在“使用者”角度设计接口
 * 三个方面 方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface SecKillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSecKillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);


    /**
     * 秒杀开启接口 秒杀开启输出接口地址，
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSecKillUrl(long seckillId);

    /**
     *执行秒杀操作
     */
    SecKillExecution executeSecKill(long seckillId, long userPhone, String md5)
    throws SecKillExecption,SecKillCloseException,RepeatKillException;
}
