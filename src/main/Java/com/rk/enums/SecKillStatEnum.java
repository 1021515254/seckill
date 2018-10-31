package com.rk.enums;

/**
 * 使用枚举代表常量数据字段(数据字典)
 */
public enum SecKillStatEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据纂改");

    private int state;

    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    SecKillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static SecKillStatEnum stateOf(int index) {
        for (SecKillStatEnum secKillStatEnum : values()) {
            if (secKillStatEnum.getState() == index) {
                return secKillStatEnum;
            }
        }
        return null;
    }
}
