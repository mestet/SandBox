package yandex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Удалить лишние повторения из массива
 * Описание
 * Дан массив, необходимо оставить только N повторений каждого числа, сохранив порядок
 * <p>
 * Для указанного массива оставить только N повторений (включая первое) каждого числа, сохранив порядок
 * Пример:
 * {20,37,20,21}, 1 -> {20,37,21}
 * {1,2,3,1,2,1,2,3}, 2 -> {1,2,3,1,2,3}
 * <p>
 * public int[] removeUnnecessaryDupes(int[] data, int n){
 * <p>
 * }
 */

public class FilterArray {

    public static class TestCase {
        int[] intArray;
        int n;
        int[] expected;

        public TestCase(int[] intArray, int n, int[] expected) {
            this.intArray = intArray;
            this.n = n;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        List<TestCase> testCaseList = Arrays.asList(
                new TestCase(
                        new int[]{20, 37, 20, 21},
                        1,
                        new int[]{20, 37, 21}),
                new TestCase(
                        new int[]{1, 2, 3, 1, 2, 1, 2, 3},
                        2,
                        new int[]{1, 2, 3, 1, 2, 3}),
                new TestCase(
                        new int[]{2, 2, 2, 1, 2, 1, 2, 1},
                        4,
                        new int[]{2, 2, 2, 1, 2, 1, 1})
        );
        FilterArray fa = new FilterArray();

        for (TestCase cs : testCaseList) {
            int[] result = fa.removeUnnecessaryDupes(cs.intArray, cs.n);
            boolean pass = Arrays.equals(result, cs.expected);
            String checkWord = pass ? "passed" : "failed";

            System.out.println("Test " + checkWord + " for: "
                    + Arrays.toString(cs.intArray)
                    + "\t -> \t"
                    + Arrays.toString(cs.expected));
        }
    }

    public int[] removeUnnecessaryDupes(int[] data, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();

        ArrayList<Integer> result = new ArrayList<>();
        for (int el : data) {
            map.merge(el, 1, Integer::sum);
            if (map.get(el) <= n) {
                result.add(el);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
