package com.laifeng.cpsjobs.service.impl.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */

import com.laifeng.cpsjobs.dao.stat.cps.PackageTaskMapper;
import com.laifeng.cpsjobs.model.cps.PackageTask;
import com.laifeng.cpsjobs.service.cps.PackageTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/14.
 */
@Service
public class PackageTaskServiceImpl implements PackageTaskService {

    @Autowired
    private PackageTaskMapper packageTaskMapper;

    /**
     * 获取打包任务列表
     *
     * @return
     */
    @Override
    public List<PackageTask> listPackageTask(int taskStatus, int offsite, int length) {
        return this.packageTaskMapper.listPackageTask(taskStatus, offsite, length);
    }

    /**
     * 新建打包任务
     *
     * @param packageTask
     */
    @Override
    public void insertPackageTask(PackageTask packageTask) {
        this.packageTaskMapper.insertPackageTask(packageTask);
    }

    /**
     * 删除打包任务
     *
     * @param id
     */
    @Override
    public void deletePackageTask(int id) {
        this.packageTaskMapper.deletePackageTask(id);
    }

    /**
     * 更新下载状态
     *
     * @param id
     */
    @Override
    public void updateStatus(int id) {
        this.packageTaskMapper.updateStatus(id);
    }

    /**
     * 更新锁
     *
     * @param id
     * @param taskStatus
     */
    public void updateLock(int id, int taskStatus) {
        this.packageTaskMapper.updateLock(id, taskStatus);
    }

    /**
     * 查看锁
     */
    public int lookLock(int id) {
        return this.packageTaskMapper.lookLock(id);
    }

    /**
     * count
     */
    @Override
    public int countPackageTask(int taskStatus) {
        return this.packageTaskMapper.countPackageTask(taskStatus);
    }
}

