package com.google.dunggiaobt.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//vu
public class BaiHat implements Parcelable {
    @SerializedName("IdBaihat")
    @Expose
    private String idbaihat;
    @SerializedName("Tenbaihat")
    @Expose
    private String tenbaihat;
    @SerializedName("Hinhbaihat")
    @Expose
    private String Hinhbaihat;

    @SerializedName("Casi")
    @Expose
    private String casi;
    @SerializedName("Linkbaihat")
    @Expose
    private String linkbaihat;
    @SerializedName("Luotthich")
    @Expose
    private String luotthich;

    protected BaiHat(Parcel in) {
        idbaihat = in.readString();
        tenbaihat = in.readString();
        Hinhbaihat = in.readString();
        casi = in.readString();
        linkbaihat = in.readString();
        luotthich = in.readString();
    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

    public String getHinhbaihat() {
        return Hinhbaihat;
    }

    public void setHinhbaihat(String hinhbaihat) {
        Hinhbaihat = hinhbaihat;
    }

    public String getCasi() {
        return casi;
    }

    public String getIdbaihat() {
        return idbaihat;
    }

    public String getLinkbaihat() {
        return linkbaihat;
    }

    public String getLuotthich() {
        return luotthich;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setCasi(String casi) {
        this.casi = casi;
    }

    public void setIdbaihat(String idbaihat) {
        this.idbaihat = idbaihat;
    }

    public void setLinkbaihat(String linkbaihat) {
        this.linkbaihat = linkbaihat;
    }

    public void setLuotthich(String luotthich) {
        this.luotthich = luotthich;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idbaihat);
        parcel.writeString(tenbaihat);
        parcel.writeString(Hinhbaihat);
        parcel.writeString(casi);
        parcel.writeString(linkbaihat);
        parcel.writeString(luotthich);
    }
}
