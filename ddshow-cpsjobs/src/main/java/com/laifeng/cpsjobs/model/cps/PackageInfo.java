package com.laifeng.cpsjobs.model.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */
import java.util.Date;

/**
 * Created by kouzhigang on 2015/5/7.
 * 打包信息
 */
public class PackageInfo {
    /**
     * 信息ID
     */
    private int id;

    /**
     * 打包名
     */
    private String packageName;

    /**
     * 子渠道ID
     */
    private int cpsPositionId;

    /**
     * 子渠道名
     */
    private String cpsPositionName;

    /**
     * CPS COOKIE
     */
    private String cpsInfo;

    /**
     * 友盟渠道名字
     */
    private String friendChannelName;

    /**
     * 是否首发
     */
    private int isFirst;

    /**
     * 首发图片
     */
    private String isFirstImage;

    /**
     * 首发图片位置
     */
    private int isFirstPosition;

    /**
     * 创建时间
     */
    private Date createTime;

    public int getId() {
        return id;
    }

    public String getPackageName() {
        return packageName;
    }

    public int getCpsPositionId() {
        return cpsPositionId;
    }

    public String getCpsPositionName(){
        return cpsPositionName;
    }

    public String getCpsInfo() {
        return cpsInfo;
    }

    public String getFriendChannelName() {
        return friendChannelName;
    }

    public int getIsFirst() {
        return isFirst;
    }

    public String getIsFirstImage() {
        return isFirstImage;
    }

    public int getIsFirstPosition() {
        return isFirstPosition;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setCpsPositionId(int cpsPositionId) {
        this.cpsPositionId = cpsPositionId;
    }

    public void setCpsPositionName(String cpsPositionName){
        this.cpsPositionName = cpsPositionName;
    }

    public void setCpsInfo(String cpsInfo) {
        this.cpsInfo = cpsInfo;
    }

    public void setFriendChannelName(String friendChannelName) {
        this.friendChannelName = friendChannelName;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }

    public void setIsFirstImage(String isFirstImage) {
        this.isFirstImage = isFirstImage;
    }

    public void setIsFirstPosition(int isFirstPosition) {
        this.isFirstPosition = isFirstPosition;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PackageInfo{" +
                "id=" + id +
                ", packageName='" + packageName + '\'' +
                ", cpsPositionId=" + cpsPositionId +
                ", cpsPositionName='" + cpsPositionName + '\'' +
                ", cpsInfo='" + cpsInfo + '\'' +
                ", friendChannelName='" + friendChannelName + '\'' +
                ", isFirst=" + isFirst +
                ", isFirstImage='" + isFirstImage + '\'' +
                ", isFirstPosition=" + isFirstPosition +
                ", createTime=" + createTime +
                '}';
    }
}

