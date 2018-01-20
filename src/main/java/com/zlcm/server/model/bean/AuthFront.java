package com.zlcm.server.model.bean;

import java.io.Serializable;

public class AuthFront implements Serializable{

    /**
     * reason : 成功
     * result : {"realname":"平药","sex":"男","nation":"汉","born":"19961207","address":"湖北省州市州区八岭山杨村五组","idcard":"421003199612072611","side":"front","orderid":"2018011918061469516"}
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
         * realname : 平药
         * sex : 男
         * nation : 汉
         * born : 19961207
         * address : 湖北省州市州区八岭山杨村五组
         * idcard : 421003199612072611
         * side : front
         * orderid : 2018011918061469516
         */

        private String realname;
        private String sex;
        private String nation;
        private String born;
        private String address;
        private String idcard;
        private String side;
        private String orderid;

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getBorn() {
            return born;
        }

        public void setBorn(String born) {
            this.born = born;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
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
