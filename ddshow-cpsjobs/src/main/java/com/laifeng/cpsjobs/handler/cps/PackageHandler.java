package com.laifeng.cpsjobs.handler.cps;

import com.laifeng.cpsjobs.service.cps.PackageService;
import com.laifeng.cpsjobs.service.cps.PackageUploadDownloadUrlService;
import com.laifeng.kafka.client.exception.KafkaLFException;
import com.laifeng.kafka.client.handler.LFMessageHandler;
import com.laifeng.kafka.client.message.JsonMapLFMessage;
import com.laifeng.kafka.client.message.LFMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by kouzhigang on 2015/7/1.
 */
public class PackageHandler implements LFMessageHandler {

    @Autowired
    private PackageService packageService;

    @Autowired
    private PackageUploadDownloadUrlService packageUploadDownloadUrlService;

    private static Log logger = LogFactory.getLog(PackageHandler.class);

    @Override
    public void onMessage(LFMessage message) throws KafkaLFException {
        if (message instanceof JsonMapLFMessage) {

            logger.info("get message at " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

            JsonMapLFMessage obj = (JsonMapLFMessage) message;

            int category = obj.getInt("category");

            //如果category == 1 执行打包任务
            if (1 == category) {
                logger.info("execute package task at " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

                try {
                    String taskListString = obj.getString("dataInfo");
                    JSONArray jsonArray = JSONObject.fromObject(taskListString).getJSONObject("dataInfo").getJSONArray("taskList");

                    Integer[] taskList = new Integer[jsonArray.size()];
                    for (int i = 0; i < jsonArray.size(); i++) {
                        taskList[i] = jsonArray.getInt(i);
                    }

                    this.packageService.runnable(taskList, this.packageService);
                } catch (Exception e) {
                    logger.error("package error: {}", e);
                }
            }

            //如果category == 2 执行更新url操作
            if (2 == category) {
                logger.info("update Download Url at" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

                try {
                    String taskId = obj.getString("dataInfo");

                    int taskIdReal = Integer.parseInt(taskId);
                    this.packageUploadDownloadUrlService.uploadDownloadUrl(taskIdReal);
                    System.out.println(taskId);
                } catch (Exception e) {
                    logger.info(e);
                }
                //packageUploadDownloadUrlService.uploadDownloadUrl();
            }
        }
    }
}
