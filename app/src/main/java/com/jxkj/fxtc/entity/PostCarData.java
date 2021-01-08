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
}
