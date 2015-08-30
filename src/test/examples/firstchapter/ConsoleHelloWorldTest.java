package examples.firstchapter.hello_world;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import examples.firstchapter.HelloWorld;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by Almaz on 02.06.2015.
 */
public class ConsoleHelloWorldTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    @Before
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanStreams(){
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void sayHello(){
        HelloWorld.sayHello();
        Assert.assertEquals(HelloWorld.HELLO_MESSAGE, outContent.toString());
    }

    @Test
    public void sayError(){
        HelloWorld.sayError();
        Assert.assertEquals(HelloWorld.HELLO_ERROR_MESSAGE, errContent.toString());
    }
}
