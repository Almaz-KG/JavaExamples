package examples.chapter_1;

/**
 * Created by Almaz on 02.06.2015.
 */
public class FizzBuzzExample {

    public static void sayFizz(int size){
        if(size < 0)
            throw new IllegalArgumentException("Ooooops! Something wrong");

        for(int i = 1; i <= size; i ++){
            if(i % 5 == 0)
                System.out.print("fizz");
        }
    }
    public static void sayBuzz(int size){
        if(size < 0)
            throw new IllegalArgumentException("Ooooops! Something wrong");

        for(int i = 1; i <= size; i ++){
            if(i % 7 == 0)
                System.out.print("buzz");
        }
    }
    public static void sayFizzBuzz(int size){
        if(size < 0)
            throw new IllegalArgumentException("Ooooops! Something wrong");

        for(int i = 1; i <= size; i ++) {
            if (i % 5 == 0)
                System.out.print("fizz");
            if (i % 7 == 0)
                System.out.print("buzz");
        }
    }
}
