package org.coursera.algorithms.dynamicconnectivity;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Almaz on 20.06.2015.
 */
public class DynamicConnectivityUsingArrayTest {
    @Test
    public void transitivityTest1(){
        DynamicConnectivityUsingArray dc = new DynamicConnectivityUsingArray(10);
        dc.union(new String[]{"1", "2"});
        dc.union(new String[]{"2", "3"});

        Assert.assertTrue(dc.isConnected("1", "3"));
    }
    @Test
    public void transitivityTest2(){
        DynamicConnectivityUsingArray dc = new DynamicConnectivityUsingArray(10);
        dc.union(new String[]{"1", "1"});
        dc.union(new String[]{"1", "1"});

        Assert.assertTrue(dc.isConnected("1", "1"));
    }
    @Test
    public void transitivityTest3(){
        DynamicConnectivityUsingArray dc = new DynamicConnectivityUsingArray(10);
        dc.union(new String[]{"1", "2"});
        dc.union(new String[]{"3", "4"});

        Assert.assertFalse(dc.isConnected("1", "4"));
    }
    @Test
    public void reflexiveRelation(){
        DynamicConnectivityUsingArray dc = new DynamicConnectivityUsingArray(10);
        dc.union(new String[]{"1"});
        dc.union(new String[]{"1"});

        Assert.assertTrue(dc.isConnected("1", "1"));
    }
    @Test
    public void symmetricRelation(){
        DynamicConnectivityUsingArray dc = new DynamicConnectivityUsingArray(10);
        dc.union(new String[]{"1", "2"});

        Assert.assertTrue(dc.isConnected("1", "2"));
        Assert.assertTrue(dc.isConnected("2", "1"));
        Assert.assertTrue(dc.isConnected("1", "1"));
        Assert.assertTrue(dc.isConnected("2", "2"));
    }
    @Test
    public void test(){
        DynamicConnectivityUsingArray dc = new DynamicConnectivityUsingArray(10);

        Assert.assertFalse(dc.isConnected("1", "9"));
        Assert.assertFalse(dc.isConnected("9", "1"));
        dc.union(new String[]{"1", "9"});


        Assert.assertTrue(dc.isConnected("9", "1"));
        Assert.assertTrue(dc.isConnected("1", "9"));
        Assert.assertTrue(dc.isConnected("9", "9"));
        Assert.assertTrue(dc.isConnected("1", "1"));
    }


}
