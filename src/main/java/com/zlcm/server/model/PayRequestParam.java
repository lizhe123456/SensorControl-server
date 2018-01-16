package com.zlcm.server.model;

import java.math.BigDecimal;

public class PayRequestParam {

    /**
     * 通知回调跳转页面
     */
    private String retUrl;

    /**
     * 订单ID
     */
    private Long orderID;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 支付编号
     */
    private String payCode;

    /**
     * 支付平台
     */
    private String onLineStyle;

    /**
     * 支付入口
     */
    private String browseType;

    /**
     * 支付金额
     */
    private BigDecimal toPay;

    /**
     * 商家
     */
    private String sellerName;

    /**
     * 商家编号
     */
    private String sellerCode;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 有效时间
     */
    private Long invalidTime;

    /**
     * 请求业务来源
     */
    private String requestBiz;

    public String getRetUrl() {
        return retUrl;
    }

    public void setRetUrl(String retUrl) {
        this.retUrl = retUrl;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getOnLineStyle() {
        return onLineStyle;
    }

    public void setOnLineStyle(String onLineStyle) {
        this.onLineStyle = onLineStyle;
    }

    public String getBrowseType() {
        return browseType;
    }

    public void setBrowseType(String browseType) {
        this.browseType = browseType;
    }

    public BigDecimal getToPay() {
        return toPay;
    }

    public void setToPay(BigDecimal toPay) {
        this.toPay = toPay;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Long invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getRequestBiz() {
        return requestBiz;
    }

    public void setRequestBiz(String requestBiz) {
        this.requestBiz = requestBiz;
    }
}
