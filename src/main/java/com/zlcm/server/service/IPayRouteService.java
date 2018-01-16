package com.zlcm.server.service;

import com.zlcm.server.model.PayRequestParam;

import java.util.Map;

public interface IPayRouteService {


    /**
     * 组装支付请求报文（http入口）
     * @param payRequestParam
     * @return
     */
    public Map<String, Object> getPayRetMap(PayRequestParam payRequestParam);

    /**
     * 组装支付请求报文（MQ入口）
     * @param payRequestParam
     * @return
     */
    public Boolean getPayRetMap4MQ(PayRequestParam payRequestParam);

}
