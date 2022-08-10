package com.vetrov.fastnet.db.services;

import com.vetrov.fastnet.db.entity.Tariff;
import com.vetrov.fastnet.db.repository.ITariff;
import com.vetrov.fastnet.db.repository.TariffImpl;

import java.util.List;


public class TariffServiceImpl implements ITariffService {
    private final ITariff repo = new TariffImpl();

    @Override
    public List<Tariff> findAll() {
        return this.repo.getAll();
    }

    @Override
    public Tariff find(long id) {
        return this.repo.getById(id);
    }

    @Override
    public Tariff find(String name) {
        return this.repo.getByName(name);
    }

    @Override
    public List<Tariff> findAllById(long id) {
        return this.repo.getAllByServiceId(id);
    }

    @Override
    public void save(Tariff tariff) {
        this.repo.create(tariff);
    }

    @Override
    public void update(Tariff tariff) {
        this.repo.update(tariff);
    }

    @Override
    public void remove(long id) {
        this.repo.delete(id);
    }
}
