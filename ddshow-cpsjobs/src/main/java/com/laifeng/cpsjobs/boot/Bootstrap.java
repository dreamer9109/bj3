package com.laifeng.cpsjobs.boot;

import com.laifeng.cpsjobs.utils.Profiles;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;


public class Bootstrap {

	private static Log logger = LogFactory.getLog(Bootstrap.class);

	public static void main(String[] args) {
		logger.info("spring cpsjobs starting...");

		// 检查Spring Profile
		Profiles.checkProfile();

		new GenericXmlApplicationContext("classpath:applicationContext-*.xml");

        logger.info("cpsjobs working...");

        // TODO 是否加入健康检测
	}
}
