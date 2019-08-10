package com.laifeng.cpsjobs.service.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */

import com.laifeng.cpsjobs.model.cps.PackageTask;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/14.
 */
public interface PackageTaskService {
    /**
     * 获取打包任务列表
     *
     * @return
     */
    public List<PackageTask> listPackageTask(
            int taskStatus,
            int offsite,
            int length
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
    public void deletePackageTask(int id);

    /**
     * 更新下载状态
     *
     * @param id
     */
    public void updateStatus(int id);

    /**
     * 更新锁
     */
    public void updateLock(int id, int taskStatus);

    /**
     * 查看锁
     */
    public int lookLock(int id);

    /**
     * count
     */
    public int countPackageTask(int taskStatus);
}
