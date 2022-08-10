package com.vetrov.fastnet.db.repository;

import com.vetrov.fastnet.db.builders.QueryBuilder;
import com.vetrov.fastnet.db.builders.TariffQueryBuilder;
import com.vetrov.fastnet.db.entity.Tariff;

import java.util.List;


public class TariffImpl implements ITariff {
    private static final String GET_ALL = "SELECT * FROM provider.tariffs";
    private static final String GET_ALL_BY_SERVICES_ID = "SELECT * FROM provider.tariffs WHERE services_id = ?";
    private static final String GET_BY_ID = "SELECT id, name, description, price, services_id FROM provider.tariffs WHERE id = ?";
    private static final String GET_BY_NAME = "SELECT id, name, description, price, services_id FROM provider.tariffs WHERE name = ?";
    private static final String CREATE = "INSERT INTO provider.tariffs (name, price, description, services_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE provider.tariffs SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM provider.tariffs WHERE id = ?";

    private DBManager instance = DBManager.getInstance();
    private QueryBuilder queryBuilder = new TariffQueryBuilder();

    @Override
    public List<Tariff> getAll() {
        return queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public List<Tariff> getAllByServiceId(long id) {
        return this.queryBuilder.executeAndReturnList(instance, GET_ALL_BY_SERVICES_ID, id);
    }

    @Override
    public Tariff getByName(String name) {
        return (Tariff) this.queryBuilder.executeAndReturn(instance, GET_BY_NAME, name);
    }

    @Override
    public Tariff getById(long id) {
        return (Tariff) queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(Tariff tariff) {
        queryBuilder.execute(instance, CREATE, tariff.getName(), tariff.getPrice(), tariff.getDescription(), tariff.getServiceId());
    }

    @Override
    public void update(Tariff tariff) {
        queryBuilder.execute(instance, UPDATE, tariff.getName(), tariff.getDescription(), tariff.getPrice(), tariff.getId());
    }

    @Override
    public void delete(long id) {
        queryBuilder.execute(instance, DELETE, id);
    }
}
