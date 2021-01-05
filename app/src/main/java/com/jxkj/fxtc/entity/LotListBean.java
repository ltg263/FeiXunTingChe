package com.jxkj.fxtc.entity;

import java.io.Serializable;
import java.util.List;

public class LotListBean {

    /**
     * count : 3
     * list : [{"address":"宁波海曙","appoStringPrice":3,"creatTime":"2020-12-31 10:32:30","delTF":0,"description":"每小时车费20元，不满30分钟以30分钟计，不满一小时以一小时计。","distance":"1349.97","endTime":"2020-12-31T02:24:02.000Z","freeTime":1,"id":3,"lat":"29.756425","lng":"121.500975","parkingName":"银泰","parkingPrice":10,"regionID":330204,"seatCount":1,"slotPrice":10,"slotTime":3,"startTime":"2020-12-31T02:24:00.000Z","status":1},{"address":"宁波鄞州环球银泰地下车库","appoStringPrice":1,"creatTime":"2020-12-29 15:11:38","delTF":0,"description":"每小时车费20元，不满30分钟以30分钟计，不满一小时以一小时计。","distance":"1349.97","endTime":"2020-12-26T01:30:22.000Z","freeTime":1,"id":2,"lat":"29.756425","lng":"121.500975","parkingName":"火车站","parkingPrice":1,"regionID":330204,"seatCount":1,"slotPrice":1,"slotTime":1,"startTime":"2020-12-31T01:30:18.000Z","status":1,"updateTime":"2020-12-31 16:57:00"},{"address":"宁波海曙","appoStringPrice":3.5,"creatTime":"2020-12-16 16:51:32","delTF":0,"description":"每小时车费20元，不满30分钟以30分钟计，不满一小时以一小时计。","distance":"1349.97","endTime":"2020-12-16 16:51:45","freeTime":1,"id":1,"isRecommend":0,"lat":"29.756425","lng":"121.500975","lotAddressId":1,"parkingName":"学府一号","parkingPrice":20,"regionID":330203,"seatCount":1,"slotPrice":10,"slotTime":3,"startTime":"2020-12-16 16:51:42","status":1,"updateTime":"2020-12-31 10:52:46"}]
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

    public static class ListBean implements Serializable {
        /**
         * address : 宁波海曙
         * appoStringPrice : 3
         * creatTime : 2020-12-31 10:32:30
         * delTF : 0
         * description : 每小时车费20元，不满30分钟以30分钟计，不满一小时以一小时计。
         * distance : 1349.97
         * endTime : 2020-12-31T02:24:02.000Z
         * freeTime : 1
         * id : 3
         * lat : 29.756425
         * lng : 121.500975
         * parkingName : 银泰
         * parkingPrice : 10
         * regionID : 330204
         * seatCount : 1
         * slotPrice : 10
         * slotTime : 3
         * startTime : 2020-12-31T02:24:00.000Z
         * status : 1
         * updateTime : 2020-12-31 16:57:00
         * isRecommend : 0
         * lotAddressId : 1
         */

        private String address;
        private String appoStringPrice;
        private String creatTime;
        private String delTF;
        private String description;
        private String distance;
        private String endTime;
        private String freeTime;
        private String id;
        private String lat;
        private String lng;
        private String parkingName;
        private String parkingPrice;
        private String regionID;
        private String seatCount;
        private String slotPrice;
        private String slotTime;
        private String startTime;
        private String status;
        private String updateTime;
        private String isRecommend;
        private String lotAddressId;

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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
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

        public String getSeatCount() {
            return seatCount;
        }

        public void setSeatCount(String seatCount) {
            this.seatCount = seatCount;
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
    }
}
