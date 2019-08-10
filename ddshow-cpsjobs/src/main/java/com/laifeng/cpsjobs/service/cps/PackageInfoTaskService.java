package com.laifeng.cpsjobs.service.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */

import com.laifeng.cpsjobs.model.cps.PackageInfoTask;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/15.
 */
public interface PackageInfoTaskService {
    public void insertPackageInfoTask(PackageInfoTask packageInfoTask);

    public void deletePackageInfoTask(int id);

    public List<PackageInfoTask> listPackageInfoTask(int id);

    public void updateSavePath(int id, String savePath);

    public PackageInfoTask getPackageInfoTaskById(int id);

    public void updateDownloadActive(int id, int downloadActive);
}
