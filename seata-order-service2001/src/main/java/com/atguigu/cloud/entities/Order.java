package com.atguigu.cloud.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * t_order
 */
public class Order implements Serializable {
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 金额
     */
    private Long money;

    /**
     * 订单状态: 0:创建中; 1:已完结
     */
    private Integer status;

    public Order() {
    }

    public Order(Long id, Long userId, Long productId, Integer count, Long money, Integer status) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.count = count;
        this.money = money;
        this.status = status;
    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * @return productId
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置
     * @param productId
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取
     * @return money
     */
    public Long getMoney() {
        return money;
    }

    /**
     * 设置
     * @param money
     */
    public void setMoney(Long money) {
        this.money = money;
    }

    /**
     * 获取
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String toString() {
        return "Order{id = " + id + ", userId = " + userId + ", productId = " + productId + ", count = " + count + ", money = " + money + ", status = " + status + "}";
    }

    private static final long serialVersionUID = 1L;

}