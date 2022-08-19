package com.vetrov.fastnet.db.builders;

import com.vetrov.fastnet.db.entity.PackageServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServiceQueryBuilder extends QueryBuilder<PackageServices> {
    @Override
    public List<PackageServices> getListOfResult(ResultSet rs) throws SQLException {
        List<PackageServices> services = new ArrayList<>();
        while (rs.next()) {
            PackageServices packageServices = new PackageServices();
            setPackageServicesInfo(rs, packageServices);
            services.add(packageServices);
        }
        return services;
    }

    @Override
    public PackageServices getResult(ResultSet rs) throws SQLException {
        PackageServices packageService = new PackageServices();
        while (rs.next()) {
            setPackageServicesInfo(rs, packageService);
        }
        return packageService;
    }

    private void setPackageServicesInfo(ResultSet rs, PackageServices packageServices) throws SQLException {
        packageServices.setId(rs.getLong("id"));
        packageServices.setName(rs.getString("name"));
        packageServices.setDescription(rs.getString("description"));
    }
}
