package com.laifeng.cpsjobs.service.cps;


import com.laifeng.cpsjobs.model.cps.PackageInfoTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/22.
 */
public class PackageTaskRunnable implements Runnable {
    private static Log logger = LogFactory.getLog(PackageTaskRunnable.class);

    private List<PackageInfoTask> packageInfoTaskList;

    private PackageService packageService;

    public PackageTaskRunnable(List<PackageInfoTask> packageInfoTaskList, PackageService packageService) {
        this.packageInfoTaskList = packageInfoTaskList;
        this.packageService = packageService;
    }

    public void run() {
        for (PackageInfoTask packageInfoTask : this.packageInfoTaskList) {
            try {
                this.packageService.executeTask(packageInfoTask);
            } catch (Exception e) {
                logger.error("execute task error: {}", e);
            }
        }
    }
}
