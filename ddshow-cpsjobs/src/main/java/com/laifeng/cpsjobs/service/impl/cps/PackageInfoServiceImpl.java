package com.laifeng.cpsjobs.service.impl.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */

import com.laifeng.cpsjobs.dao.stat.cps.PackageInfoMapper;
import com.laifeng.cpsjobs.model.cps.PackageInfo;
import com.laifeng.cpsjobs.service.cps.PackageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/8.
 */
@Service
public class PackageInfoServiceImpl implements PackageInfoService {
    @Autowired
    private PackageInfoMapper packageInfoMapper;

    /**
     * 获取录入信息列表
     *
     * @param packageName
     * @param channelName
     * @param friendChannelName
     * @return
     */
    @Override
    public List<PackageInfo> listPackageInfo(String packageName, String channelName, String friendChannelName) {
        return this.packageInfoMapper.listPackageInfo(packageName, channelName, friendChannelName);
    }

    /**
     * 获取录入信息
     *
     * @param id
     * @return
     */
    @Override
    public PackageInfo getPackageInfo(int id) {
        return this.packageInfoMapper.getPackageInfo(id);
    }

    /**
     * 获取基本包使用信息
     *
     * @param id
     * @return
     */
    public List<PackageInfo> getPackageInfoByIn(List<Integer> id) {
        return this.packageInfoMapper.getPackageInfoByIn(id);
    }

    /**
     * 增加信息
     *
     * @param packageInfo
     */
    @Override
    public void insertPackageInfo(PackageInfo packageInfo) {
        this.packageInfoMapper.insertPackageInfo(packageInfo);
    }

    /**
     * 更新信息
     *
     * @param packageInfo
     */
    @Override
    public void updatePackageInfo(PackageInfo packageInfo) {
        this.packageInfoMapper.updatePackageInfo(packageInfo);
    }

    /**
     * 删除信息
     *
     * @param id
     */
    @Override
    public void deletePackageInfo(int id) {
        this.packageInfoMapper.deletePackageInfo(id);
    }
}

