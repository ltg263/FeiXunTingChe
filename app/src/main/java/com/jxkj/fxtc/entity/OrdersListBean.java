package com.jxkj.fxtc.entity;

import java.util.List;

public class OrdersListBean {

    /**
     * count : 7
     * list : [{"address":"宁波海曙","creatTime":"2020-12-25 17:10:43","delTF":0,"endTime":"2020-12-25 17:10:41","id":16,"license":"浙·T7797","lotID":1,"orderNo":"TNO20122517100121","orderPrice":1340,"orderType":0,"seatID":2,"seatName":"D2","startTime":"2020-12-25 16:02:55","status":0,"useTime":1,"userId":12},{"address":"宁波海曙","creatTime":"2020-12-25 16:02:04","delTF":0,"endTime":"2020-12-25 16:02:03","id":15,"license":"浙·T7797","lotID":1,"orderNo":"TNO20122516020141","orderPrice":2420,"orderType":0,"seatID":2,"seatName":"D2","startTime":"2020-12-25 14:01:00","status":0,"useTime":2,"userId":12},{"address":"宁波海曙","creatTime":"2020-12-25 13:32:04","delTF":0,"endTime":"2020-12-25 13:31:50","id":14,"license":"浙·T7797","lotID":1,"orderNo":"null20122513320146","orderPrice":40,"orderType":0,"payType":2,"relaPrice":40,"seatID":2,"seatName":"D2","startTime":"2020-12-25 11:59:35","status":0,"useTime":1,"userId":12},{"address":"宁波海曙","creatTime":"2020-12-25 11:44:05","delTF":0,"endTime":"2020-12-25 11:44:04","id":13,"license":"浙·T7797","lotID":1,"orderNo":"null20122511440173","orderPrice":2,"orderType":0,"payType":3,"relaPrice":2,"seatID":2,"seatName":"D2","startTime":"2020-12-25 11:37:48","status":1,"useTime":0,"userId":12},{"address":"宁波海曙","creatTime":"2020-12-25 11:29:56","delTF":0,"endTime":"2020-12-25 11:29:56","id":12,"license":"浙·T7797","lotID":1,"orderNo":"null20122511290233","orderPrice":1.33,"orderType":0,"seatID":2,"seatName":"D2","startTime":"2020-12-25 11:25:19","status":0,"useTime":0,"userId":12},{"address":"宁波海曙","creatTime":"2020-12-25 11:25:06","delTF":0,"endTime":"2020-12-25 11:25:05","id":11,"license":"浙·T7797","lotID":1,"orderNo":"null20122511250158","orderPrice":2.33,"orderType":0,"seatID":2,"seatName":"D2","startTime":"2020-12-25 11:17:29","status":0,"useTime":0,"userId":12},{"address":"宁波海曙","appointmentPrice":3,"creatTime":"2020-12-24 15:10:10","delTF":0,"expressTime":"2020-12-31 15:15:10","id":10,"license":"浙·C7797","lotID":1,"mobile":"13486697492","orderNo":"TNO20122415100154","orderPrice":3,"orderType":1,"payType":3,"relaPrice":3,"seatID":1,"seatName":"D1","status":1,"useTime":0,"userId":12}]
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * address : 宁波海曙
         * creatTime : 2020-12-25 17:10:43
         * delTF : 0
         * endTime : 2020-12-25 17:10:41
         * id : 16
         * license : 浙·T7797
         * lotID : 1
         * orderNo : TNO20122517100121
         * orderPrice : 1340
         * orderType : 0
         * seatID : 2
         * seatName : D2
         * startTime : 2020-12-25 16:02:55
         * status : 0
         * useTime : 1
         * userId : 12
         * payType : 2
         * relaPrice : 40
         * appointmentPrice : 3
         * expressTime : 2020-12-31 15:15:10
         * mobile : 13486697492
         */

        private String address;
        private String creatTime;
        private String delTF;
        private String endTime;
        private String id;
        private String license;
        private String lotID;
        private String orderNo;
        private String orderPrice;
        private String orderType;
        private String seatID;
        private String seatName;
        private String startTime;
        private String status;
        private String useTime;
        private String userId;
        private String payType;
        private String relaPrice;
        private String appointmentPrice;
        private String expressTime;
        private String mobile;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(String creatTime) {
            this.creatTime = creatTime;
        }

        public String getDelTF() {
            return delTF;
        }

        public void setDelTF(String delTF) {
            this.delTF = delTF;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLotID() {
            return lotID;
        }

        public void setLotID(String lotID) {
            this.lotID = lotID;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(String orderPrice) {
            this.orderPrice = orderPrice;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getSeatID() {
            return seatID;
        }

        public void setSeatID(String seatID) {
            this.seatID = seatID;
        }

        public String getSeatName() {
            return seatName;
        }

        public void setSeatName(String seatName) {
            this.seatName = seatName;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUseTime() {
            return useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getRelaPrice() {
            return relaPrice;
        }

        public void setRelaPrice(String relaPrice) {
            this.relaPrice = relaPrice;
        }

        public String getAppointmentPrice() {
            return appointmentPrice;
        }

        public void setAppointmentPrice(String appointmentPrice) {
            this.appointmentPrice = appointmentPrice;
        }

        public String getExpressTime() {
            return expressTime;
        }

        public void setExpressTime(String expressTime) {
            this.expressTime = expressTime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
