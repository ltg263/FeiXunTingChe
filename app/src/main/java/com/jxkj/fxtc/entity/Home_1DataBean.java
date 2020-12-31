package com.jxkj.fxtc.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Home_1DataBean implements Parcelable {

    List<DataInfo> data_1;
    List<DataInfo> data_2;
    List<DataInfo> data_3;
    List<DataInfo> data_4;
    List<DataInfo> data_5;

    public List<DataInfo> getData_1() {
        return data_1;
    }

    public void setData_1(List<DataInfo> data_1) {
        this.data_1 = data_1;
    }

    public List<DataInfo> getData_2() {
        return data_2;
    }

    public void setData_2(List<DataInfo> data_2) {
        this.data_2 = data_2;
    }

    public List<DataInfo> getData_3() {
        return data_3;
    }

    public void setData_3(List<DataInfo> data_3) {
        this.data_3 = data_3;
    }

    public List<DataInfo> getData_4() {
        return data_4;
    }

    public void setData_4(List<DataInfo> data_4) {
        this.data_4 = data_4;
    }

    public List<DataInfo> getData_5() {
        return data_5;
    }

    public void setData_5(List<DataInfo> data_5) {
        this.data_5 = data_5;
    }

    public static class DataInfo implements Parcelable {
        String time;
        String angle;

        public DataInfo(String time, String angle) {
            this.time = time;
            this.angle = angle;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAngle() {
            return angle;
        }

        public void setAngle(String angle) {
            this.angle = angle;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.time);
            dest.writeString(this.angle);
        }

        protected DataInfo(Parcel in) {
            this.time = in.readString();
            this.angle = in.readString();
        }

        public static final Creator<DataInfo> CREATOR = new Creator<DataInfo>() {
            @Override
            public DataInfo createFromParcel(Parcel source) {
                return new DataInfo(source);
            }

            @Override
            public DataInfo[] newArray(int size) {
                return new DataInfo[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data_1);
        dest.writeList(this.data_2);
        dest.writeList(this.data_3);
        dest.writeList(this.data_4);
        dest.writeList(this.data_5);
    }

    public Home_1DataBean() {
    }

    protected Home_1DataBean(Parcel in) {
        this.data_1 = new ArrayList<DataInfo>();
        in.readList(this.data_1, DataInfo.class.getClassLoader());
        this.data_2 = new ArrayList<DataInfo>();
        in.readList(this.data_2, DataInfo.class.getClassLoader());
        this.data_3 = new ArrayList<DataInfo>();
        in.readList(this.data_3, DataInfo.class.getClassLoader());
        this.data_4 = new ArrayList<DataInfo>();
        in.readList(this.data_4, DataInfo.class.getClassLoader());
        this.data_5 = new ArrayList<DataInfo>();
        in.readList(this.data_5, DataInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<Home_1DataBean> CREATOR = new Parcelable.Creator<Home_1DataBean>() {
        @Override
        public Home_1DataBean createFromParcel(Parcel source) {
            return new Home_1DataBean(source);
        }

        @Override
        public Home_1DataBean[] newArray(int size) {
            return new Home_1DataBean[size];
        }
    };
}
