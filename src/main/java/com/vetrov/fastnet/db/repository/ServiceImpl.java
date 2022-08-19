package com.vetrov.fastnet.db.repository;

import com.vetrov.fastnet.db.builders.QueryBuilder;
import com.vetrov.fastnet.db.builders.ServiceQueryBuilder;
import com.vetrov.fastnet.db.entity.PackageServices;
import org.apache.log4j.Logger;

import java.util.List;


public class ServiceImpl implements IService {
    private static final String GET_ALL = "SELECT * FROM internet_provider.services";
    private static final String GET_BY_ID = "SELECT id, name, description FROM internet_provider.services WHERE id = ?";
    private static final String CREATE = "INSERT INTO internet_provider.services (name, description) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM internet_provider.services WHERE id = ?";

    private final DBManager instance = DBManager.getInstance();
    private static final Logger log = Logger.getLogger(ServiceImpl.class);
    @Override
    public List<PackageServices> getAll() {
        QueryBuilder<PackageServices> queryBuilder = new ServiceQueryBuilder();
        return queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public PackageServices getById(long id) {
        QueryBuilder<PackageServices> queryBuilder = new ServiceQueryBuilder();
        return queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(PackageServices packageServices) {
        QueryBuilder<PackageServices> queryBuilder = new ServiceQueryBuilder();
        queryBuilder.execute(instance, CREATE, packageServices.getName(), packageServices.getDescription());
        log.info("Service created");
    }

    @Override
    public void update(PackageServices packageServices) {
        log.warn("Unsupported method");
    }

    @Override
    public void delete(long id) {
        QueryBuilder<PackageServices> queryBuilder = new ServiceQueryBuilder();
        queryBuilder.execute(instance, DELETE, id);
        log.info("Service deleted");
    }
}
