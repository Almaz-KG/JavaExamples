package examples.firstchapter;

import javaexamples.firstchapter.FibonacciNumberCalc;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by almu0214 on 03.06.2015.
 */
public class FibonacciNumberTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void init(){
        System.setOut(new PrintStream(out));
    }


    @Test
    public void testExceptionZeroParameterFiboNumbers(){
        boolean thrown = false;

        try {
            FibonacciNumberCalc.showNumbers(0);
        } catch (IllegalArgumentException e){
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }
    @Test
    public void testExceptionNegativeParameterFiboNumbers(){
        boolean thrown = false;

        try {
            FibonacciNumberCalc.showNumbers(-20);
        } catch (IllegalArgumentException e){
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }

    @Test
    public void testFirstThirdFiboNumbers(){
        FibonacciNumberCalc.showNumbers(3);
        Assert.assertEquals("1,1,2", out.toString());
    }
    @Test
    public void testFirstAndSecondFiboNumbers(){
        FibonacciNumberCalc.showNumbers(2);
        Assert.assertEquals("1,1", out.toString());
    }
    @Test
    public void testFirstFiboNumber(){
        FibonacciNumberCalc.showNumbers(1);
        Assert.assertEquals("1", out.toString());
    }
    @Test
    public void testFirst10FiboNumbers(){
        FibonacciNumberCalc.showNumbers(20);
        Assert.assertEquals("1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765", out.toString());
    }

    @After
    public void deinit(){
        System.setOut(null);
    }

}
