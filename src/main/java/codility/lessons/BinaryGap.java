package codility.lessons;

import java.util.HashMap;
import java.util.Map;

import static codility.LogSolutionResults.log;

/**
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones
 * at both ends in the binary representation of N.
 * <p>
 * For example, number 9 has binary representation 1001 and contains a binary gap of length 2.
 * The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3.
 * The number 20 has binary representation 10100 and contains one binary gap of length 1.
 * The number 15 has binary representation 1111 and has no binary gaps.
 * The number 32 has binary representation 100000 and has no binary gaps.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int N); }
 * <p>
 * that, given a positive integer N, returns the length of its longest binary gap.
 * The function should return 0 if N doesn't contain a binary gap.
 * <p>
 * For example, given N = 1041 the function should return 5, because N has binary representation 10000010001
 * and so its longest binary gap is of length 5. Given N = 32 the function should return 0,
 * because N has binary representation '100000' and thus no binary gaps.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * N is an integer within the range [1..2,147,483,647].
 */

public class BinaryGap {

    public static void main(String[] args) {
        Solution solution = new Solution();

        Map<Integer, Integer> caseList = new HashMap<Integer, Integer>() {{
            put(9, 2);
            put(529, 4);
            put(20, 1);
            put(1041, 5);
            put(15, 0);
            put(32, 0);
            put(1, 0);
            put(6, 0);
            put(5, 1);
        }};

        caseList.forEach((k, v) -> {
            log(k, v, solution.solution(k));
        });
    }

    static class Solution {

        public int solution(int N) {
            if (N < 5) {
                return 0;
            }
            char[] binary = Integer.toBinaryString(N).toCharArray();

            int candidate = 0;
            for (int i = 0; i < binary.length - 1; i++) {
                int count = 0;
                if (binary[i] == '1' && binary[i + 1] == '0') {
                    int x = i + 1;
                    while (binary[x] == '0') {
                        count++;
                        x++;
                        if (x >= binary.length) return candidate;
                    }
                    i = x - 1;
                    if (count > candidate) candidate = count;
                }
            }
            return candidate;
        }
    }

}
