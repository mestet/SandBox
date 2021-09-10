package codility;

import java.util.Objects;

public class LogSolutionResults {

    public static void log(Object scenario, Object expect, Object result) {
        String testResult = "FAIL";
        if (Objects.equals(expect, result)) {
            testResult = "PASS";
        }
        System.out.println(testResult
                + ", scenario = " + scenario
                + ", expect = " + expect
                + ", result = " + result);
    }


}
