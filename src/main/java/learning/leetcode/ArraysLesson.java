package learning.leetcode;

import java.util.Arrays;

import static utils.logs.Logger.log;

public class ArraysLesson {

    public static void main(String[] args) {
        ArraysLesson lsn = new ArraysLesson();

    }

    public int[] replaceElements(int[] arr) {
        int curMax = 0;
        int ln = arr.length;
        if (arr.length < 1) return arr;

        arr[ln - 1] = -1;
        for (int i = ln - 2; i > 0; i--) {
            int el = arr[i];
            if (el > curMax) {
                curMax = el;
            } else {
                arr[i] = curMax;
            }
        }

        return arr;
    }


    public boolean validMountainArray(int[] arr) {
        int ln = arr.length;
        if (ln < 3) return false; // to short
        if (arr[0] > arr[1]) return false; // descending from start

        int prev = arr[0];
        int peak = -1;
        for (int i = 1; i < ln; i++) {
            int cur = arr[i];
            if (cur == prev) return false; // plato found

            if (peak < 0) {
                // ascending
                if (cur < prev) {
                    // peak found, start descending
                    peak = i - 1;
                }
            } else {
                // descending
                if (cur > prev) {
                    return false; // second descending found
                }
            }
            prev = cur;
        }

        return peak > 0;
    }

    public boolean checkIfExist(int[] arr) {
        int ln = arr.length;
        if (ln <= 1) return false;

        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];
            int m = n * 2;
            int mPos = linearFindFirstPosition(arr, m);
            if (mPos != i && mPos != -1) {
                return true;
            }
        }

        return false;
    }

    private int linearFindFirstPosition(int[] arr, int el) {
        int ln = arr.length;
        if (ln == 0) return -1;
        for (int i = 0; i < ln; i++) {
            if (arr[i] == el) return i;
        }
        return -1;
    }

    public int removeDuplicates(int[] nums) {
        int ln = nums.length;
        if (ln <= 1) return ln;

        int c = nums[0];
        for (int i = 1; i < ln; i++) {
            while (nums[i] == c && i < ln) {
                removeElementAt(nums, i);
                ln--;
            }
            c = nums[i];
        }
        return ln;
    }

    public int removeElement(int[] nums, int val) {
        int ln = nums.length;

        for (int i = 0; i < ln; i++) {
            while (nums[i] == val && i < ln) {
                removeElementAt(nums, i);
                ln--;
            }
        }

        return ln;
    }

    private void removeElementAt(int[] nums, int pos) {
        for (int i = pos + 1; i < nums.length; i++) {
            nums[i - 1] = nums[i];
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n >= 0) System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    public void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == 0) {
                int curr = arr[i];
                for (int j = i + 1; j < arr.length; j++) {
                    int next = arr[j];
                    arr[j] = curr;
                    curr = next;
                }
                i++;
            }
        }
    }

    public void shiftDuplicateZeros(int[] arr) {
        int l = arr.length;
        int[] result = new int[l];

        int readPos = 0;
        for (int i = 0; i < l; i++) {
            int next = arr[readPos];
            result[i] = next;
            if (next == 0 && i < l - 1) {
                i++;
                result[i] = 0;
            }
            readPos++;
        }

        System.arraycopy(result, 0, arr, 0, l);
    }

    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    public int findNumbers(int[] nums) {
        int count = 0;

        for (int n : nums) {
            if (String.valueOf(n).length() % 2 == 0) {
                count++;
            }
        }
        return count;
    }


    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int candidate = 0;

        for (int e : nums) {
            if (e == 1) {
                candidate++;
            } else {
                if (max < candidate) max = candidate;
                candidate = 0;
            }
            if (max < candidate) max = candidate;
        }

        return max;
    }
}
