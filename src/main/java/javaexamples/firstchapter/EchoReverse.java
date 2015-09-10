package javaexamples.firstchapter;

/**
 * Created by almu0214 on 03.06.2015.
 */
public class EchoReverse {
    public static void reverse(String s){
        if(s == null)
            throw new NullPointerException("Oooooops!!! This is NPE Exception");
        System.out.print(new StringBuilder(s).reverse().toString());
        return;
    }
}
