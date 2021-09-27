package yandex;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * (1,2,10,11) + (1,2,11) -> true
 * (1,2,12,11) + (1,2,11) -> false
 * (1,2,4,5,7,9,13) + (1,2,9) -> true
 * (1,2,4,5,7,9,13) + (1,2,3) -> false
 * (1,2,4,5,7,9,13) + (2,1,7) -> false
 * (1,2,4,5,7,9,13) + (4,7,1) -> false
 * (2,4,5,7,9,13,1) + (4,7,1) -> false
 * (2,4,5,7,0,1,14) + (4,7,1) -> true
 */

public class FindIntegerSubsequence {

    @AllArgsConstructor
    static class TestCase {
        List<Integer> mainList;
        List<Integer> subList;
        boolean expected;
    }

    public static void main(String[] args) {
        FindIntegerSubsequence lc = new FindIntegerSubsequence();
        List<TestCase> testCaseList = Arrays.asList(
                new TestCase(Arrays.asList(1, 2, 10, 11), Arrays.asList(1, 2, 11), true),
                new TestCase(Arrays.asList(1, 2, 12, 11), Arrays.asList(1, 2, 11), false),
                new TestCase(Arrays.asList(1, 2, 4, 5, 7, 9, 13), Arrays.asList(1, 2, 9), true),
                new TestCase(Arrays.asList(1, 2, 4, 5, 7, 9, 13), Arrays.asList(1, 2, 3), false),
                new TestCase(Arrays.asList(1, 2, 4, 5, 7, 9, 13), Arrays.asList(2, 1, 7), false),
                new TestCase(Arrays.asList(1, 2, 4, 5, 7, 9, 13), Arrays.asList(4, 7, 1), false),
                new TestCase(Arrays.asList(2, 4, 5, 7, 9, 13, 1), Arrays.asList(4, 7, 1), false),
                new TestCase(Arrays.asList(2, 4, 5, 7, 0, 1, 14), Arrays.asList(4, 7, 1), true),
                new TestCase(Arrays.asList(2, 4, 5, 7, 0, 1, 14), Arrays.asList(4, 3, 2), true)
        );

        for (TestCase ts : testCaseList) {
            System.out.println("Result is: " +
                    lc.solve(ts.mainList, ts.subList) + ", expected was: " + ts.expected);

        }


    }

    public boolean solve(List<Integer> mainList, List<Integer> subList) {
        int start = getSequenceStart(mainList, subList.get(0)) + 1;
        if (start < 0) return false;

        for (int i = 1; i < subList.size(); i++) {
            Integer sub = subList.get(i);
            for (int j = start; j < mainList.size(); j++) {
                Integer main = mainList.get(j);
                int compare = sub.compareTo(main);
                if (compare == 0) {
                    start++;
                    break;
                } else if (compare < 0) {
                    return false;
                }
                start++;
            }
            if (start >= mainList.size()) return false;
        }

        return true;
    }

    private int getSequenceStart(List<Integer> mainList, Integer firstEl) {
        for (int i = 0; i < mainList.size(); i++) {
            if (firstEl.equals(mainList.get(i))) return i;
        }
        return -1;
    }

}
