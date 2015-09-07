package designpatterns.dao.daoimpl.mysql;

import designpatterns.dao.daoimpl.DAOFactory;
import designpatterns.dao.daoimpl.GenericDAO;
import designpatterns.dao.entities.Group;
import designpatterns.dao.entities.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Almaz on 07.09.2015.
 */
public class MysqlDAOFactory implements DAOFactory {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Almaz";
    private static final String URL = "jdbc:mysql://localhost:3306/daotalk";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";


    public MysqlDAOFactory() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    @Override
    public GenericDAO<Group> getGroupDAO(Connection connection) {
        return new MysqlGroupDAO(connection);
    }

    @Override
    public GenericDAO<Student> getStudentDAO(Connection connection) {
        return null;
    }
}
