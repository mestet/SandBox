package utils.logs;

public class Logger {

    public static void log(Object subj) {
        System.out.println(subj);
    }

    public static void log(String msg, Object subj) {
        log(msg + "\t::\t" + subj);
    }

}
