package learning.leetcode;


import java.util.Random;


public class MountainRoute {

    private static final Character zero = '_';
    private static final char up = '/';
    private static final char down = '\\';

    private final int[] steps;

    public int getHighestPeak() {
        return highestPeak;
    }

    public int getLowestHollow() {
        return lowestHollow;
    }

    private int highestPeak;
    private int lowestHollow;

    public MountainRoute(int[] steps) {
        this.steps = steps;

        initPeakAndHollow(steps);
    }

    private void initPeakAndHollow(int[] steps) {
        int peak = 0;
        int hollow = 0;
        int sum = 0;
        for (int step : steps) {
            sum += step;
            if (sum > peak) peak = sum;
            if (sum < hollow) hollow = sum;
        }
        this.highestPeak = peak;
        this.lowestHollow = hollow;
    }

    public char[][] drawRoute() {
        int ln = steps.length;
        int hDiff = highestPeak - lowestHollow + 1;

        char[][] canvas = initCanvas(hDiff, ln);

        int cur = highestPeak;

        for (int i = 0; i < ln; i++) {
            switch (steps[i]) {
                case 0:
                    canvas[cur][i] = zero;
                    break;
                case 1:
                    canvas[cur][i] = up;
                    cur--;
                    break;
                case -1:
                    if (i > 0) cur++;
                    canvas[cur][i] = down;
                    break;
            }
        }

        return canvas;
    }

    private char[][] initCanvas(int h, int ln) {
        char[][] canvas = new char[h][ln];
        for (int i = 0; i < h; i++) {
            canvas[i] = initLine(ln);
        }
        return canvas;
    }

    private char[] initLine(int ln) {
        char[] result = new char[ln];
        for (int i = 0; i < ln; i++) {
            result[i] = ' ';
        }
        return result;
    }

    public int countPeaks() {
        int ln = steps.length;
        if (ln < 2) return 0;

        int peaks = 0;
        boolean wasStepUp = false;
        for (int cur : steps) {
            if (cur == 1) {
                wasStepUp = true;
            } else if (cur == -1) {
                if (wasStepUp) peaks++;
                wasStepUp = false;
            }
        }

        return peaks;
    }

    public static int[] generateSteps(int size) {
        int[] steps = new int[size];
        Random rnd = new Random();
        for (int i = 0; i < size; i++) {
            steps[i] = rnd.nextInt(3) - 1;
        }
        return steps;
    }
}
