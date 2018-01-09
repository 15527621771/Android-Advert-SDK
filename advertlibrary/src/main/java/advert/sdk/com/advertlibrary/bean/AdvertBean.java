package advert.sdk.com.advertlibrary.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 模拟数据javabean
 */

public class AdvertBean implements Parcelable {
    //广告显示时间,毫秒
    private int advertTime;
    //广告类型,0=插屏,1=横幅,2=通知栏
    private int advertType;
    //横幅广告位置,0=不是横幅,1=顶部,2=底部
    private int bannerLocation;
    //广告图片连接
    private String advertPicUrl;
    //广告apk下载连接
    private String advertApkDownloadUrl;

    public AdvertBean() {
        super();
    }

    public AdvertBean(int advertTime, int advertType, int bannerLocation, String advertPicUrl, String advertApkDownloadUrl) {
        this.advertTime = advertTime;
        this.advertType = advertType;
        this.bannerLocation = bannerLocation;
        this.advertPicUrl = advertPicUrl;
        this.advertApkDownloadUrl = advertApkDownloadUrl;
    }

    public int getAdvertTime() {
        return advertTime;
    }

    public void setAdvertTime(int advertTime) {
        this.advertTime = advertTime;
    }

    public int getAdvertType() {
        return advertType;
    }

    public void setAdvertType(int advertType) {
        this.advertType = advertType;
    }

    public int getBannerLocation() {
        return bannerLocation;
    }

    public void setBannerLocation(int bannerLocation) {
        this.bannerLocation = bannerLocation;
    }

    public String getAdvertPicUrl() {
        return advertPicUrl;
    }

    public void setAdvertPicUrl(String advertPicUrl) {
        this.advertPicUrl = advertPicUrl;
    }

    public String getAdvertApkDownloadUrl() {
        return advertApkDownloadUrl;
    }

    public void setAdvertApkDownloadUrl(String advertApkDownloadUrl) {
        this.advertApkDownloadUrl = advertApkDownloadUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.advertTime);
        dest.writeInt(this.advertType);
        dest.writeInt(this.bannerLocation);
        dest.writeString(this.advertPicUrl);
        dest.writeString(this.advertApkDownloadUrl);
    }

    protected AdvertBean(Parcel in) {
        this.advertTime = in.readInt();
        this.advertType = in.readInt();
        this.bannerLocation = in.readInt();
        this.advertPicUrl = in.readString();
        this.advertApkDownloadUrl = in.readString();
    }

    public static final Parcelable.Creator<AdvertBean> CREATOR = new Parcelable.Creator<AdvertBean>() {
        @Override
        public AdvertBean createFromParcel(Parcel source) {
            return new AdvertBean(source);
        }

        @Override
        public AdvertBean[] newArray(int size) {
            return new AdvertBean[size];
        }
    };

    @Override
    public String toString() {
        return "AdvertBean{" +
                "advertTime=" + advertTime +
                ", advertType=" + advertType +
                ", bannerLocation=" + bannerLocation +
                ", advertPicUrl='" + advertPicUrl + '\'' +
                ", advertApkDownloadUrl='" + advertApkDownloadUrl + '\'' +
                '}';
    }
}
