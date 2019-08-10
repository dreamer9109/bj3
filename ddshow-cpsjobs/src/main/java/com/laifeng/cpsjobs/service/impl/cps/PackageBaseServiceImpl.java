package com.laifeng.cpsjobs.service.impl.cps;

import com.laifeng.cpsjobs.dao.stat.cps.PackageBaseMapper;
import com.laifeng.cpsjobs.model.cps.PackageBase;
import com.laifeng.cpsjobs.service.cps.PackageBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kouzhigang on 2015/5/20.
 */
@Service
public class PackageBaseServiceImpl implements PackageBaseService {
    @Autowired
    private PackageBaseMapper packageBaseMapper;

    @Override
    public List<PackageBase> listPackageBase(int offsite, int length) {
        return this.packageBaseMapper.listPackageBase(offsite, length);
    }

    @Override
    public void updateStatus(int id) {
        this.packageBaseMapper.updateStatus(id);
    }

    @Override
    public void insertPackageBase(PackageBase packageBase) {
        this.packageBaseMapper.insertPackageBase(packageBase);
    }

    @Override
    public int countPackageBase() {
        return this.packageBaseMapper.countPackageBase();
    }
}
