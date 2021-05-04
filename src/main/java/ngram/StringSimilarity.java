package ngram;

import info.debatty.java.stringsimilarity.NGram;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StringSimilarity {
    private static final Pattern normalizePattern = Pattern.compile("(?U)[^\\p{Alnum}]");

    public static void run(String[] args) {
        NGram nGram = new NGram();
        PrintStream printer = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        List<String> stringList = Arrays.asList(
                "wildberries",
                "Wield berris",
                "WildBerries",
                "WildBerrie",
                "Wil",
                "Wild Berries",
                "weilDDerries",
                "Дикие ягоды",
                "Дикая ягода",
                "WB",
                "Berries",
                "Ozon",
                "Amazon",
                "W@&^B", // "WB"
                "  WILD-Berries!%",
                "WD       " // -> "WD"
        );

        printer.println("\nResult:");

        for (int i = 0; i < stringList.size(); i++) {
            String s1 = stringList.get(i);
            String s1n = normalize(s1);
            for (String s2 : stringList) {
                String s2n = normalize(s2);
                double sim = 1 - nGram.distance(s1n, s2n);

                printer.println(
                        "Similarity between " + s1 + "(" + s1n + ")"
                                + " and "
                                + s2 + "(" + s2n + ")"
                                + " is: " + sim);
            }
        }

        printer.println("\n");
        stringList.forEach(str -> printer.println(normalize(str)));
    }

    private static String normalize(String originalString) {
        return normalizePattern.matcher(originalString)
                .replaceAll("")
                .toUpperCase();
    }

}



