package com.laifeng.cpsjobs.service.cps;


import com.laifeng.cpsjobs.model.cps.PackageInfoTask;
import com.laifeng.fs.mogile.model.LaifengFileSystemException;
import com.laifeng.fs.mogile.model.MogileException;

import java.io.IOException;

/**
 * Created by kouzhigang on 2015/5/22.
 */

public interface PackageService {

    //执行打包任务
    public void executeTask(PackageInfoTask packageInfoTaskList) throws IOException;

    //锁定任务状态
    public void closeStatus(int taskId, int statusCode);

    //执行python脚本
    public void executeScript(
            int iid,
            int id,
            String apkPath,
            String cpsPosition,
            String cpsCookie,
            int isFirst,
            String isFirstImg,
            int isFirstPosition,
            String friendChannelName,
            String jobCode,
            int cpsPositionId,
            String cpsVersion,
            String md5
    ) throws IOException, MogileException, LaifengFileSystemException;

    //解除锁定任务
    public void unCloseStatus(int taskId, int statusCode);

    public void runnable(Integer[] taskList, PackageService packageService);
}
