package com.laifeng.cpsjobs.model.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */

import java.util.Date;

/**
 * Created by kouzhigang on 2015/5/15.
 */
public class PackageInfoTask {
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

    /**
     * 基本包ID
     */
    private int packageId;

    /**
     * 打包任务任务ID
     */
    private int taskId;

    /**
     * 工单号
     */
    private String jobCode;

    /**
     * 基本包名
     */
    private String basePackageName;

    /**
     * 基本包版本
     */
    private String basePackageVersion;

    /**
     * 基本包路径
     *
     * @return
     */
    private String path;

    /**
     * 下载路径
     *
     * @return
     */
    private String savePath;

    /**
     * 目标下载路径
     *
     * @return
     */
    private String downloadUrl;

    /**
     * 下载状态
     *
     * @return
     */
    private int downloadActive;

    /**
     * 基本包MD5
     *
     * @return
     */
    private String md5;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getDownloadActive() {
        return downloadActive;
    }

    public void setDownloadActive(int downloadActive) {
        this.downloadActive = downloadActive;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getCpsPositionId() {
        return cpsPositionId;
    }

    public void setCpsPositionId(int cpsPositionId) {
        this.cpsPositionId = cpsPositionId;
    }

    public String getCpsPositionName() {
        return cpsPositionName;
    }

    public void setCpsPositionName(String cpsPositionName) {
        this.cpsPositionName = cpsPositionName;
    }

    public String getCpsInfo() {
        return cpsInfo;
    }

    public void setCpsInfo(String cpsInfo) {
        this.cpsInfo = cpsInfo;
    }

    public String getFriendChannelName() {
        return friendChannelName;
    }

    public void setFriendChannelName(String friendChannelName) {
        this.friendChannelName = friendChannelName;
    }

    public int getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }

    public String getIsFirstImage() {
        return isFirstImage;
    }

    public void setIsFirstImage(String isFirstImage) {
        this.isFirstImage = isFirstImage;
    }

    public int getIsFirstPosition() {
        return isFirstPosition;
    }

    public void setIsFirstPosition(int isFirstPosition) {
        this.isFirstPosition = isFirstPosition;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getBasePackageName() {
        return basePackageName;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    public String getBasePackageVersion() {
        return basePackageVersion;
    }

    public void setBasePackageVersion(String basePackageVersion) {
        this.basePackageVersion = basePackageVersion;
    }

    @Override
    public String toString() {
        return "PackageInfoTask{" +
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
                ", packageId=" + packageId +
                ", taskId=" + taskId +
                ", jobCode='" + jobCode + '\'' +
                ", basePackageName='" + basePackageName + '\'' +
                ", basePackageVersion='" + basePackageVersion + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

