package com.jxkj.fxtc.entity;

import com.jxkj.fxtc.conpoment.utils.StringUtil;

import java.util.List;

public class CouponCanListUser {

    /**
     * list : [{"id":37,"userId":89,"couponId":4,"couponName":"测试数据","remark":"啊啊啊啊","reliefAmount":1,"limitAmount":10,"beginDate":"2020-08-31 10:35:55","expireDate":"2020-09-07 10:35:55","status":2,"delTf":false,"createTime":"2020-08-29 08:46:41","provinceId":null,"cityId":null,"districtId":null,"region":null}]
     * totalCount : 1
     */

    private String totalCount;
    private List<ListBean> list;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
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
         * id : 37
         * userId : 89
         * couponId : 4
         * couponName : 测试数据
         * remark : 啊啊啊啊
         * reliefAmount : 1
         * limitAmount : 10
         * beginDate : 2020-08-31 10:35:55
         * expireDate : 2020-09-07 10:35:55
         * status : 2
         * delTf : false
         * createTime : 2020-08-29 08:46:41
         * provinceId : null
         * cityId : null
         * districtId : null
         * region : null
         */

        private String id;
        private String userId;
        private String couponId;
        private String couponName;
        private String remark;
        private String reliefAmount;
        private String limitAmount;
        private String beginDate;
        private String expireDate;
        private String status;
        private boolean delTf;
        private String createTime;
        private String provinceId;
        private String cityId;
        private String districtId;
        private String region;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getReliefAmount() {
            return StringUtil.trimZero(reliefAmount);
        }

        public void setReliefAmount(String reliefAmount) {
            this.reliefAmount = reliefAmount;
        }

        public String getLimitAmount() {
            return StringUtil.trimZero(limitAmount);
        }

        public void setLimitAmount(String limitAmount) {
            this.limitAmount = limitAmount;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isDelTf() {
            return delTf;
        }

        public void setDelTf(boolean delTf) {
            this.delTf = delTf;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }
}
