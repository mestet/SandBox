package training;

import java.util.StringJoiner;

public class Fibonacci {

    private static final double SQRT_FIVE = Math.sqrt(5);
    private static final double GOLDEN_RATIO = (1 + SQRT_FIVE) / 2;

    public static void main(String[] args) {
        Fibonacci instance = new Fibonacci();

        int n = 13;
        instance.printFibonacciSequence(n);
        System.out.println(n + "-th element is: " + instance.nthFibonacciElement(n));

    }


    private long nthFibonacciElement(int n) {
        return Math.round(Math.pow(GOLDEN_RATIO, n) / SQRT_FIVE);
    }

    public void printFibonacciSequence(int n) {
        StringJoiner sj = new StringJoiner(" ", "Sequence is: ", ";");
        for (int i = 0; i <= n; i++) {
            sj.add(String.valueOf(nthFibonacciElement(i)));
        }
        System.out.println(sj);
    }
}
