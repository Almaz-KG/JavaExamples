package javaexamples.firstchapter;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by almu0214 on 04.06.2015.
 */
public class PrimeNumberTest {
    @Test
    public void testPrimeNumber(){
        Assert.assertEquals(97, PrimeNumber.getMaxPrimeNumber(100));
        Assert.assertEquals(103, PrimeNumber.getMaxPrimeNumber(103));
        Assert.assertEquals(2, PrimeNumber.getMaxPrimeNumber(2));
        Assert.assertEquals(1, PrimeNumber.getMaxPrimeNumber(1));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPrimeNumberThrowException(){
        PrimeNumber.getMaxPrimeNumber(0);
        PrimeNumber.getMaxPrimeNumber(-256);
        PrimeNumber.getMaxPrimeNumber(-10);
    }
}
