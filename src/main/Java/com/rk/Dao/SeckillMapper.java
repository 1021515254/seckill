package com.rk.Dao;

import com.rk.entity.Seckill;
import org.apache.ibatis.annotations.Param;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

public interface SeckillMapper {

    /**
     * 减库存
     * @param seckillId 商品id
     * @param killTime 下单时间
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     *根据ID查询秒杀商品
     * @param seckillId
     * @return
     */
    Seckill queryById(@Param("seckillId") long seckillId);

    /**
     * 根据偏移量查询秒杀商品
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offet") int offet,@Param("limit") int limit);
}