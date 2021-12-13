package interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Даны массив целых чисел n и число k.
Найти в массиве два таких числа a и b, что  a + b = k и вывести их "(a, b)" , либо вывести "none".
Позиции возвращаемых чисел в исходном массиве должны быть различны,
то есть одно и то же число нельзя использовать дважды для суммы.

[9, -7, 3, 6, -2], 7 => (9, -2)
[9, -7, 3, 6, -2], 10 => none
[4, 2, 8, 6, 4], 4 => none
[4, 2, 8, 6], 4 => none
[4, 0, 8, 6], 4 => (4, 0)
[4, 2, 8, 6, 4], 6 => (4, 2)
[4, 2, 8, 3, 4], 8 => (4, 4)
[4, 2, 8, 6, 4], 8 => (2, 6)
 */

public class FindSumParts {

    public static void main(String[] args) {
        List<Integer> testCase = Arrays.asList(4, -4, 3, 8, 1, 6, 2);
        findSumParts(testCase, 8);

    }

    static void findSumParts(List<Integer> input, int k) {
        Map<Integer, Integer> intMap = new HashMap<>();

        for (Integer el : input) {
            Integer possiblePair = intMap.get(el);
            if (possiblePair != null) {
                System.out.println("(" + el + ", " + possiblePair + ")");
                return;
            }
            Integer possibleValue = k - el;
            intMap.put(possibleValue, el);
        }
        System.out.println("none");
    }
}
