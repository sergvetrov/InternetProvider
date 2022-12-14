package com.vetrov.fastnet.db.builders;

import com.vetrov.fastnet.db.entity.Tariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TariffQueryBuilder extends QueryBuilder<Tariff> {
    @Override
    public List<Tariff> getListOfResult(ResultSet rs) throws SQLException {
        List<Tariff> tariffs = new ArrayList<>();
        while (rs.next()) {
            Tariff tariff = new Tariff();
            setTariffInfo(rs, tariff);
            tariffs.add(tariff);
        }
        return tariffs;
    }

    @Override
    public Tariff getResult(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        while (rs.next()) {
            setTariffInfo(rs, tariff);
        }
        return tariff;
    }

    private void setTariffInfo(ResultSet rs, Tariff tariff) throws SQLException {
        tariff.setId(rs.getLong("id"));
        tariff.setName(rs.getString("name"));
        tariff.setDescription(rs.getString("description"));
        tariff.setPrice(rs.getDouble("price"));
        tariff.setServiceId(rs.getLong("services_id"));
    }
}
