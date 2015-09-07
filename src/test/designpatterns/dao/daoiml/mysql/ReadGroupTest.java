package designpatterns.dao.daoiml.mysql;

import designpatterns.dao.daoimpl.DAOFactory;
import designpatterns.dao.daoimpl.GroupDAO;
import designpatterns.dao.daoimpl.mysql.MysqlDAOFactory;
import designpatterns.dao.entities.Group;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Almaz on 07.09.2015.
 */
public class ReadGroupTest {
    @Test
    public void simpleTest() throws Exception{
        DAOFactory factory = new MysqlDAOFactory();
        List<Group> groupList;

        try(Connection connection = factory.getConnection()){
            GroupDAO dao = factory.getGroupDAO(connection);
            groupList = dao.getAll();
        }

        Assert.assertNotNull(groupList);
        Assert.assertTrue(groupList.size() > 0);
    }
}
