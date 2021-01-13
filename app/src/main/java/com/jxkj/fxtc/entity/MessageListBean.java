package com.jxkj.fxtc.entity;

import java.util.List;

public class MessageListBean {

    /**
     * count : 1
     * list : [{"createTime":"2021-01-07 16:44:32","detail":"内容","id":1,"messageType":1,"status":1,"title":"标题","userID":12}]
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
         * createTime : 2021-01-07 16:44:32
         * detail : 内容
         * id : 1
         * messageType : 1
         * status : 1
         * title : 标题
         * userID : 12
         */

        private String createTime;
        private String detail;
        private String id;
        private String messageType;
        private String status;
        private String title;
        private String userID;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }
    }
}
