package com.laifeng.cpsjobs.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;

import java.util.Set;

/**
 * User: Xun Date: 13-8-15 Time: 下午5:57
 */
public class Profiles {

    public static final String PROFILE_PARAM_KEY = "spring.profiles.active";

    public static final String DEV = "dev";

    public static final String TEST = "test|test_1|test_2|test_3|test_4|test_5|test_6|test_7|test_8|test_9|test_10";

    public static final String PRE_DEPLOY = "predeploy";

    public static final Set<String> testProfiles = Sets.newHashSet(Splitter.on("|").splitToList(TEST));

    public static final String ONLINE = "online";

    public static final String ONLINE_STAT = "online-stat";

    public static final String ERROR_MESSAGE = String.format("ERROR: Invalid profile! Please specify a profile use -D%s param. Dev profile use %s, Test profile use %s, Predeploy profile use %s, Online profile use %s, Online-stat profile use %s. eg. -D%s=%s.", PROFILE_PARAM_KEY, DEV, TEST, PRE_DEPLOY, ONLINE, PROFILE_PARAM_KEY, ONLINE, ONLINE_STAT);

    public static boolean isValid(String profile) {
        if (StringUtils.equals(ONLINE, profile)) {
            return true;
        }

        if (StringUtils.equals(ONLINE_STAT, profile)) {
            return true;
        }

        if (StringUtils.equals(PRE_DEPLOY, profile)) {
            return true;
        }

        if (testProfiles.contains(profile)) {
            return true;
        }

        if (StringUtils.equals(DEV, profile)) {
            return true;
        }

        return false;
    }

    public static void checkProfile() {
        String activeProfiles = System.getProperty(Profiles.PROFILE_PARAM_KEY);

        if (!Profiles.isValid(activeProfiles)) {
            System.err.println(Profiles.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

}
