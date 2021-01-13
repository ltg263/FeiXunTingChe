package com.jxkj.fxtc.entity;

public class SeatParkbudBean {

    /**
     * creatTime : 2021-01-07 12:07:24
     * delTF : 0
     * floor : 1
     * id : 7
     * license : 气_13345
     * lotID : 3
     * parkingLot : {"address":"宁波海曙","appoStringPrice":3,"creatTime":"2020-12-31 10:32:30","delTF":0,"description":"每小时车费20元，不满30分钟以30分钟计，不满一小时以一小时计。","endTime":"2020-12-31T02:24:02.000Z","freeTime":1,"id":3,"lat":"29.756425","lng":"121.500975","parkingName":"银泰","parkingPrice":10,"regionID":330204,"slotPrice":10,"slotTime":3,"startTime":"2020-12-31T02:24:00.000Z","status":1,"updateTime":"2021-01-05 14:24:19"}
     * seatName : D1
     * startTime : 2021-01-08 17:44:01
     * status : 1
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

    public static class ParkingLotBean {
        /**
         * address : 宁波海曙
         * appoStringPrice : 3
         * creatTime : 2020-12-31 10:32:30
         * delTF : 0
         * description : 每小时车费20元，不满30分钟以30分钟计，不满一小时以一小时计。
         * endTime : 2020-12-31T02:24:02.000Z
         * freeTime : 1
         * id : 3
         * lat : 29.756425
         * lng : 121.500975
         * parkingName : 银泰
         * parkingPrice : 10
         * regionID : 330204
         * slotPrice : 10
         * slotTime : 3
         * startTime : 2020-12-31T02:24:00.000Z
         * status : 1
         * updateTime : 2021-01-05 14:24:19
         */

        private String address;
        private String appoStringPrice;
        private String creatTime;
        private String delTF;
        private String description;
        private String endTime;
        private String freeTime;
        private String id;
        private String lat;
        private String lng;
        private String parkingName;
        private String parkingPrice;
        private String regionID;
        private String slotPrice;
        private String slotTime;
        private String startTime;
        private String status;
        private String updateTime;

        private String mapCode;
        private String poiName;

        public void setMapCode(String mapCode) {
            this.mapCode = mapCode;
        }

        public void setPoiName(String poiName) {
            this.poiName = poiName;
        }

        public String getMapCode() {
            return mapCode;
        }

        public String getPoiName() {
            return poiName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
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

        public String getRegionID() {
            return regionID;
        }

        public void setRegionID(String regionID) {
            this.regionID = regionID;
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
