package com.vetrov.fastnet.db.repository;

import com.vetrov.fastnet.db.builders.ContactDetailsQueryBuilder;
import com.vetrov.fastnet.db.builders.QueryBuilder;
import com.vetrov.fastnet.db.entity.ContactDetails;

import java.util.List;


public class ContactDetailsImpl implements IContactDetails {
    private static final String CREATE = "INSERT INTO internet_provider.contact_details (id, city, street, home, apartment, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM internet_provider.contact_details";
    private static final String GET_BY_ID = "SELECT id, city, street, home, apartment, email, phone FROM internet_provider.contact_details WHERE id = ?";
    private static final String UPDATE = "UPDATE internet_provider.contact_details SET email = ?, phone = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM internet_provider.contact_details WHERE id = ?";
    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'internet_provider' AND TABLE_NAME = 'users'";

    private final DBManager instance = DBManager.getInstance();
    private final QueryBuilder<ContactDetails> queryBuilder = new ContactDetailsQueryBuilder();

    @Override
    public List<ContactDetails> getAll() {
        return this.queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public ContactDetails getById(long id) {
        return this.queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(ContactDetails details) {
        long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        queryBuilder.execute(instance, CREATE, id, details.getCity(), details.getStreet(), details.getHome(), details.getApartment(), details.getEmail(), details.getPhone());
    }

    @Override
    public void update(ContactDetails details) {
        this.queryBuilder.execute(instance, UPDATE, details.getEmail(), details.getPhone(), details.getId());
    }

    @Override
    public void delete(long id) {
        this.queryBuilder.execute(instance, DELETE, id);
    }
}
