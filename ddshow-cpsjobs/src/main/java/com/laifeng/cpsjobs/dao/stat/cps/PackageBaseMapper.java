package com.laifeng.cpsjobs.dao.stat.cps;

import com.laifeng.cpsjobs.model.cps.PackageBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/20.
 */
public interface PackageBaseMapper {
    public List<PackageBase> listPackageBase(
            @Param("offsite") int offsite,
            @Param("length") int length
    );

    public void updateStatus(@Param("id") int id);

    public void insertPackageBase(PackageBase packageBase);

    public int countPackageBase();
}
