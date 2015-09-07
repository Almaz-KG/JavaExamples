package designpatterns.dao.daoimpl.mysql;

import designpatterns.dao.daoimpl.GenericDAO;
import designpatterns.dao.entities.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almaz on 07.09.2015.
 */
public class MysqlGroupDAO implements GenericDAO<Group> {
    private Connection connection;

    public MysqlGroupDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Group create() throws SQLException{
       return null;
    }

    @Override
    public Group persist(Group group) throws SQLException {
        return null;
    }

    @Override
    public Group getByPK(int id) throws SQLException{
        String sql = "SELECT * FROM daotalk.Group WHERE id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        rs.next();

        Group g = new Group();
        g.setId(rs.getInt("id"));
        g.setNumber(rs.getInt("number"));
        g.setDepartment(rs.getString("department"));

        return g;
    }

    @Override
    public void update(Group group) {

    }

    @Override
    public void delete(Group group) {

    }

    @Override
    public List<Group> getAll() throws SQLException{
        String sql = "SELECT * FROM daotalk.Group;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<Group> list = new ArrayList<>();
        while (rs.next()) {
            Group g = new Group();
            g.setId(rs.getInt("id"));
            g.setNumber(rs.getInt("number"));
            g.setDepartment(rs.getString("department"));
            list.add(g);
        }
        return list;
    }
}
