package interview;

import java.util.Arrays;
import java.util.List;

/**
 * Есть целочисленная матрица размера N x M со следующими свойствами:
 * В каждой строке числа упорядочены
 * Первое число каждой строки не меньше последнего числа предыдущей строки
 * <p>
 * Надо найти в этой матрице элемент и выдать true, если он есть, в противном случае false
 * <p>
 * <p>
 * [[1,   2,   3],
 * [10,  20,  30],
 * [100, 200, 300]], 20 => true
 * <p>
 * [[1,   2,   3],
 * [10,  20,  30],
 * [100, 200, 300]], 25 => false
 */

public class SliceSearching {

    private static class TestCase {
        List<List<Integer>> matrix;
        int el;

        public TestCase(List<List<Integer>> matrix, int el) {
            this.matrix = matrix;
            this.el = el;
        }
    }

    public static void main(String[] args) {

        List<TestCase> cases = Arrays.asList(
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(10, 20, 30),
                        Arrays.asList(100, 200, 300)), 1),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(10, 20, 30),
                        Arrays.asList(100, 200, 300)), 100),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(10, 20, 30),
                        Arrays.asList(100, 200, 300)), 300),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(10, 20, 30),
                        Arrays.asList(100, 200, 300)), 301),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(10, 20, 30),
                        Arrays.asList(100, 200, 300)), 0),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                        Arrays.asList(9, 11, 13, 15, 17, 19, 21),
                        Arrays.asList(22, 33, 44, 55, 66, 77, 88),
                        Arrays.asList(101, 102, 103, 120, 130, 140, 200),
                        Arrays.asList(200, 220, 231, 242, 256, 266, 300),
                        Arrays.asList(301, 331, 332, 340, 355, 366, 400),
                        Arrays.asList(401, 403, 404, 405, 417, 422, 500)), 42),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                        Arrays.asList(9, 11, 13, 15, 17, 19, 21),
                        Arrays.asList(22, 33, 44, 55, 66, 77, 88),
                        Arrays.asList(101, 102, 103, 120, 130, 140, 200),
                        Arrays.asList(200, 220, 231, 242, 256, 266, 300),
                        Arrays.asList(301, 331, 332, 340, 355, 366, 400),
                        Arrays.asList(401, 403, 404, 405, 417, 422, 500)), 256),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                        Arrays.asList(9, 11, 13, 15, 17, 19, 21),
                        Arrays.asList(22, 33, 44, 55, 66, 77, 88),
                        Arrays.asList(101, 102, 103, 120, 130, 140, 200),
                        Arrays.asList(200, 220, 231, 242, 256, 266, 300),
                        Arrays.asList(301, 331, 332, 340, 355, 366, 400),
                        Arrays.asList(401, 403, 404, 405, 417, 422, 500)), 242),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                        Arrays.asList(9, 11, 13, 15, 17, 19, 21),
                        Arrays.asList(22, 33, 44, 55, 66, 77, 88),
                        Arrays.asList(101, 102, 103, 120, 130, 140, 200),
                        Arrays.asList(200, 220, 231, 242, 256, 266, 300),
                        Arrays.asList(301, 331, 332, 340, 355, 366, 400),
                        Arrays.asList(401, 403, 404, 405, 417, 422, 500)), 0),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                        Arrays.asList(9, 11, 13, 15, 17, 19, 21),
                        Arrays.asList(22, 33, 44, 55, 66, 77, 88),
                        Arrays.asList(101, 102, 103, 120, 130, 140, 200),
                        Arrays.asList(200, 220, 231, 242, 256, 266, 300),
                        Arrays.asList(301, 331, 332, 340, 355, 366, 400),
                        Arrays.asList(401, 403, 404, 405, 417, 422, 500)), 500),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                        Arrays.asList(9, 11, 13, 15, 17, 19, 21),
                        Arrays.asList(22, 33, 44, 55, 66, 77, 88),
                        Arrays.asList(101, 102, 103, 120, 130, 140, 200),
                        Arrays.asList(200, 220, 231, 242, 256, 266, 300),
                        Arrays.asList(301, 331, 332, 340, 355, 366, 400),
                        Arrays.asList(401, 403, 404, 405, 417, 422, 500)), 1),
                new TestCase(Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                        Arrays.asList(9, 11, 13, 15, 17, 19, 21),
                        Arrays.asList(22, 33, 44, 55, 66, 77, 88),
                        Arrays.asList(101, 102, 103, 120, 130, 140, 200),
                        Arrays.asList(200, 220, 231, 242, 256, 266, 300),
                        Arrays.asList(301, 331, 332, 340, 355, 366, 400),
                        Arrays.asList(401, 403, 404, 405, 417, 422, 500)), 501)

        );

        for (TestCase tc : cases) {
            System.out.println("Case for: " + tc.el);
            System.out.println(isPresentInMatrix(tc.matrix, tc.el));
        }
    }


    static boolean isPresentInMatrix(List<List<Integer>> matrix, int el) {
        if (matrix.isEmpty()) return false;

        List<Integer> row = searchRow(matrix, el);
        if (row.isEmpty()) return false;

        return searchElement(row, el);
    }

    private static boolean searchElement(List<Integer> row, int el) {
        int pos = row.indexOf(el);
        return pos >= 0;
    }

    static List<Integer> searchRow(List<List<Integer>> matrix, int el) {
        int m = matrix.size() - 1;
        int lowBound = 0;
        int upBound = m;
        List<Integer> row;
        while (true) {
            int slice = (upBound + lowBound) / 2;

            row = matrix.get(slice);
            if (row.isEmpty()) return row;

            if (slice == lowBound || slice == upBound) {
                return matrix.get(slice);
            }

            Integer firstInRow = row.get(0);
            Integer lastInRow = row.get(row.size() - 1);
            if (el < firstInRow) {
                upBound = slice;
            } else if (el > lastInRow) {
                lowBound = slice + 1;
            } else {
                return row;
            }
        }
    }
}