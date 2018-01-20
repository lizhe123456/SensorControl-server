package com.zlcm.server.model.bean;

import java.io.Serializable;

public class AuthBack implements Serializable{

    /**
     * reason : 成功
     * result : {"begin":"20130415","department":"剩州市公安局荆州分局","end":"20230415","side":"back","orderid":"2018011918061676933"}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * begin : 20130415
         * department : 剩州市公安局荆州分局
         * end : 20230415
         * side : back
         * orderid : 2018011918061676933
         */

        private String begin;
        private String department;
        private String end;
        private String side;
        private String orderid;

        public String getBegin() {
            return begin;
        }

        public void setBegin(String begin) {
            this.begin = begin;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }
    }

}
