package com.rk.Dao;

import com.rk.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledMapper {

    /**
     * 插入购买明细，可过滤重复秒杀
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId , @Param("userPhone") long userPhone);


    /**
     * 根据id查询SuccessKilled并携带秒杀商品实体
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId , @Param("userPhone") long userPhone);
}