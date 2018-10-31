package com.rk.dto;

import com.rk.entity.SuccessKilled;
import com.rk.enums.SecKillStatEnum;

/**
 * 秒杀返回结果
 */
public class SecKillExecution {

    private long seckillId;//秒杀商品ID

    private int state;//秒杀执行结果状态

    private String stateInfo;//状态描述

    private SuccessKilled successKilled;//秒杀成功对象

    public SecKillExecution(long seckillId, SecKillStatEnum secKillStatEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = secKillStatEnum.getState();
        this.stateInfo = secKillStatEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SecKillExecution(long seckillId, SecKillStatEnum secKillStatEnum) {
        this.seckillId = seckillId;
        this.state = secKillStatEnum.getState();
        this.stateInfo = secKillStatEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SecKillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
