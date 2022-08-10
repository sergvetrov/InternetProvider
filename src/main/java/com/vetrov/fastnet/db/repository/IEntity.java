package com.vetrov.fastnet.db.repository;

import com.vetrov.fastnet.db.entity.Entity;

import java.util.List;


public interface IEntity<T extends Entity> {
    List<T> getAll();

    T getById(long id);

    void create(T t);

    void update(T t);

    void delete(long id);
}
