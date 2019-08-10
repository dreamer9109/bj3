package com.laifeng.cpsjobs.service.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */


import com.laifeng.cpsjobs.model.cps.PackageInfo;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/8.
 */
public interface PackageInfoService {

    /**
     * 获取录入列表
     *
     * @param packageName
     * @param channelName
     * @param friendChannelName
     * @return
     */
    public List<PackageInfo> listPackageInfo(String packageName, String channelName, String friendChannelName);

    /**
     * 获取录入信息
     *
     * @param id
     * @return
     */
    public PackageInfo getPackageInfo(int id);

    /**
     * 获取基本包使用信息
     *
     * @param id
     * @return
     */
    public List<PackageInfo> getPackageInfoByIn(List<Integer> id);

    /**
     * 增加信息
     *
     * @param packageInfo
     */
    public void insertPackageInfo(PackageInfo packageInfo);

    /**
     * 修改信息
     *
     * @param packageInfo
     */
    public void updatePackageInfo(PackageInfo packageInfo);

    /**
     * 删除信息
     *
     * @param id
     */
    public void deletePackageInfo(int id);
}

