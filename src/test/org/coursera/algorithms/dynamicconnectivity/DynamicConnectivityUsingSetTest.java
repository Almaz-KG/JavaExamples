package org.coursera.algorithms.dynamicconnectivity;

import org.coursera.algorithms.dynamicconnectivity.DynamicConnectivityUsingSet;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Almaz on 20.06.2015.
 */
public class DynamicConnectivityUsingSetTest {
    @Test
    public void transitivityTest1(){
        DynamicConnectivityUsingSet dc = new DynamicConnectivityUsingSet();
        dc.union(new String[]{"1", "2"});
        dc.union(new String[]{"2", "3"});

        Assert.assertTrue(dc.isConnected("1", "3"));
    }
    @Test
    public void transitivityTest2(){
        DynamicConnectivityUsingSet dc = new DynamicConnectivityUsingSet();
        dc.union(new String[]{"1", "1"});
        dc.union(new String[]{"1", "1"});

        Assert.assertTrue(dc.isConnected("1", "1"));
    }
    @Test
    public void transitivityTest3(){
        DynamicConnectivityUsingSet dc = new DynamicConnectivityUsingSet();
        dc.union(new String[]{"1", "2"});
        dc.union(new String[]{"3", "4"});

        Assert.assertFalse(dc.isConnected("1", "4"));
    }
    @Test
    public void reflexiveRelation(){
        DynamicConnectivityUsingSet dc = new DynamicConnectivityUsingSet();
        dc.union(new String[]{"1"});
        dc.union(new String[]{"1"});

        Assert.assertEquals(1, dc.count());
    }
    @Test
    public void symmetricRelation(){
        DynamicConnectivityUsingSet dc = new DynamicConnectivityUsingSet();
        dc.union(new String[]{"1", "2"});

        Assert.assertTrue(dc.isConnected("1", "2"));
        Assert.assertTrue(dc.isConnected("2", "1"));
        Assert.assertTrue(dc.isConnected("1", "1"));
        Assert.assertTrue(dc.isConnected("2", "2"));
    }



}
