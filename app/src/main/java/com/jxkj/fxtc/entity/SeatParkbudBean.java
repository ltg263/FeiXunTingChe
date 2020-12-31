package com.jxkj.fxtc.entity;

public class SeatParkbudBean {

    /**
     * creatTime : 2020-12-24 16:28:24
     * delTF : 0
     * floor : 2
     * id : 2
     * license : 浙·T7797
     * lotID : 1
     * parkingLot : {"appoStringPrice":3,"creatTime":"2020-12-16 16:51:32","delTF":0,"endTime":"2020-12-16 16:51:45","freeTime":0,"id":1,"isRecommend":0,"lotAddressId":1,"parkingName":"学府一号","parkingPrice":20,"slotPrice":10,"slotTime":0,"startTime":"2020-12-16 16:51:42","status":1,"updateTime":"2020-12-16 16:51:35"}
     * seatName : D2
     * startTime : 2020-12-25 17:16:48
     * status : 1
     * updateTime : 2020-12-25 17:10:43
     * userID : 12
     */

    private String creatTime;
    private String delTF;
    private String floor;
    private String id;
    private String license;
    private String lotID;
    private ParkingLotBean parkingLot;
    private String seatName;
    private String startTime;
    private String status;
    private String updateTime;
    private String userID;

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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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

    public ParkingLotBean getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLotBean parkingLot) {
        this.parkingLot = parkingLot;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public static class ParkingLotBean {
        /**
         * appoStringPrice : 3
         * creatTime : 2020-12-16 16:51:32
         * delTF : 0
         * endTime : 2020-12-16 16:51:45
         * freeTime : 0
         * id : 1
         * isRecommend : 0
         * lotAddressId : 1
         * parkingName : 学府一号
         * parkingPrice : 20
         * slotPrice : 10
         * slotTime : 0
         * startTime : 2020-12-16 16:51:42
         * status : 1
         * updateTime : 2020-12-16 16:51:35
         */

        private String appoStringPrice;
        private String creatTime;
        private String delTF;
        private String endTime;
        private String freeTime;
        private String id;
        private String isRecommend;
        private String lotAddressId;
        private String parkingName;
        private String parkingPrice;
        private String slotPrice;
        private String slotTime;
        private String startTime;
        private String status;
        private String updateTime;

        public String getAppoStringPrice() {
            return appoStringPrice;
        }

        public void setAppoStringPrice(String appoStringPrice) {
            this.appoStringPrice = appoStringPrice;
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

        public String getFreeTime() {
            return freeTime;
        }

        public void setFreeTime(String freeTime) {
            this.freeTime = freeTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getLotAddressId() {
            return lotAddressId;
        }

        public void setLotAddressId(String lotAddressId) {
            this.lotAddressId = lotAddressId;
        }

        public String getParkingName() {
            return parkingName;
        }

        public void setParkingName(String parkingName) {
            this.parkingName = parkingName;
        }

        public String getParkingPrice() {
            return parkingPrice;
        }

        public void setParkingPrice(String parkingPrice) {
            this.parkingPrice = parkingPrice;
        }

        public String getSlotPrice() {
            return slotPrice;
        }

        public void setSlotPrice(String slotPrice) {
            this.slotPrice = slotPrice;
        }

        public String getSlotTime() {
            return slotTime;
        }

        public void setSlotTime(String slotTime) {
            this.slotTime = slotTime;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
