package codility;

import java.util.Arrays;

public class Solution {

    public int getBinaryGap(int N) {
        if (N < 5) {
            return 0;
        }
        char[] binary = Integer.toBinaryString(N).toCharArray();

        System.out.println("Char arr is " + Arrays.toString(binary));

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

    public int[] cyclicRotation(int[] A, int K) {
        int len = A.length;

        if (len < 2 || K < 1) return A;
        int[] B = new int[len];

        for (int i = 0; i < len; i++) {
            int x = i + K;
            while (x >= len) {
                x = x - len;
            }
            B[x] = A[i];
        }

        return B;
    }

    public int oddOccurrences(int[] A) {
        int c = 0;
        for (int i = 0; i < A.length; i++) {
            c ^= A[i];
        }
        return c;
    }

    public int missingElement(int[] A) {
        int big = 0;
        for (int i = 0; i <= A.length + 1; i++) {
            big ^= i;
        }

        int small = 0;
        for (int i = 0; i < A.length; i++) {
            small ^= A[i];
        }

        return small ^ big;
    }

    public int frogJump(int X, int Y, int D) {
        if (X >= Y) return 0;
        int dist = Y - X;
        return (dist + D - 1)/D;
    }

    public int isPermutation (int[] A) {
        int f = 0;
        int c = 0;
        for (int i = 1; i <= A.length ; i++) {
            f ^= i;
            c ^= A[i-1];
        }
        return f == c ? 1 : 0;
    }

    public int frogRiverOne(int X, int[] A){


        return 0;
    }


}
