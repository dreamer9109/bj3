package com.laifeng.cpsjobs.handler;

import com.laifeng.cpsjobs.service.TestService;
import com.laifeng.kafka.client.exception.KafkaLFException;
import com.laifeng.kafka.client.handler.LFMessageHandler;
import com.laifeng.kafka.client.message.JsonMapLFMessage;
import com.laifeng.kafka.client.message.LFMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhengcunwen on 2015/6/26.
 *增加功能dev
 */
public class TestHandler implements LFMessageHandler {

    @Autowired
    private TestService testService;

    public void execute(){

        System.out.println("--------------" + testService.getListCount());
    }

    @Override
    public void onMessage(LFMessage message) throws KafkaLFException {
        if (message instanceof JsonMapLFMessage) {
            JsonMapLFMessage obj = (JsonMapLFMessage)message;
            int jobs = obj.getInt("jobId");

            System.out.println("------------jobs:" + jobs);

        }
    }
}
