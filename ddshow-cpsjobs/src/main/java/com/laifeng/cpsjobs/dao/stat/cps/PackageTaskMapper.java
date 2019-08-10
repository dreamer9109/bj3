package com.laifeng.cpsjobs.dao.stat.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */

import com.laifeng.cpsjobs.model.cps.PackageTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/14.
 */
public interface PackageTaskMapper {
    /**
     * 获取打包任务列表
     *
     * @return
     */
    public List<PackageTask> listPackageTask(
            @Param("taskStatus") int taskStatus,
            @Param("offsite") int offsite,
            @Param("length") int length
    );

    /**
     * 新建打包任务
     *
     * @param packageTask
     */
    public void insertPackageTask(PackageTask packageTask);

    /**
     * 删除打包任务
     *
     * @param id
     */
    public void deletePackageTask(@Param("id") int id);

    /**
     * 更新下载状态
     *
     * @param id
     */
    public void updateStatus(@Param("id") int id);

    /**
     * 更新锁
     */
    public void updateLock(@Param("id") int id, @Param("taskStatus") int taskStatus);

    /**
     * 查看锁
     */
    public int lookLock(@Param("id") int id);

    /**
     * count
     */
    public int countPackageTask(@Param("taskStatus") int taskStatus);
}

