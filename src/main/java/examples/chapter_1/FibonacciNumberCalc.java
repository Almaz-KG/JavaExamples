package examples.chapter_1;

/**
 * Created by almu0214 on 03.06.2015.
 */
public class FibonacciNumberCalc {
    public static void showNumbers(int count) {
        for (int i = 1; i < count; i++)
            System.out.print(fibonacciRecusion(i) + ",");
        System.out.print(fibonacciRecusion(count));
    }

    public static int fibonacciRecusion(int number) {
        if(number <= 0)
            throw new IllegalArgumentException("Ooooooooops! Argument is negative");
        if (number == 1 || number == 2) {
            return 1;
        }

        return fibonacciRecusion(number - 1) + fibonacciRecusion(number - 2);
    }
}
