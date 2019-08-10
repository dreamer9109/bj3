package com.laifeng.cpsjobs.dao.stat.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */

import com.laifeng.cpsjobs.model.cps.PackageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/8.
 * package Info Mapper
 */
public interface PackageInfoMapper {

    /**
     * 获取录入信息列表
     *
     * @param packageName
     * @param channelNmae
     * @param friendChannelName
     * @return
     */
    public List<PackageInfo> listPackageInfo(
            @Param("packageName") String packageName,
            @Param("channelName") String channelNmae,
            @Param("friendChannelName") String friendChannelName
    );

    /**
     * 获取录入信息
     *
     * @param id
     * @return
     */
    public PackageInfo getPackageInfo(@Param("id") int id);

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
    public void deletePackageInfo(@Param("id") int id);
}
