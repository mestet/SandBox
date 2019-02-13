import java.util.ArrayList;
import java.util.List;


public class LuhnGenerator {


    public static List<String> Generate16Pan(String bin, Integer size) {
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
            if (resultPans.size() >= size) {
                break;
            }

        }
        return resultPans;
    }


    public static boolean isLuhn(String pan) {
        int sum = 0;
        for (int i = 0; i < pan.length(); i++) {
            int digit = pan.charAt(pan.length() - i - 1) - '0';
            if (i % 2 == 1) digit *= 2;
            sum += digit > 9 ? digit - 9 : digit;
        }
        return sum % 10 == 0;
    }


    static void generatePanToConsole(String bin, Integer amount) {
        String separator = new String(new char[80]).replace("\0", "=");
        List<String> generatedPans = LuhnGenerator.Generate16Pan(bin, amount);
        System.out.println("Start generating");
        System.out.println(separator);
        generatedPans.forEach(System.out::println);
        System.out.println(separator);
        System.out.println("Stop generating");
    }
}