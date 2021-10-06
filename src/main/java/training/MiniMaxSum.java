package training;

import java.util.ArrayList;
import java.util.List;

/**
 * Given five positive integers, find the minimum and maximum values
 * that can be calculated by summing exactly four of the five integers.
 * Then print the respective minimum and maximum values
 * as a single line of two space-separated long integers.
 *
 * {1,3,5,7,9} -> "16 24"
 * {1,2,3,4,5} -> "10 14"
 */

public class MiniMaxSum {

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>(){{
            add(1);
            add(3);
            add(5);
            add(7);
            add(9);
        }};
        miniMaxSum(arr);
    }

    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here
        int ln = arr.size();
        arr.sort(Integer::compareTo);

        Long min = 0L;
        Long max = 0L;
        for (int i = 0; i < arr.size(); i++) {
            if (i < ln - 1) {
                min += arr.get(i);
            }
            if (i > 0) {
                max += arr.get(i);
            }
        }
        System.out.println(min + " " + max);
    }
}
