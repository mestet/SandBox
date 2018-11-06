import java.util.ArrayList;
import java.util.List;


public class LuhnGenerator {


    public static List<String> Generate16Pan(String bin) {
        StringBuilder startValue = new StringBuilder(bin);
        StringBuilder endValue = new StringBuilder(bin);
        while (startValue.length() < 16) {
            startValue.append('0');
            endValue.append('9');
        }
        Long start = Long.valueOf(startValue.toString());
        Long end = Long.valueOf(endValue.toString());

        List<String> resultPans = new ArrayList<>();
        for (Long i = start; i<= end; i++) {
            if (isLuhn(i.toString())) {
                resultPans.add(i.toString());
                System.out.println(i.toString());
            }
            if (resultPans.size() >= 500) {
                break;
            }

        }
        return resultPans;
    }


    private static boolean isLuhn(String pan) {
        int sum = 0;
        for (int i = 0; i < pan.length(); i++) {
            int digit = pan.charAt(pan.length() - i - 1) - '0';
            if (i % 2 == 1) digit *= 2;
            sum += digit > 9 ? digit - 9 : digit;
        }
        return sum % 10 == 0;
    }
}