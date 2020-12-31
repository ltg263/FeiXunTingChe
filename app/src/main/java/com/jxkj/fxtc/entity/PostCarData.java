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
}
