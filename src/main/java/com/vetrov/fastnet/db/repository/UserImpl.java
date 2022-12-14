package com.vetrov.fastnet.db.repository;

import com.vetrov.fastnet.db.builders.QueryBuilder;
import com.vetrov.fastnet.db.builders.TariffQueryBuilder;
import com.vetrov.fastnet.db.builders.UserQueryBuilder;
import com.vetrov.fastnet.db.entity.Tariff;
import com.vetrov.fastnet.db.entity.User;
import org.apache.log4j.Logger;

import java.util.List;


public class UserImpl implements IUser {
    private static final String GET_ALL = "SELECT * FROM internet_provider.users";
    private static final String GET_BY_ID = "SELECT id, login, password, first_name, last_name, surname, blocked, roles_id, contact_details_id, accounts_id FROM internet_provider.users WHERE id = ?";
    private static final String GET_BY_LOGIN = "SELECT id, login, password, first_name, last_name, surname, blocked, roles_id, contact_details_id, accounts_id FROM internet_provider.users WHERE login = ?";
    private static final String CREATE = "INSERT INTO internet_provider.users (login, password, first_name, last_name, surname, blocked, roles_id, contact_details_id, accounts_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE internet_provider.users SET login = ?, password = ?, first_name = ?, last_name = ?, surname = ?, blocked = ?, roles_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM internet_provider.users WHERE id = ?";

    private static final String ADD_LINK_USERS_HAS_TRAFFICS = "INSERT INTO internet_provider.users_has_tariffs (users_id, tariffs_id) VALUES (?, ?)";
    private static final String GET_LINK_USERS_HAS_TRAFFICS = "SELECT t.id, t.name, t.description, t.price, t.services_id FROM tariffs AS t JOIN users_has_tariffs AS uht ON t.id = uht.tariffs_id AND uht.users_id = (SELECT id FROM users WHERE id = ?)";
    private static final String DELETE_LINK_USERS_HAS_TRAFFICS = "DELETE FROM internet_provider.users_has_tariffs WHERE users_id = ?";

    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'internet_provider' AND TABLE_NAME = 'users'";

    private final DBManager instance = DBManager.getInstance();
    private static final Logger log = Logger.getLogger(TariffImpl.class);
    private final QueryBuilder<User> queryBuilder = new UserQueryBuilder();

    @Override
    public List<User> getAll() {
        return queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public User getById(long id) {
        return queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(User user) {
        long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        queryBuilder.execute(instance, CREATE, user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getSurname(), user.isBlocked(), user.getRoleId(), id, id);
        log.info("User was created");
    }

    @Override
    public void update(User user) {
        queryBuilder.execute(instance, UPDATE, user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getSurname(), user.isBlocked(), user.getRoleId(), user.getId());
        log.info("User was updated");
    }

    @Override
    public void delete(long id) {
        queryBuilder.execute(instance, DELETE, id);
        log.info("User was deleted");
    }

    @Override
    public User getByLogin(String login) {
        return queryBuilder.executeAndReturn(instance, GET_BY_LOGIN, login);
    }

    @Override
    public List<Tariff> getTariffs(User user) {
        QueryBuilder<Tariff> queryBuilder = new TariffQueryBuilder();
        return queryBuilder.executeAndReturnList(instance, GET_LINK_USERS_HAS_TRAFFICS, user.getId());
    }

    @Override
    public void addLinksUsersHasTariffs(User user, String[] tariffsId) {
        User tmp = getByLogin(user.getLogin());
        QueryBuilder<Tariff> queryBuilder = new TariffQueryBuilder();
        for (String id : tariffsId) {
            queryBuilder.execute(instance, ADD_LINK_USERS_HAS_TRAFFICS, tmp.getId(), id);
        }
    }

    @Override
    public void deleteLinksUsersHasTariffs(User user) {
        queryBuilder.execute(instance, DELETE_LINK_USERS_HAS_TRAFFICS, user.getId());
    }
}
