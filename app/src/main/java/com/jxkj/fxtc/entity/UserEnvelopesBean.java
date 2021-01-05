package com.jxkj.fxtc.entity;

import java.util.List;

public class UserEnvelopesBean {

    /**
     * list : [{"amountMoney":200,"createTime":"2020-10-23 10:48:22","effectiveTime":"2020-10-23 10:48:22","envelopsName":"满2000减200","fullReduction":2000,"id":14,"invalidTime":"2020-11-22 10:48:22","status":1,"type":1,"userId":12},{"amountMoney":10,"createTime":"2020-10-23 10:53:55","effectiveTime":"2020-10-23 10:53:55","envelopsName":"全商品通用","fullReduction":0,"id":15,"invalidTime":"2020-11-22 10:53:55","status":1,"type":1,"userId":12}]
     * totalCount : 2
     */

    private int totalCount;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * amountMoney : 200
         * createTime : 2020-10-23 10:48:22
         * effectiveTime : 2020-10-23 10:48:22
         * envelopsName : 满2000减200
         * fullReduction : 2000
         * id : 14
         * invalidTime : 2020-11-22 10:48:22
         * status : 1
         * type : 1
         * userId : 12
         */

        private String amountMoney;
        private String createTime;
        private String effectiveTime;
        private String envelopsName;
        private String fullReduction;
        private String id;
        private String invalidTime;
        private String status;
        private String type;
        private String userId;

        public String getAmountMoney() {
            return amountMoney;
        }

        public void setAmountMoney(String amountMoney) {
            this.amountMoney = amountMoney;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEffectiveTime() {
            return effectiveTime;
        }

        public void setEffectiveTime(String effectiveTime) {
            this.effectiveTime = effectiveTime;
        }

        public String getEnvelopsName() {
            return envelopsName;
        }

        public void setEnvelopsName(String envelopsName) {
            this.envelopsName = envelopsName;
        }

        public String getFullReduction() {
            return fullReduction;
        }

        public void setFullReduction(String fullReduction) {
            this.fullReduction = fullReduction;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInvalidTime() {
            return invalidTime;
        }

        public void setInvalidTime(String invalidTime) {
            this.invalidTime = invalidTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
