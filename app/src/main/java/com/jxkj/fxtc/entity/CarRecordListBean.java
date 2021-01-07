package com.jxkj.fxtc.entity;

import java.util.List;

public class CarRecordListBean {

    /**
     * count : 18
     * list : [{"creatTime":"2020-12-25 17:10:43","delTF":0,"endTime":"2020-12-25 17:10:41","id":23,"license":"浙·T7797","lotID":1,"seatID":2,"startTime":"2020-12-25 16:02:55","status":1,"userID":1},{"creatTime":"2020-12-25 16:02:04","delTF":0,"endTime":"2020-12-25 16:02:03","id":22,"license":"浙·T7797","lotID":1,"seatID":2,"startTime":"2020-12-25 14:01:00","status":1,"userID":2},{"creatTime":"2020-12-25 14:00:01","delTF":0,"endTime":"2020-12-25 14:00:01","id":21,"license":"浙·T7797","lotID":1,"seatID":2,"startTime":"2020-12-25 13:32:37","status":1,"userID":0},{"creatTime":"2020-12-25 13:31:51","delTF":0,"endTime":"2020-12-25 13:31:50","id":20,"license":"浙·T7797","lotID":1,"seatID":2,"startTime":"2020-12-25 11:59:35","status":1,"userID":1},{"creatTime":"2020-12-25 11:44:05","delTF":0,"endTime":"2020-12-25 11:44:04","id":18,"license":"浙·T7797","lotID":1,"seatID":2,"startTime":"2020-12-25 11:37:48","status":1,"userID":0},{"creatTime":"2020-12-25 11:29:56","delTF":0,"endTime":"2020-12-25 11:29:56","id":16,"license":"浙·T7797","lotID":1,"seatID":2,"startTime":"2020-12-25 11:25:19","status":1,"userID":0},{"creatTime":"2020-12-25 11:25:06","delTF":0,"endTime":"2020-12-25 11:25:05","id":15,"license":"浙·T7797","lotID":1,"seatID":2,"startTime":"2020-12-25 11:17:29","status":1,"userID":0},{"creatTime":"2020-12-25 11:13:50","delTF":0,"endTime":"2020-12-25 11:13:49","id":14,"license":"浙·T7797","lotID":1,"seatID":2,"startTime":"2020-12-25 11:10:30","status":1,"userID":0},{"creatTime":"2020-12-25 11:08:47","delTF":0,"endTime":"2020-12-25 11:08:47","id":13,"license":"浙·T7797","lotID":1,"seatID":2,"startTime":"2020-12-25 11:01:37","status":1,"userID":0},{"creatTime":"2020-12-25 11:01:19","delTF":0,"endTime":"2020-12-25 11:01:18","id":12,"license":"浙·T7797","lotID":1,"seatID":2,"startTime":"2020-12-25 10:53:06","status":1,"userID":0}]
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
         * creatTime : 2020-12-25 17:10:43
         * delTF : 0
         * endTime : 2020-12-25 17:10:41
         * id : 23
         * license : 浙·T7797
         * lotID : 1
         * seatID : 2
         * startTime : 2020-12-25 16:02:55
         * status : 1
         * userID : 1
         */

        private String creatTime;
        private String delTF;
        private String endTime;
        private String id;
        private String license;
        private String lotID;
        private String seatID;
        private String startTime;
        private String status;
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

        public String getSeatID() {
            return seatID;
        }

        public void setSeatID(String seatID) {
            this.seatID = seatID;
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

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }
    }
}
