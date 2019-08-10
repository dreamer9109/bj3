package com.laifeng.cpsjobs.service.impl.cps;

import com.laifeng.cpsjobs.model.cps.PackageInfoTask;
import com.laifeng.cpsjobs.service.cps.PackageInfoTaskService;
import com.laifeng.cpsjobs.service.cps.PackageService;
import com.laifeng.cpsjobs.service.cps.PackageTaskRunnable;
import com.laifeng.cpsjobs.service.cps.PackageTaskService;
import com.laifeng.cpsjobs.utils.FileMd5Utils;
import com.laifeng.fs.mogile.model.LaifengFileSystemException;
import com.laifeng.fs.mogile.model.MogileException;
import com.laifeng.fs.mogile.service.LaifengFileSystemService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kouzhigang on 2015/5/22.
 */
@Service
public class PackageServiceImpl implements PackageService {

    final private String commandSeparator = " ";

    final private int NO_LOCK_CODE = 0;
    final private int LOCK_CODE = 2;
    final private int UN_LOCK_CODE = 1;

    private final static String IMG_SERVICE = "package/";

    private ExecutorService execute = Executors.newFixedThreadPool(1);

    @Autowired
    private PackageInfoTaskService packageInfoTaskService;

    @Autowired
    private PackageTaskService packageTaskService;

    @Autowired
    private LaifengFileSystemService laifengFileSystemService;

    private Log logger = LogFactory.getLog(PackageServiceImpl.class);

    @Autowired
    @Qualifier("configProperties")
    private Properties configProperties;


    //执行打包任务
    public void executeTask(PackageInfoTask packageInfoTask) throws IOException {
        //打包信息ID
        int id = packageInfoTask.getId();

        //打包任务ID
        int taskId = packageInfoTask.getTaskId();

        //基本包路径
        String apkPath = packageInfoTask.getPath();

        //渠道名
        String cpsPosition = packageInfoTask.getCpsPositionName();

        //渠道ID
        int cpsPositionId = packageInfoTask.getCpsPositionId();

        //版本
        String cpsVersion = packageInfoTask.getBasePackageVersion();

        //cookie
        String cpsCookie = packageInfoTask.getCpsInfo();

        //是否首发
        int isFirst = packageInfoTask.getIsFirst();

        //首发图片
        String isFirstImg = packageInfoTask.getIsFirstImage();

        //首发图片位置
        int isFirstPosition = packageInfoTask.getIsFirstPosition();

        //友盟渠道名
        String friendChannelName = packageInfoTask.getFriendChannelName();

        //jobCode
        String jobCode = packageInfoTask.getJobCode();

        //基本包md5
        String md5 = packageInfoTask.getMd5();

        //锁定任务状态
        this.closeStatus(taskId, LOCK_CODE);

        //执行打包外部命令
        try {
            this.executeScript(
                    id,
                    taskId,
                    apkPath,
                    cpsPosition,
                    cpsCookie,
                    isFirst,
                    isFirstImg,
                    isFirstPosition,
                    friendChannelName,
                    jobCode,
                    cpsPositionId,
                    cpsVersion,
                    md5
            );
            this.unCloseStatus(taskId, UN_LOCK_CODE);
        } catch (Exception e) {
            this.unCloseStatus(taskId, UN_LOCK_CODE);
        } finally {
            this.unCloseStatus(taskId, UN_LOCK_CODE);
        }
    }

    //锁定任务状态
    public void closeStatus(int taskId, int statusCode) {
        this.packageTaskService.updateLock(taskId, statusCode);
    }

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
    ) throws IOException, MogileException, LaifengFileSystemException {
        String isFirstImgPath = StringUtils.EMPTY;
        String parame = StringUtils.EMPTY;

        String resPath = this.configProperties.getProperty("package.resourceDir");
        if (resPath == null) {
            logger.error("package.resourceDir:null");
        }

        File resPathReal = new File(resPath);
        if (!resPathReal.exists()) {
            resPathReal.mkdirs();
        }

        String savePath = this.configProperties.getProperty("package.saveDir");
        if (savePath == null) {
            logger.error("package.saveDir:null");
        }

        File savePathReal = new File(savePath);
        if (!savePathReal.exists()) {
            savePathReal.mkdirs();
        }

        String basePackagePath = this.configProperties.getProperty("package.basePackageSaveDir");
        if (basePackagePath == null) {
            logger.error("package.basePackageSaveDir:null");
        }

        File basePackagePathReal = new File(basePackagePath);
        if (!basePackagePathReal.exists()) {
            basePackagePathReal.mkdirs();
        }

        String executeScriptPath = this.configProperties.getProperty("package.packageScriptDir");
        if (executeScriptPath == null) {
            logger.error("package.packageScriptDir:null");
        }

        //下载基本包
        String[] apkSourcePathArr = apkPath.split("/");
        String apkSourceName = apkSourcePathArr[apkSourcePathArr.length - 1];

        String basePackageSavePath = basePackagePath + "/" + "_" + md5 + "_" + apkSourceName;
        File basePackageSavePathFile = new File(basePackageSavePath);

        String apkPathDownload = null;

        //校验基本包及md5值 防止每次打包重新下载基本包
        if (!basePackageSavePathFile.exists()) {
            try {
                apkPathDownload = this.laifengFileSystemService.saveFile(apkPath, basePackageSavePath);
                logger.info("download base package:" + apkPathDownload);
            } catch (LaifengFileSystemException e) {
                e.printStackTrace();
                logger.error(apkPath + "download load error", e);
            }
        } else {
            if (StringUtils.isNotBlank(md5) && md5.equals(FileMd5Utils.getFileMD5String(basePackageSavePathFile))) {
                apkPathDownload = basePackageSavePath;
            } else {
                try {
                    apkPathDownload = this.laifengFileSystemService.saveFile(apkPath, basePackageSavePath);
                    logger.info("re download base package:" + apkPathDownload);
                } catch (LaifengFileSystemException e) {
                    e.printStackTrace();
                    logger.error(apkPath + "re download load error", e);
                }
            }
        }

        //生成打包路径、文件名
        String nameFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:sssSSS").format(new Date());
        //String newName = nameFormat.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") + Math.round(Math.random() * 8999 + 1000);
        String saveFullPath = savePathReal + "/" + jobCode;

        //如果首次发布并且上传首发图片
        if (isFirst != 0 && StringUtils.isNotBlank(isFirstImg)) {
            String isFirstImgDir = resPathReal + "/" + jobCode;
            File isFirstImgDirReal = new File(isFirstImgDir);
            if (!isFirstImgDirReal.exists()) {
                isFirstImgDirReal.mkdirs();
            }

            isFirstImgPath = isFirstImgDirReal + "/" + isFirstImg.substring(20) + ".jpg";
            String downloadImage = PackageServiceImpl.exec("wget -P " + resPathReal + "/ " + isFirstImg + " -O " + isFirstImgPath, null).toString();
            logger.info("python image:" + downloadImage);

            parame = "python " + executeScriptPath + commandSeparator +
                    apkPathDownload + commandSeparator +
                    saveFullPath + commandSeparator +
                    friendChannelName + commandSeparator +
                    cpsCookie + commandSeparator +
                    isFirstImgPath + commandSeparator +
                    isFirstPosition;
        } else {
            parame = "python " + executeScriptPath + commandSeparator +
                    apkPathDownload + commandSeparator +
                    saveFullPath + commandSeparator +
                    friendChannelName + commandSeparator +
                    cpsCookie;
        }

        logger.info("package parameter: " + parame);
        String executeInfo = PackageServiceImpl.exec(parame, null).toString();

        logger.info("python return:" + executeInfo);

        if (StringUtils.isNotBlank(cpsVersion)) {
            cpsVersion = cpsVersion.replace(".", "_");
        }

        //mfs key
        String remotePath = IMG_SERVICE + cpsPositionId + "/" + friendChannelName + "_" + cpsVersion + "_" + id + ".apk";
        String remoteUrl = null;

        try {
            //上传到MFS
            remoteUrl = this.laifengFileSystemService.uploadFile(saveFullPath + "/" + friendChannelName + ".apk", remotePath);
            logger.info("upload package apk:" + remoteUrl);

            if (remoteUrl != null) {
                this.packageInfoTaskService.updateSavePath(iid, remoteUrl);
                logger.info("python " + nameFormat + ":" + parame);
            } else {
                logger.error("package error: " + iid);
                throw new MogileException("package upload mfs error");
            }
        } catch (LaifengFileSystemException e) {
            throw new LaifengFileSystemException(e);

        } catch (MogileException e) {
            //尝试重新上传打包文件
            logger.error(apkPath + " apk upload error", e);

            boolean reUpload = true;
            int i = 1;

            while (reUpload) {
                logger.warn("try to re upload " + apkPath);
                logger.warn("try to re upload " + i);

                try {
                    //上传到MFS
                    remoteUrl = this.laifengFileSystemService.uploadFile(saveFullPath + "/" + friendChannelName + ".apk", remotePath);
                    logger.info("re upload package apk:" + remoteUrl);

                    if (remoteUrl != null) {
                        this.packageInfoTaskService.updateSavePath(iid, remoteUrl);
                        logger.info("python " + nameFormat + ":" + parame);

                        reUpload = false;
                    } else {
                        logger.error("re upload package error: " + iid);
                    }

                    i++;
                } catch (Exception ee) {
                    logger.error("re upload error :{}", ee);
                }
            }
//            throw new MogileException(e);
        } finally {
            //删除基本包 兼容md5校验 每次打完不删除基本包
//            File apkPathDownloadFile = new File(apkPathDownload);
//            if (apkPathDownloadFile.exists()) {
//                apkPathDownloadFile.delete();
//            }

            //删除打包生成包
            File saveFullPathFile = new File(saveFullPath + "/" + friendChannelName + ".apk");
            if (saveFullPathFile.exists()) {
                saveFullPathFile.delete();
            }

            //删除首发图片
            File isFirstImgPathFile = new File(isFirstImgPath);
            if (isFirstImgPathFile.exists()) {
                isFirstImgPathFile.delete();
            }
        }
    }

    //解除锁定任务
    public void unCloseStatus(int taskId, int statusCode) {
        this.packageTaskService.updateLock(taskId, statusCode);
    }

    public static ExecResult exec(String command, File dir) throws IOException {
        ExecResult ret = new ExecResult();
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(command, null, dir);

        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = null;
        while ((line = input.readLine()) != null) {
            ret.output += line + "\n";
        }
        input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
        while ((line = input.readLine()) != null) {
            ret.errout += line + "\n";
        }
        try {
            ret.code = pr.waitFor();
        } catch (InterruptedException e) {
            ret.code = -1;
        }
        return ret;
    }

    /**
     * 执行返回的数据结构
     */
    static class ExecResult {
        //外部程序返回的错误码，0表示执行成功
        int code;
        //外部程序的标准输出stdout
        String output = "";
        //外部程序的标准错误输出stderr
        String errout = "";

        public String toString() {
            return "code:" + code + ",output:" + output + ",errout:" + errout;
        }
    }

    public void runnable(Integer[] taskList, PackageService packageService) {
        for (Integer i : taskList) {
            List<PackageInfoTask> packageInfoTaskList = this.packageInfoTaskService.listPackageInfoTask(i);
            this.execute.submit(new PackageTaskRunnable(packageInfoTaskList, packageService));
        }
    }
}
