package com.vetrov.fastnet.db.repository;

import com.vetrov.fastnet.db.builders.QueryBuilder;
import com.vetrov.fastnet.db.builders.ServiceQueryBuilder;
import com.vetrov.fastnet.db.entity.PackageServices;

import java.util.List;


public class ServiceImpl implements IService {
    private static final String GET_ALL = "SELECT * FROM internet_provider.services";
    private static final String GET_BY_ID = "SELECT id, name, description FROM internet_provider.services WHERE id = ?";
    private static final String CREATE = "INSERT INTO internet_provider.services (name, description) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE internet_provider.services SET name = ?, description = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM internet_provider.services WHERE id = ?";

    private DBManager instance = DBManager.getInstance();

    @Override
    public List<PackageServices> getAll() {
        QueryBuilder queryBuilder = new ServiceQueryBuilder();
        return queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public PackageServices getById(long id) {
        QueryBuilder queryBuilder = new ServiceQueryBuilder();
        return (PackageServices) queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(PackageServices packageServices) {
        QueryBuilder queryBuilder = new ServiceQueryBuilder();
        queryBuilder.execute(instance, CREATE, packageServices.getName(), packageServices.getDescription());
    }

    @Override
    public void update(PackageServices packageServices) {

    }

    @Override
    public void delete(long id) {
        QueryBuilder queryBuilder = new ServiceQueryBuilder();
        queryBuilder.execute(instance, DELETE, id);
    }
}
