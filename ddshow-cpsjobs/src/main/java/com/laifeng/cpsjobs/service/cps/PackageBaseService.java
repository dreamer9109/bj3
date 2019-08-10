package com.laifeng.cpsjobs.service.cps;


import com.laifeng.cpsjobs.model.cps.PackageBase;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/20.
 */
public interface PackageBaseService {
    public List<PackageBase> listPackageBase(
            int offsite,
            int length
    );

    public void updateStatus(int id);

    public void insertPackageBase(PackageBase packageBase);

    public int countPackageBase();
}
