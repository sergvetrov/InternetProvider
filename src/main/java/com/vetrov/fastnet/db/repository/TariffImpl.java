package com.vetrov.fastnet.db.repository;

import com.vetrov.fastnet.db.builders.QueryBuilder;
import com.vetrov.fastnet.db.builders.TariffQueryBuilder;
import com.vetrov.fastnet.db.entity.Tariff;
import org.apache.log4j.Logger;

import java.util.List;


public class TariffImpl implements ITariff {
    private static final String GET_ALL = "SELECT * FROM internet_provider.tariffs";
    private static final String GET_ALL_BY_SERVICES_ID = "SELECT * FROM internet_provider.tariffs WHERE services_id = ?";
    private static final String GET_BY_ID = "SELECT id, name, description, price, services_id FROM internet_provider.tariffs WHERE id = ?";
    private static final String GET_BY_NAME = "SELECT id, name, description, price, services_id FROM internet_provider.tariffs WHERE name = ?";
    private static final String CREATE = "INSERT INTO internet_provider.tariffs (name, price, description, services_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE internet_provider.tariffs SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM internet_provider.tariffs WHERE id = ?";

    private final DBManager instance = DBManager.getInstance();
    private static final Logger log = Logger.getLogger(TariffImpl.class);
    private final QueryBuilder<Tariff> queryBuilder = new TariffQueryBuilder();

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
        return this.queryBuilder.executeAndReturn(instance, GET_BY_NAME, name);
    }

    @Override
    public Tariff getById(long id) {
        return queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(Tariff tariff) {
        queryBuilder.execute(instance, CREATE, tariff.getName(), tariff.getPrice(), tariff.getDescription(), tariff.getServiceId());
        log.info("Tariff was created");
    }

    @Override
    public void update(Tariff tariff) {
        queryBuilder.execute(instance, UPDATE, tariff.getName(), tariff.getDescription(), tariff.getPrice(), tariff.getId());
        log.info("Tariff was updated");
    }

    @Override
    public void delete(long id) {
        queryBuilder.execute(instance, DELETE, id);
        log.info("Tariff was deleted");
    }
}
