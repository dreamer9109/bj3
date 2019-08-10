package com.laifeng.cpsjobs.service.impl.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */

import com.laifeng.cpsjobs.dao.stat.cps.PackageInfoTaskMapper;
import com.laifeng.cpsjobs.model.cps.PackageInfoTask;
import com.laifeng.cpsjobs.service.cps.PackageInfoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/15.
 */
@Service
public class PackageInfoTaskServiceImpl implements PackageInfoTaskService {
    @Autowired
    private PackageInfoTaskMapper packageInfoTaskMapper;

    public void insertPackageInfoTask(PackageInfoTask packageInfoTask) {
        this.packageInfoTaskMapper.insertPackageInfoTask(packageInfoTask);
    }

    public void deletePackageInfoTask(int id) {
        this.packageInfoTaskMapper.deletePackageInfoTask(id);
    }

    public List<PackageInfoTask> listPackageInfoTask(int id) {
        return this.packageInfoTaskMapper.listPackageInfoTask(id);
    }

    public void updateSavePath(int id, String savePath) {
        this.packageInfoTaskMapper.updateSavePath(id, savePath);
    }

    public PackageInfoTask getPackageInfoTaskById(int id) {
        return this.packageInfoTaskMapper.getPackageInfoTaskById(id);
    }

    @Override
    public void updateDownloadActive(int id, int downloadActive) {
        this.packageInfoTaskMapper.updateDownloadActive(id, downloadActive);
    }
}
