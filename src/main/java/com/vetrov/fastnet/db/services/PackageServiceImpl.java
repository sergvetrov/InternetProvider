package com.vetrov.fastnet.db.services;

import com.vetrov.fastnet.db.entity.PackageServices;
import com.vetrov.fastnet.db.repository.IService;
import com.vetrov.fastnet.db.repository.ServiceImpl;

import java.util.List;


public class PackageServiceImpl implements IPackageService {
    private final IService repo = new ServiceImpl();

    @Override
    public List<PackageServices> findAll() {
        return this.repo.getAll();
    }

    @Override
    public PackageServices find(long id) {
        return this.repo.getById(id);
    }

    @Override
    public void save(PackageServices service) {
        this.repo.create(service);
    }

    @Override
    public void update(PackageServices service) {
        this.repo.update(service);
    }

    @Override
    public void remove(long id) {
        this.repo.delete(id);
    }
}
