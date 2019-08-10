package com.laifeng.cpsjobs.service.impl.cps;

import com.laifeng.cpsjobs.model.cps.PackageInfoTask;
import com.laifeng.cpsjobs.service.cps.PackageInfoTaskService;
import com.laifeng.cpsjobs.service.cps.PackageUploadDownloadUrlService;
import com.laifeng.fs.mogile.model.LaifengFileSystemException;
import com.laifeng.fs.mogile.model.MogileException;
import com.laifeng.fs.mogile.service.LaifengFileSystemService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.util.Properties;

/**
 * Created by kouzhigang on 2015/9/28.
 */
public class PackageUploadDownloadUrlServiceImpl implements PackageUploadDownloadUrlService {

    private final static String IMG_SERVICE = "package/";
    private Log logger = LogFactory.getLog(PackageServiceImpl.class);

    @Autowired
    @Qualifier("configProperties")
    private Properties configProperties;

    @Autowired
    private PackageInfoTaskService packageInfoTaskService;

    @Autowired
    private LaifengFileSystemService laifengFileSystemService;

    @Override
    public void uploadDownloadUrl(int taskId) {
        PackageInfoTask packageInfoTask = packageInfoTaskService.getPackageInfoTaskById(taskId);

        String saveUrl = packageInfoTask.getSavePath();

        String downloadUrl = packageInfoTask.getDownloadUrl();

        String downloadUrlReal = downloadUrl.replace("http://package.laifeng.com/package/", "");

        if (StringUtils.isNotBlank(saveUrl)) {
            //下载apk包存放路径
            String savePath = this.configProperties.getProperty("package.saveDir");
            if (savePath == null) {
                logger.error("package.saveDir:null");
            }

            File savePathReal = new File(savePath);
            if (!savePathReal.exists()) {
                savePathReal.mkdirs();
            }

            //下载基本包
            String[] apkSourcePathArr = saveUrl.split("/");
            String apkSourceName = apkSourcePathArr[apkSourcePathArr.length - 1];

            String apkPathDownload = null;

            try {
                apkPathDownload = this.laifengFileSystemService.saveFile(saveUrl, savePath + "/" + apkSourceName);
                logger.info("download package:" + apkPathDownload);
            } catch (LaifengFileSystemException e) {
                logger.error(saveUrl + "download load error", e);
            }

            //上传基本包
            String uploadDownUrl = null;
            try {
                uploadDownUrl = this.laifengFileSystemService.uploadFileNoDate(apkPathDownload, downloadUrlReal);
                logger.info("upload package:" + uploadDownUrl);

                //删除下载包的临时文件
                File apkPathDownloadFile = new File(apkPathDownload);
                if (apkPathDownloadFile.exists()) {
                    apkPathDownloadFile.delete();
                }

                //更新任务打包状态
                if (StringUtils.isNotBlank(uploadDownUrl)) {
                    this.packageInfoTaskService.updateDownloadActive(taskId, 1);
                }
                logger.info("update download active success!");
            } catch (LaifengFileSystemException e) {
                logger.error(uploadDownUrl + "upload load LFS error", e);
            } catch (MogileException e) {
                logger.error(uploadDownUrl + "upload load MFS error", e);
            } catch (Exception e) {
                logger.error(uploadDownUrl + "upload load error", e);
            }
        }
    }
}
