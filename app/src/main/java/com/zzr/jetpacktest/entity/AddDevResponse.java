package com.zzr.jetpacktest.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zzr
 * @Desc
 * @Date 2020/9/8
 */
public class AddDevResponse implements Parcelable {

    /**
     * data : {"result":"ok","devModleSw":"V2.1.8","plugMult":0,"devNo":335,"plugDiv":0,"devModleId":"HA-LGT-C30H","devChNum":2,"uuid":"440e3cb861184d058f7f5937cdcef208","devModleHw":"0","chList":[{"devType":256,"devCh":0},{"devType":256,"devCh":1}],"devIeeeAddr":"124b001d3881c5"}
     * fromDev : g_83290
     * toDev : a_18950
     */

    private DataBean data;
    private String fromDev;
    private String toDev;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getFromDev() {
        return fromDev;
    }

    public void setFromDev(String fromDev) {
        this.fromDev = fromDev;
    }

    public String getToDev() {
        return toDev;
    }

    public void setToDev(String toDev) {
        this.toDev = toDev;
    }

    public static class DataBean implements Parcelable {
        /**
         * result : ok
         * devModleSw : V2.1.8
         * plugMult : 0
         * devNo : 335
         * plugDiv : 0
         * devModleId : HA-LGT-C30H
         * devChNum : 2
         * uuid : 440e3cb861184d058f7f5937cdcef208
         * devModleHw : 0
         * chList : [{"devType":256,"devCh":0},{"devType":256,"devCh":1}]
         * devIeeeAddr : 124b001d3881c5
         */

        private int errno;
        private String result;
        private String devModleSw;
        private int plugMult;
        private int devNo;
        private int plugDiv;
        private String devModleId;
        private int devChNum;
        private String uuid;
        private String devModleHw;
        private String devIeeeAddr;
        private ArrayList<ChListBean> chList;

        public boolean isSucceed() {
            return "ok".equals(result);
        }

        public int getErrno() {
            return errno;
        }

        public void setErrno(int errno) {
            this.errno = errno;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getDevModleSw() {
            return devModleSw;
        }

        public void setDevModleSw(String devModleSw) {
            this.devModleSw = devModleSw;
        }

        public int getPlugMult() {
            return plugMult;
        }

        public void setPlugMult(int plugMult) {
            this.plugMult = plugMult;
        }

        public int getDevNo() {
            return devNo;
        }

        public void setDevNo(int devNo) {
            this.devNo = devNo;
        }

        public int getPlugDiv() {
            return plugDiv;
        }

        public void setPlugDiv(int plugDiv) {
            this.plugDiv = plugDiv;
        }

        public String getDevModleId() {
            return devModleId;
        }

        public void setDevModleId(String devModleId) {
            this.devModleId = devModleId;
        }

        public int getDevChNum() {
            return devChNum;
        }

        public void setDevChNum(int devChNum) {
            this.devChNum = devChNum;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getDevModleHw() {
            return devModleHw;
        }

        public void setDevModleHw(String devModleHw) {
            this.devModleHw = devModleHw;
        }

        public String getDevIeeeAddr() {
            return devIeeeAddr;
        }

        public void setDevIeeeAddr(String devIeeeAddr) {
            this.devIeeeAddr = devIeeeAddr;
        }

        public List<ChListBean> getChList() {
            return chList;
        }

        public void setChList(ArrayList<ChListBean> chList) {
            this.chList = chList;
        }

        public static class ChListBean implements Parcelable {
            /**
             * devType : 256
             * devCh : 0
             */

            private int devType;
            private int devCh;

            public int getDevType() {
                return devType;
            }

            public void setDevType(int devType) {
                this.devType = devType;
            }

            public int getDevCh() {
                return devCh;
            }

            public void setDevCh(int devCh) {
                this.devCh = devCh;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.devType);
                dest.writeInt(this.devCh);
            }

            public ChListBean() {
            }

            protected ChListBean(Parcel in) {
                this.devType = in.readInt();
                this.devCh = in.readInt();
            }

            public static final Creator<ChListBean> CREATOR = new Creator<ChListBean>() {
                @Override
                public ChListBean createFromParcel(Parcel source) {
                    return new ChListBean(source);
                }

                @Override
                public ChListBean[] newArray(int size) {
                    return new ChListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.errno);
            dest.writeString(this.result);
            dest.writeString(this.devModleSw);
            dest.writeInt(this.plugMult);
            dest.writeInt(this.devNo);
            dest.writeInt(this.plugDiv);
            dest.writeString(this.devModleId);
            dest.writeInt(this.devChNum);
            dest.writeString(this.uuid);
            dest.writeString(this.devModleHw);
            dest.writeString(this.devIeeeAddr);
            dest.writeTypedList(this.chList);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.errno = in.readInt();
            this.result = in.readString();
            this.devModleSw = in.readString();
            this.plugMult = in.readInt();
            this.devNo = in.readInt();
            this.plugDiv = in.readInt();
            this.devModleId = in.readString();
            this.devChNum = in.readInt();
            this.uuid = in.readString();
            this.devModleHw = in.readString();
            this.devIeeeAddr = in.readString();
            this.chList = in.createTypedArrayList(ChListBean.CREATOR);
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.fromDev);
        dest.writeString(this.toDev);
    }

    public AddDevResponse() {
    }

    protected AddDevResponse(Parcel in) {
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.fromDev = in.readString();
        this.toDev = in.readString();
    }

    public static final Creator<AddDevResponse> CREATOR = new Creator<AddDevResponse>() {
        @Override
        public AddDevResponse createFromParcel(Parcel source) {
            return new AddDevResponse(source);
        }

        @Override
        public AddDevResponse[] newArray(int size) {
            return new AddDevResponse[size];
        }
    };
}
