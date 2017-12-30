package com.zlcm.server.model.bean;

import com.zlcm.server.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Log implements Serializable {

    private Integer lid;

    private String type;

    private String title;

    private String remoteaddr;

    private String requesturi;

    private String params;

    private String exception;

    private String method;

    private String ip;

    private Date operatedate;

    private String timeout;			//结束时间

    private Integer uid;


    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRemoteaddr() {
        return remoteaddr;
    }

    public void setRemoteaddr(String remoteaddr) {
        this.remoteaddr = remoteaddr == null ? null : remoteaddr.trim();
    }

    public String getRequesturi() {
        return requesturi;
    }

    public void setRequesturi(String requesturi) {
        this.requesturi = requesturi == null ? null : requesturi.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getIp() {
        return StringUtils.isBlank(ip) ? ip : ip.trim();
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getOperatedate() {
        return operatedate;
    }

    public void setOperatedate(Date operatedate) {
        this.operatedate = operatedate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 设置请求参数
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {
        if (paramMap == null){
            return;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : ((Map<String, String[]>)paramMap).entrySet()){
            params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
        }
        this.params = params.toString();
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "Log{" +
                "lid=" + lid +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", remoteaddr='" + remoteaddr + '\'' +
                ", requesturi='" + requesturi + '\'' +
                ", params='" + params + '\'' +
                ", exception='" + exception + '\'' +
                ", method='" + method + '\'' +
                ", ip='" + ip + '\'' +
                ", operatedate=" + operatedate +
                ", timeout='" + timeout + '\'' +
                ", uid=" + uid +
                '}';
    }
}