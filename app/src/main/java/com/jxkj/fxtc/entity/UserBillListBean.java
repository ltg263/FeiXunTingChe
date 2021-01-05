package com.jxkj.fxtc.entity;

import java.util.List;

public class UserBillListBean {

    /**
     * count : 1
     * list : [{"amount":3,"billType":2,"createTime":"2020-12-30 13:34:25","delTF":0,"id":1,"license":"浙·C7797","orderDTO":{"appoStringmentPrice":3,"creatTime":"2020-12-24 15:10:10","delTF":0,"expressTime":"2020-12-31 15:15:10","id":10,"license":"浙·C7797","lotDto":{"address":"宁波海曙","appoStringPrice":3.5,"creatTime":"2020-12-16 16:51:32","delTF":0,"endTime":"2020-12-16 16:51:45","freeTime":1,"id":1,"isRecommend":0,"lat":"29.756425","list":[{"creatTime":"2020-12-24 16:28:24","delTF":0,"floor":"1","id":2,"license":"浙·T7797","lotID":1,"seatName":"D2","startTime":"2020-12-25 17:16:48","status":1,"updateTime":"2020-12-29 16:18:07","userID":12},{"appoStringmentEndTime":"2020-12-26 16:51:32","appoStringmentTime":"2020-12-25 16:51:32","creatTime":"2020-12-16 16:53:22","delTF":0,"floor":"2","id":1,"license":"浙·C7797","lotID":1,"seatName":"D1","status":2,"userID":12}],"lng":"121.500975","lotAddressId":1,"parkingName":"学府一号","parkingPrice":20,"regionID":330203,"slotPrice":10,"slotTime":3,"startTime":"2020-12-16 16:51:42","status":1,"updateTime":"2020-12-31 10:52:46"},"lotID":1,"mobile":"13486697492","orderNo":"TNO20122415100154","orderPrice":3,"orderType":1,"payType":3,"relaPrice":3,"seatID":1,"status":1,"tUserInvoice":{"amount":3,"content":"内容","creatime":"2020-12-25 13:21:31","delTF":0,"examine":2,"id":1,"more":"电话12345678","orderId":10,"rise":"个人","status":2,"type":1,"updateTime":"2020-12-30 17:06:56","userID":12},"useTime":0,"userId":12},"orderID":10,"orderno":"TNO20122415100154","payTime":"2020-12-30 13:34:29","userID":12}]
     */

    private int count;
    private List<ListBeanX> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX {
        /**
         * amount : 3
         * billType : 2
         * createTime : 2020-12-30 13:34:25
         * delTF : 0
         * id : 1
         * license : 浙·C7797
         * orderDTO : {"appoStringmentPrice":3,"creatTime":"2020-12-24 15:10:10","delTF":0,"expressTime":"2020-12-31 15:15:10","id":10,"license":"浙·C7797","lotDto":{"address":"宁波海曙","appoStringPrice":3.5,"creatTime":"2020-12-16 16:51:32","delTF":0,"endTime":"2020-12-16 16:51:45","freeTime":1,"id":1,"isRecommend":0,"lat":"29.756425","list":[{"creatTime":"2020-12-24 16:28:24","delTF":0,"floor":"1","id":2,"license":"浙·T7797","lotID":1,"seatName":"D2","startTime":"2020-12-25 17:16:48","status":1,"updateTime":"2020-12-29 16:18:07","userID":12},{"appoStringmentEndTime":"2020-12-26 16:51:32","appoStringmentTime":"2020-12-25 16:51:32","creatTime":"2020-12-16 16:53:22","delTF":0,"floor":"2","id":1,"license":"浙·C7797","lotID":1,"seatName":"D1","status":2,"userID":12}],"lng":"121.500975","lotAddressId":1,"parkingName":"学府一号","parkingPrice":20,"regionID":330203,"slotPrice":10,"slotTime":3,"startTime":"2020-12-16 16:51:42","status":1,"updateTime":"2020-12-31 10:52:46"},"lotID":1,"mobile":"13486697492","orderNo":"TNO20122415100154","orderPrice":3,"orderType":1,"payType":3,"relaPrice":3,"seatID":1,"status":1,"tUserInvoice":{"amount":3,"content":"内容","creatime":"2020-12-25 13:21:31","delTF":0,"examine":2,"id":1,"more":"电话12345678","orderId":10,"rise":"个人","status":2,"type":1,"updateTime":"2020-12-30 17:06:56","userID":12},"useTime":0,"userId":12}
         * orderID : 10
         * orderno : TNO20122415100154
         * payTime : 2020-12-30 13:34:29
         * userID : 12
         */

        private String amount;
        private String billType;
        private String createTime;
        private String delTF;
        private String id;
        private String license;
        private OrderDTOBean orderDTO;
        private String orderID;
        private String orderno;
        private String payTime;
        private String userID;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBillType() {
            return billType;
        }

        public void setBillType(String billType) {
            this.billType = billType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public OrderDTOBean getOrderDTO() {
            return orderDTO;
        }

        public void setOrderDTO(OrderDTOBean orderDTO) {
            this.orderDTO = orderDTO;
        }

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public static class OrderDTOBean {
            /**
             * appoStringmentPrice : 3
             * creatTime : 2020-12-24 15:10:10
             * delTF : 0
             * expressTime : 2020-12-31 15:15:10
             * id : 10
             * license : 浙·C7797
             * lotDto : {"address":"宁波海曙","appoStringPrice":3.5,"creatTime":"2020-12-16 16:51:32","delTF":0,"endTime":"2020-12-16 16:51:45","freeTime":1,"id":1,"isRecommend":0,"lat":"29.756425","list":[{"creatTime":"2020-12-24 16:28:24","delTF":0,"floor":"1","id":2,"license":"浙·T7797","lotID":1,"seatName":"D2","startTime":"2020-12-25 17:16:48","status":1,"updateTime":"2020-12-29 16:18:07","userID":12},{"appoStringmentEndTime":"2020-12-26 16:51:32","appoStringmentTime":"2020-12-25 16:51:32","creatTime":"2020-12-16 16:53:22","delTF":0,"floor":"2","id":1,"license":"浙·C7797","lotID":1,"seatName":"D1","status":2,"userID":12}],"lng":"121.500975","lotAddressId":1,"parkingName":"学府一号","parkingPrice":20,"regionID":330203,"slotPrice":10,"slotTime":3,"startTime":"2020-12-16 16:51:42","status":1,"updateTime":"2020-12-31 10:52:46"}
             * lotID : 1
             * mobile : 13486697492
             * orderNo : TNO20122415100154
             * orderPrice : 3
             * orderType : 1
             * payType : 3
             * relaPrice : 3
             * seatID : 1
             * status : 1
             * tUserInvoice : {"amount":3,"content":"内容","creatime":"2020-12-25 13:21:31","delTF":0,"examine":2,"id":1,"more":"电话12345678","orderId":10,"rise":"个人","status":2,"type":1,"updateTime":"2020-12-30 17:06:56","userID":12}
             * useTime : 0
             * userId : 12
             */

            private String appoStringmentPrice;
            private String creatTime;
            private String delTF;
            private String expressTime;
            private String id;
            private String license;
            private LotDtoBean lotDto;
            private String lotID;
            private String mobile;
            private String orderNo;
            private String orderPrice;
            private String orderType;
            private String payType;
            private String relaPrice;
            private String seatID;
            private String status;
            private TUserInvoiceBean tUserInvoice;
            private String useTime;
            private String userId;

            public String getAppoStringmentPrice() {
                return appoStringmentPrice;
            }

            public void setAppoStringmentPrice(String appoStringmentPrice) {
                this.appoStringmentPrice = appoStringmentPrice;
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

            public String getExpressTime() {
                return expressTime;
            }

            public void setExpressTime(String expressTime) {
                this.expressTime = expressTime;
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

            public LotDtoBean getLotDto() {
                return lotDto;
            }

            public void setLotDto(LotDtoBean lotDto) {
                this.lotDto = lotDto;
            }

            public String getLotID() {
                return lotID;
            }

            public void setLotID(String lotID) {
                this.lotID = lotID;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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

            public String getSeatID() {
                return seatID;
            }

            public void setSeatID(String seatID) {
                this.seatID = seatID;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public TUserInvoiceBean getTUserInvoice() {
                return tUserInvoice;
            }

            public void setTUserInvoice(TUserInvoiceBean tUserInvoice) {
                this.tUserInvoice = tUserInvoice;
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

            public static class LotDtoBean {
                /**
                 * address : 宁波海曙
                 * appoStringPrice : 3.5
                 * creatTime : 2020-12-16 16:51:32
                 * delTF : 0
                 * endTime : 2020-12-16 16:51:45
                 * freeTime : 1
                 * id : 1
                 * isRecommend : 0
                 * lat : 29.756425
                 * list : [{"creatTime":"2020-12-24 16:28:24","delTF":0,"floor":"1","id":2,"license":"浙·T7797","lotID":1,"seatName":"D2","startTime":"2020-12-25 17:16:48","status":1,"updateTime":"2020-12-29 16:18:07","userID":12},{"appoStringmentEndTime":"2020-12-26 16:51:32","appoStringmentTime":"2020-12-25 16:51:32","creatTime":"2020-12-16 16:53:22","delTF":0,"floor":"2","id":1,"license":"浙·C7797","lotID":1,"seatName":"D1","status":2,"userID":12}]
                 * lng : 121.500975
                 * lotAddressId : 1
                 * parkingName : 学府一号
                 * parkingPrice : 20
                 * regionID : 330203
                 * slotPrice : 10
                 * slotTime : 3
                 * startTime : 2020-12-16 16:51:42
                 * status : 1
                 * updateTime : 2020-12-31 10:52:46
                 */

                private String address;
                private String appoStringPrice;
                private String creatTime;
                private String delTF;
                private String endTime;
                private String freeTime;
                private String id;
                private String isRecommend;
                private String lat;
                private String lng;
                private String lotAddressId;
                private String parkingName;
                private String parkingPrice;
                private String regionID;
                private String slotPrice;
                private String slotTime;
                private String startTime;
                private String status;
                private String updateTime;
                private List<ListBean> list;

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

                public List<ListBean> getList() {
                    return list;
                }

                public void setList(List<ListBean> list) {
                    this.list = list;
                }

                public static class ListBean {
                    /**
                     * creatTime : 2020-12-24 16:28:24
                     * delTF : 0
                     * floor : 1
                     * id : 2
                     * license : 浙·T7797
                     * lotID : 1
                     * seatName : D2
                     * startTime : 2020-12-25 17:16:48
                     * status : 1
                     * updateTime : 2020-12-29 16:18:07
                     * userID : 12
                     * appoStringmentEndTime : 2020-12-26 16:51:32
                     * appoStringmentTime : 2020-12-25 16:51:32
                     */

                    private String creatTime;
                    private String delTF;
                    private String floor;
                    private String id;
                    private String license;
                    private String lotID;
                    private String seatName;
                    private String startTime;
                    private String status;
                    private String updateTime;
                    private String userID;
                    private String appoStringmentEndTime;
                    private String appoStringmentTime;

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

                    public String getAppoStringmentEndTime() {
                        return appoStringmentEndTime;
                    }

                    public void setAppoStringmentEndTime(String appoStringmentEndTime) {
                        this.appoStringmentEndTime = appoStringmentEndTime;
                    }

                    public String getAppoStringmentTime() {
                        return appoStringmentTime;
                    }

                    public void setAppoStringmentTime(String appoStringmentTime) {
                        this.appoStringmentTime = appoStringmentTime;
                    }
                }
            }

            public static class TUserInvoiceBean {
                /**
                 * amount : 3
                 * content : 内容
                 * creatime : 2020-12-25 13:21:31
                 * delTF : 0
                 * examine : 2
                 * id : 1
                 * more : 电话12345678
                 * orderId : 10
                 * rise : 个人
                 * status : 2
                 * type : 1
                 * updateTime : 2020-12-30 17:06:56
                 * userID : 12
                 */

                private String amount;
                private String content;
                private String creatime;
                private String delTF;
                private String examine;
                private String id;
                private String more;
                private String orderId;
                private String rise;
                private String status;
                private String type;
                private String updateTime;
                private String userID;

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getCreatime() {
                    return creatime;
                }

                public void setCreatime(String creatime) {
                    this.creatime = creatime;
                }

                public String getDelTF() {
                    return delTF;
                }

                public void setDelTF(String delTF) {
                    this.delTF = delTF;
                }

                public String getExamine() {
                    return examine;
                }

                public void setExamine(String examine) {
                    this.examine = examine;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMore() {
                    return more;
                }

                public void setMore(String more) {
                    this.more = more;
                }

                public String getOrderId() {
                    return orderId;
                }

                public void setOrderId(String orderId) {
                    this.orderId = orderId;
                }

                public String getRise() {
                    return rise;
                }

                public void setRise(String rise) {
                    this.rise = rise;
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
    }
}
