package com.laifeng.cpsjobs.dao.stat.cps;

/**
 * Created by kouzhigang on 2015/5/18.
 */

import com.laifeng.cpsjobs.model.cps.PackageInfoTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/15.
 */
public interface PackageInfoTaskMapper {
    public void insertPackageInfoTask(PackageInfoTask packageInfoTask);

    public void deletePackageInfoTask(@Param("id") int id);

    public List<PackageInfoTask> listPackageInfoTask(@Param("id") int id);

    public void updateSavePath(
            @Param("id") int id,
            @Param("savePath") String savePath
    );

    public PackageInfoTask getPackageInfoTaskById(@Param("id") int id);

    public void updateDownloadActive(@Param("id") int id, @Param("downloadActive") int downloadActive);
}
