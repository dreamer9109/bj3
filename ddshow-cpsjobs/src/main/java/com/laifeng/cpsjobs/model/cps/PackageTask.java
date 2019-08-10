package com.laifeng.cpsjobs.model.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */
import java.util.Date;

/**
 * Created by kouzhigang on 2015/5/7.
 * 打包任务
 */
public class PackageTask {

    /**
     * 基本包任务ID
     */
    private int id;

    /**
     * 工单号
     */
    private String jobCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 任务状态
     */
    private int taskStatus;

    /**
     * 确认状态(下载)
     */
    private int status;

    public int getId() {
        return id;
    }

    public String getJobCode() {
        return jobCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PackageTask{" +
                "id=" + id +
                ", jobCode='" + jobCode + '\'' +
                ", createTime=" + createTime +
                ", taskStatus=" + taskStatus +
                ", status=" + status +
                '}';
    }
}

