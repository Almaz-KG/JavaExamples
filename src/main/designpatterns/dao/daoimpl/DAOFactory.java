package designpatterns.dao.daoimpl;

import designpatterns.dao.entities.Group;
import designpatterns.dao.entities.Student;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Almaz on 07.09.2015.
 */
public interface DAOFactory {
    Connection getConnection() throws SQLException;
    GenericDAO<Group> getGroupDAO(Connection connection);
    GenericDAO<Student> getStudentDAO(Connection connection);
}
