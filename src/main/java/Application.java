import learning.leetcode.MountainRoute;
import ngram.StringSimilarity;

import java.util.Arrays;


/**
 * Class to run stuff
 */
public class Application {

    public static void main(String[] args) {

        int[] steps = MountainRoute.generateSteps(180);
        MountainRoute mountainRoute = new MountainRoute(steps);

        char[][] canvas = mountainRoute.drawRoute();

        System.out.println("Steps:");
        System.out.println(Arrays.toString(steps));

        System.out.println("Highest peak " + mountainRoute.getHighestPeak());
        System.out.println("Lowest hollow " + mountainRoute.getLowestHollow());
        System.out.println("Peaks count " + mountainRoute.countPeaks());
        for(char[] line:canvas) {
            for(char step: line){
                System.out.print(step);
            }
            System.out.println();
        }
        System.out.println("Drawing finished");
    }

}
