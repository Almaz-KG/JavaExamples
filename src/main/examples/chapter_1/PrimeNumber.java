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
        for(int i = 2; i < (n / 2) + 1 ; i++) {
            if(n % i == 0)
                return false;
        }
        return true;
    }
}
