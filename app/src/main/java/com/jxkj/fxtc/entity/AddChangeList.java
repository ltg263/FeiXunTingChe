package com.jxkj.fxtc.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class AddChangeList implements Parcelable {

    /**
     * changeList : [{"changeTime":"2020-11-18T07:14:43.702Z","value":"14，8，0，32，31"},{"changeTime":"2020-11-18T07:14:43.702Z","value":"14，8，0，32，31"}]
     * id : 0
     */

    private int id;
    private List<ChangeListBean> changeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ChangeListBean> getChangeList() {
        return changeList;
    }

    public void setChangeList(List<ChangeListBean> changeList) {
        this.changeList = changeList;
    }

    public static class ChangeListBean implements Parcelable {
        /**
         * changeTime : 2020-11-18T07:14:43.702Z
         * value : 14，8，0，32，31
         */

        private String changeTime;
        private String value;

        public String getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(String changeTime) {
            this.changeTime = changeTime;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.changeTime);
            dest.writeString(this.value);
        }

        public ChangeListBean() {
        }

        protected ChangeListBean(Parcel in) {
            this.changeTime = in.readString();
            this.value = in.readString();
        }

        public static final Creator<ChangeListBean> CREATOR = new Creator<ChangeListBean>() {
            @Override
            public ChangeListBean createFromParcel(Parcel source) {
                return new ChangeListBean(source);
            }

            @Override
            public ChangeListBean[] newArray(int size) {
                return new ChangeListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeList(this.changeList);
    }

    public AddChangeList() {
    }

    protected AddChangeList(Parcel in) {
        this.id = in.readInt();
        this.changeList = new ArrayList<ChangeListBean>();
        in.readList(this.changeList, ChangeListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<AddChangeList> CREATOR = new Parcelable.Creator<AddChangeList>() {
        @Override
        public AddChangeList createFromParcel(Parcel source) {
            return new AddChangeList(source);
        }

        @Override
        public AddChangeList[] newArray(int size) {
            return new AddChangeList[size];
        }
    };
}
