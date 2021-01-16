package com.jxkj.fxtc.entity;

/**
 * author : LiuJie
 * date   : 2020/6/115:56
 */
public class PostCarData {

    public static class PostAddCarInfo{


        /**
         * license : 浙·B7797
         * type : 0企业1个人
         */

        private String license;
        private int type;

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class AppointmentInfo{

        /**
         * mobile : 13486697492
         * orderType : 1
         * appointmentTime : 2020-12-25 16:51:32
         * appointmentEndTime : 2020-12-26 16:51:32
         * license : 浙·C7797
         * seatID : 1
         */

        private String mobile;
        private String orderType;
        private String appointmentTime;
        private String appointmentEndTime;
        private String license;
        private String lotId;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getAppointmentTime() {
            return appointmentTime;
        }

        public void setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
        }

        public String getAppointmentEndTime() {
            return appointmentEndTime;
        }

        public void setAppointmentEndTime(String appointmentEndTime) {
            this.appointmentEndTime = appointmentEndTime;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLotId() {
            return lotId;
        }

        public void setLotId(String lotId) {
            this.lotId = lotId;
        }
    }

    public static class PayOrdersBaen{

        /**
         * orderNo : null20122513320146
         * payType : 2
         */

        private String orderNo;
        private String payType;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }
    }

    public static class InvoiceBean{

        /**
         * type : 1
         * rise : 个人
         * more : 电话12345678
         * content : 内容
         * orderId : 10
         * email : 9999@qq.com
         */

        private String type;
        private String rise;
        private String more;
        private String content;
        private String orderId;
        private String email;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRise() {
            return rise;
        }

        public void setRise(String rise) {
            this.rise = rise;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class PayAmount{

        /**
         * amount : 100
         * payType : 1
         */

        private String amount;
        private String payType;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }
    }
    public static class Feedback{

        /**
         * content : 反馈信息
         * imgUrl : 图片
         * contact : 联系方式
         */

        private String content;
        private String imgUrl;
        private String contact;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
    }

}
