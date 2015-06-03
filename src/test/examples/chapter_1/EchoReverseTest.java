package examples.chapter_1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by almu0214 on 03.06.2015.
 */
public class EchoReverseTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void setUp(){
        System.setOut(new PrintStream(out));
    }
    @After
    public void clean(){
        System.setOut(null);
    }


    @Test
    public void testEchoReverseWord(){
        String word = "Word";
        EchoReverse.reverse(word);

        Assert.assertEquals("droW", out.toString());
    }
    @Test
    public void testNPEException(){
        boolean thrown = false;
        try {
            EchoReverse.reverse(null);
        } catch (NullPointerException e){
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }
    @Test
    public void testEchoReverseEmptyWord(){
        String word = "";
        EchoReverse.reverse(word);

        Assert.assertEquals("", out.toString());
    }
    @Test
    public void testEchoReverseOneSymbolWord(){
        String word = "W";
        EchoReverse.reverse(word);

        Assert.assertEquals("W", out.toString());
    }
}
