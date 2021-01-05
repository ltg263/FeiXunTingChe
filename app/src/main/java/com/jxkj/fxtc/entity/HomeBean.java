package com.jxkj.fxtc.entity;

import java.util.List;

public class HomeBean {

    /**
     * ad : [{"createTime":"2020-05-08 03:51:32","delTf":0,"id":52,"imgUrl":"https://api.nbqichen.net/fangyi/upload/744CC189B27AEE89C759D9A54CDC78DB.jpg","linkUrl":"1","queueNo":99,"status":1,"type":2}]
     * userCar : {"creatime":"2021-01-05 14:16:31","defaultCar":0,"delTF":0,"id":8,"license":"浙.B1234","status":0,"type":1,"userID":16}
     * banners : [{"createTime":"2020-06-01 07:25:10","delTf":0,"id":54,"imgUrl":"https://api.nbqichen.net/dangan/upload/D742209F066F4AEA2F32405B2A5C6FBD.png","linkUrl":"","queueNo":1,"status":1,"type":1},{"createTime":"2020-03-03 14:13:47","delTf":0,"id":50,"imgUrl":"https://api.nbqichen.net/fangyi/upload/B78850C6A5C5F13CA70C28EE47B1CF2F.png","productId":12,"productName":"便携式消毒喷雾","queueNo":2,"status":1,"type":1}]
     */

    private UserCarBean userCar;
    private List<AdBean> ad;
    private List<BannersBean> banners;

    public UserCarBean getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCarBean userCar) {
        this.userCar = userCar;
    }

    public List<AdBean> getAd() {
        return ad;
    }

    public void setAd(List<AdBean> ad) {
        this.ad = ad;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class UserCarBean {
        /**
         * creatime : 2021-01-05 14:16:31
         * defaultCar : 0
         * delTF : 0
         * id : 8
         * license : 浙.B1234
         * status : 0
         * type : 1
         * userID : 16
         */

        private String creatime;
        private String defaultCar;
        private String delTF;
        private String id;
        private String license;
        private ParkingSeatDTOBean parkingSeatDTO;
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

        public ParkingSeatDTOBean getParkingSeatDTO() {
            return parkingSeatDTO;
        }

        public void setParkingSeatDTO(ParkingSeatDTOBean parkingSeatDTO) {
            this.parkingSeatDTO = parkingSeatDTO;
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

        public static class ParkingSeatDTOBean {
            /**
             * creatTime : 2020-12-16 16:53:22
             * delTF : 0
             * floor : 2
             * id : 1
             * imageUrl : xxxx
             * license : 浙·B7797
             * lotID : 1
             * seatName : D1
             * status : 2
             * useTime : 0
             * userID : 12
             */

            private String creatTime;
            private String delTF;
            private String floor;
            private String id;
            private String imageUrl;
            private String license;
            private String lotID;
            private String seatName;
            private String status;
            private String useTime;
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

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
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

            public String getSeatName() {
                return seatName;
            }

            public void setSeatName(String seatName) {
                this.seatName = seatName;
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

            public String getUserID() {
                return userID;
            }

            public void setUserID(String userID) {
                this.userID = userID;
            }
        }
    }

    public static class AdBean {
        /**
         * createTime : 2020-05-08 03:51:32
         * delTf : 0
         * id : 52
         * imgUrl : https://api.nbqichen.net/fangyi/upload/744CC189B27AEE89C759D9A54CDC78DB.jpg
         * linkUrl : 1
         * queueNo : 99
         * status : 1
         * type : 2
         */

        private String createTime;
        private String delTf;
        private String id;
        private String imgUrl;
        private String linkUrl;
        private String queueNo;
        private String status;
        private String type;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDelTf() {
            return delTf;
        }

        public void setDelTf(String delTf) {
            this.delTf = delTf;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getQueueNo() {
            return queueNo;
        }

        public void setQueueNo(String queueNo) {
            this.queueNo = queueNo;
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
    }

    public static class BannersBean {
        /**
         * createTime : 2020-06-01 07:25:10
         * delTf : 0
         * id : 54
         * imgUrl : https://api.nbqichen.net/dangan/upload/D742209F066F4AEA2F32405B2A5C6FBD.png
         * linkUrl :
         * queueNo : 1
         * status : 1
         * type : 1
         * productId : 12
         * productName : 便携式消毒喷雾
         */

        private String createTime;
        private String delTf;
        private String id;
        private String imgUrl;
        private String linkUrl;
        private String queueNo;
        private String status;
        private String type;
        private String productId;
        private String productName;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDelTf() {
            return delTf;
        }

        public void setDelTf(String delTf) {
            this.delTf = delTf;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getQueueNo() {
            return queueNo;
        }

        public void setQueueNo(String queueNo) {
            this.queueNo = queueNo;
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

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}
