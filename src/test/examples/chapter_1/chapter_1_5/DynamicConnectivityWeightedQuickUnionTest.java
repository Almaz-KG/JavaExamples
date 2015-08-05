package examples.chapter_1.chapter_1_5;

import examples.chapter_1.chapter_1_5.almaz.DynamicConnectivity;
import examples.chapter_1.chapter_1_5.almaz.DynamicConnectivityWeightedQuickUnion;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Almaz on 20.06.2015.
 */
public class DynamicConnectivityWeightedQuickUnionTest {
    @Test
    public void transitivityTest1(){
        DynamicConnectivity dc = new DynamicConnectivityWeightedQuickUnion(10);
        dc.union(new String[]{"1", "2"});
        dc.union(new String[]{"2", "3"});

        Assert.assertTrue(dc.isConnected("1", "3"));
    }
    @Test
    public void transitivityTest2(){
        DynamicConnectivity dc = new DynamicConnectivityWeightedQuickUnion(10);
        dc.union(new String[]{"1", "1"});
        dc.union(new String[]{"1", "1"});

        Assert.assertTrue(dc.isConnected("1", "1"));
    }
    @Test
    public void transitivityTest3(){
        DynamicConnectivity dc = new DynamicConnectivityWeightedQuickUnion(10);
        dc.union(new String[]{"1", "2"});
        dc.union(new String[]{"3", "4"});

        Assert.assertFalse(dc.isConnected("1", "4"));
    }
    @Test
    public void reflexiveRelation(){
        DynamicConnectivity dc = new DynamicConnectivityWeightedQuickUnion(10);
        dc.union(new String[]{"1"});
        dc.union(new String[]{"1"});

        Assert.assertTrue(dc.isConnected("1", "1"));
    }
    @Test
    public void symmetricRelation(){
        DynamicConnectivity dc = new DynamicConnectivityWeightedQuickUnion(10);
        dc.union(new String[]{"1", "2"});

        Assert.assertTrue(dc.isConnected("1", "2"));
        Assert.assertTrue(dc.isConnected("2", "1"));
        Assert.assertTrue(dc.isConnected("1", "1"));
        Assert.assertTrue(dc.isConnected("2", "2"));
    }


}
