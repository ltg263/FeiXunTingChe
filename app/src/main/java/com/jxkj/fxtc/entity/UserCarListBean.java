package com.jxkj.fxtc.entity;

import java.util.List;

public class UserCarListBean {

    /**
     * count : 4
     * list : [{"creatime":"2020-12-24 11:20:54","defaultCar":0,"delTF":0,"id":4,"license":"浙·A7797","status":0,"type":1,"updateTime":"2020-12-24 11:21:01","userID":12},{"creatime":"2020-12-24 11:20:51","defaultCar":1,"delTF":0,"id":3,"license":"浙·T7797","status":1,"type":0,"updateTime":"2020-12-24 11:20:58","userID":12},{"creatime":"2020-12-24 11:20:48","defaultCar":0,"delTF":0,"id":2,"license":"浙·C7797","status":0,"type":1,"updateTime":"2020-12-24 11:20:57","userID":12},{"creatime":"2020-12-24 10:38:45","defaultCar":0,"delTF":0,"id":1,"license":"浙·B7797","status":1,"type":0,"updateTime":"2020-12-24 10:43:58","userID":12}]
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
         * creatime : 2020-12-24 11:20:54
         * defaultCar : 0
         * delTF : 0
         * id : 4
         * license : 浙·A7797
         * status : 0
         * type : 1
         * updateTime : 2020-12-24 11:21:01
         * userID : 12
         */

        private String creatime;
        private String defaultCar;
        private String delTF;
        private String id;
        private String license;
        private String status;
        private String type;
        private String updateTime;
        private String userID;

        public String getCreatime() {
            return creatime;
        }

        public void setCreatime(String creatime) {
            this.creatime = creatime;
        }

        public String getDefaultCar() {
            return defaultCar;
        }

        public void setDefaultCar(String defaultCar) {
            this.defaultCar = defaultCar;
        }

        public String getDelTF() {
            return delTF;
        }

        public void setDelTF(String delTF) {
            this.delTF = delTF;
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
    }
}
