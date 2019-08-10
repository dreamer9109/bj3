package com.laifeng.cpsjobs.model.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */
import java.util.Date;

/**
 * Created by kouzhigang on 2015/5/7.
 * 基本包
 */
public class PackageBase {

    /**
     * 基本包ID
     */
    private int id;

    /**
     * 基本包名称
     */
    private String name;

    /**
     * 基本包版本
     */
    private String version;

    /**
     * 基本包创建时间
     */

    private Date createTime;

    /**
     * 基本包状态
     */
    private int status;

    /**
     * 基本包路径
     */
    private String path;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
