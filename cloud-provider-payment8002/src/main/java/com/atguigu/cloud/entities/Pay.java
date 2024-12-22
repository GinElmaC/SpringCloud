package com.atguigu.cloud.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Pay {
    private Integer id;
    private String payNo;
    private String orderNo;
    private Integer userId;
    private BigDecimal amount;
    private Byte deleted;
    private Date createTime;
    private Date updateTime;

    public Pay() {
    }

    public Pay(Integer id, String payNo, String orderNo, Integer userId, BigDecimal amount, Byte deleted, Date createTime, Date updateTime) {
        this.id = id;
        this.payNo = payNo;
        this.orderNo = orderNo;
        this.userId = userId;
        this.amount = amount;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return payNo
     */
    public String getPayNo() {
        return payNo;
    }

    /**
     * 设置
     * @param payNo
     */
    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    /**
     * 获取
     * @return orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取
     * @return deleted
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置
     * @param deleted
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String toString() {
        return "Pay{id = " + id + ", payNo = " + payNo + ", orderNo = " + orderNo + ", userId = " + userId + ", amount = " + amount + ", deleted = " + deleted + ", createTime = " + createTime + ", updateTime = " + updateTime + "}";
    }
}
