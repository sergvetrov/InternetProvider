package com.vetrov.fastnet.db.services;

import com.vetrov.fastnet.db.entity.PackageServices;

import java.util.List;

public interface IPackageService {

    List<PackageServices> findAll();

    PackageServices find(long id);

    void save(PackageServices service);

    void update(PackageServices service);

    void remove(long id);
}
