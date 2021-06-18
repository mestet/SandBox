package learning.date;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Runner {

    private static final Pattern PATTERN = Pattern.compile(
            "^(?<format>[^/]+)(/service=(?<service>[^/]*))?/date=(?<date>[0-9]{8})/hour=(?<hour>[0-9]{2})$");

    private static final Pattern PATTERN_UNKNOWN_FORMAT = Pattern.compile(
            "^(?<format>[^/]+)(/service=(?<service>[^/]*))?/date=(?<date>[0-9]{8})/hour=(?<hour>[0-9]{2})$");

    public static void main(String[] args) {

        String uf = "unkn_format/date=20210618/hour=12";
        String nf = "new_format/service=10000000100/date=20210618/hour=12";

        Matcher pm_uf = PATTERN.matcher(uf);



        log.debug("unkn_format check PATTERN: {}", check(PATTERN, uf));
        log.debug("new_format check PATTERN: {}", check(PATTERN, nf));

        log.debug("unkn_format check PATTERN_UNKNOWN_FORMAT: {}", check(PATTERN_UNKNOWN_FORMAT, uf));
        log.debug("new_format check PATTERN_UNKNOWN_FORMAT: {}", check(PATTERN_UNKNOWN_FORMAT, nf));
    }


    private static boolean check(Pattern p, String part) {
        return p.matcher(part).matches();
    }
}
