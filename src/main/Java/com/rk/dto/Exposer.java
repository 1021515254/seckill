package com.rk.dto;

/**
 * 数据传输层，
 * 暴露秒杀地址DTO
 */
public class Exposer {

    private boolean exposer;//是否开始秒杀

    private String md5;//地址加密部分

    private long seckilled;//秒杀商品ID

    private long now;//当前系统时间（毫秒）

    private long start;//秒杀开始时间

    private long end;//秒杀结束时间

    public Exposer(boolean exposer,long seckilled ,long now, long start, long end) {
        this.exposer = exposer;
        this.seckilled = seckilled;
        this.now = now;
        this.start = start;
        this.end = end;
    }
    public Exposer(boolean exposer, long seckilled) {
        this.exposer = exposer;
        this.seckilled = seckilled;
    }
    public Exposer(boolean exposer, String md5, long seckilled) {
        this.exposer = exposer;
        this.md5 = md5;
        this.seckilled = seckilled;
    }

    public boolean isExposer() {
        return exposer;
    }

    public void setExposer(boolean exposer) {
        this.exposer = exposer;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckilled() {
        return seckilled;
    }

    public void setSeckilled(long seckilled) {
        this.seckilled = seckilled;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposer=" + exposer +
                ", md5='" + md5 + '\'' +
                ", seckilled=" + seckilled +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
