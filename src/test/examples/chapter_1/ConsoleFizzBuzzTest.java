package examples.chapter_1.fizz_buzz;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import examples.chapter_1.FizzBuzzExample;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Almaz on 02.06.2015.
 */
public class ConsoleFizzBuzzTest {
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
    public void sayFizz(){
        int size = 20;
        FizzBuzzExample.sayFizz(size);
        String fizz = "fizzfizzfizzfizz";

        Assert.assertEquals(fizz, outContent.toString());
    }
    @Test
    public void sayFizzException(){
        boolean thrown = false;

        try {
            int size = -10;
            FizzBuzzExample.sayFizz(size);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        Assert.assertTrue(thrown);
    }
    @Test
    public void sayBuzz(){
        int size = 22;
        FizzBuzzExample.sayBuzz(size);
        String buzz = "buzzbuzzbuzz";

        Assert.assertEquals(buzz, outContent.toString());
    }
    @Test
    public void sayBuzzException(){
        boolean thrown = false;

        try {
            int size = -10;
            FizzBuzzExample.sayBuzz(size);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }
    @Test
    public void sayFizzBuzz(){
        int size = 36;
        FizzBuzzExample.sayFizzBuzz(size);
        String fizzBuzz = "fizzbuzzfizzbuzzfizzfizzbuzzfizzbuzzfizzfizzbuzz";

        Assert.assertEquals(fizzBuzz, outContent.toString());
    }
    @Test
    public void sayFizzBuzzException(){
        boolean thrown = false;

        try{
            FizzBuzzExample.sayFizzBuzz(-10);
        } catch (IllegalArgumentException e){
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }

}
