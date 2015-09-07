package designpatterns.dao.daoimpl;

import designpatterns.dao.entities.Group;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Almaz on 07.09.2015.
 */
public interface GenericDAO<T> {
    T create() throws SQLException;

    T persist(T t) throws SQLException;

    T getByPK(int id) throws SQLException;

    void update(T t);

    void delete(T t);

    List<T> getAll() throws SQLException;
}
