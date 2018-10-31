package com.rk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Seckill {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill.seckill_id
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    private Long seckillId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill.name
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill.number
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    private Integer number;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill.start_time
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill.end_time
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill.create_time
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    private Date creatTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill.seckill_id
     *
     * @return the value of seckill.seckill_id
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public Long getSeckillId() {
        return seckillId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill.seckill_id
     *
     * @param seckillId the value for seckill.seckill_id
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill.name
     *
     * @return the value of seckill.name
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill.name
     *
     * @param name the value for seckill.name
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill.number
     *
     * @return the value of seckill.number
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill.number
     *
     * @param number the value for seckill.number
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill.start_time
     *
     * @return the value of seckill.start_time
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill.start_time
     *
     * @param startTime the value for seckill.start_time
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill.end_time
     *
     * @return the value of seckill.end_time
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill.end_time
     *
     * @param endTime the value for seckill.end_time
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill.create_time
     *
     * @return the value of seckill.create_time
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill.create_time
     *
     * @param creatTime the value for seckill.create_time
     *
     * @mbg.generated Mon Aug 13 20:28:04 CST 2018
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", creatTime=" + creatTime +
                '}';
    }
}