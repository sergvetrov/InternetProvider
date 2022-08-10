package com.vetrov.fastnet.db.repository;

import com.vetrov.fastnet.db.entity.Tariff;

import java.util.List;


public interface ITariff extends IEntity<Tariff> {

    List<Tariff> getAllByServiceId(long id);

    Tariff getByName(String name);
}
