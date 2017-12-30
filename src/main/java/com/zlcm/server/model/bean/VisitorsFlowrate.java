package com.zlcm.server.model.bean;

import java.io.Serializable;

public class VisitorsFlowrate implements Serializable {
    private Integer did;

    private Integer visitorsFlowrate;


    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getVisitorsFlowrate() {
        return visitorsFlowrate;
    }

    public void setVisitorsFlowrate(Integer visitorsFlowrate) {
        this.visitorsFlowrate = visitorsFlowrate;
    }
}