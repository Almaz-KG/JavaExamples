package javaexamples.firstchapter;

/**
 * Created by Almaz on 02.06.2015.
 */
public class HelloWorld {
    public static final String HELLO_MESSAGE = "Hello everybody!";
    public static final String HELLO_ERROR_MESSAGE = "Ooooooops! There are something errors!";

    public static void sayHello(){
        System.out.print(HELLO_MESSAGE);
    }

    public static void sayError(){
        System.err.print(HELLO_ERROR_MESSAGE);
    }
}
