package main.examples.chapter_1;

/**
 * Created by almu0214 on 04.06.2015.
 */
public class PrimeNumber {
    public static int getMaxPrimeNumber(int max) {
        if(max <= 0)
            throw new IllegalArgumentException("Oooooops!");

        return isPrime(max) ? max : getMaxPrimeNumber(max - 1);
    }
    public static boolean isPrime(int n) {
        if(n == 2)
            return true;

        if(n % 2 == 0)
            return false;

        for(int i = 3; i * i <= n; i+=2) {
            if(n % i == 0)
                return false;
        }
        return true;
    }
}
