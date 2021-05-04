package alex.learning.google.scholar;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class GoogleScholarParsing {

    private static final String USER_AGENT = "Mozilla/5.0 " +
            "(Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/88.0.4324.190 Safari/537.36";

    private static final String COOKIE =
            "HSID=AzDX4jN57uFQ1-ndd; SSID=Alqi66OD_MeQiHBs7; APISID=1BAgMmT_P1ulLyeJ/AxUrWiQTXnhUPZwfx; SAPISID=h2bfHhXUTyZ2gLRL/A8wudIi8qcK524bVU; __Secure-3PAPISID=h2bfHhXUTyZ2gLRL/A8wudIi8qcK524bVU; ANID=AHWqTUmFFKUrp6Y-wkrOYuubt0on-I19RdyKoy1sGil2mnQzU2Y2YNKvU9ybUNqE; S=billing-ui-v3=-5cZajfL4GZcxM7AKb9n2WuDL78Nql6K:billing-ui-v3-efe=-5cZajfL4GZcxM7AKb9n2WuDL78Nql6K; SID=7QdUIM0WZ-XIAu07WQaVW4c7MyYkk06SdOiWtCFf-lkwBCQD-kCLSe-tuZ6h7FCKS_MvFA.; __Secure-3PSID=7QdUIM0WZ-XIAu07WQaVW4c7MyYkk06SdOiWtCFf-lkwBCQDoZmhC1lRBGfwOuPOMJueNA.; SEARCH_SAMESITE=CgQI_ZEB; NID=211=GL3oEZNsB5IbsQ92ep3NfuTLv_IYSTlMa6mPsWdqIoEXIWnfnypRmSXbpl4L3ImdEFU4ARRa_o92WFHm7iB4ghYP7SHk6NM786PJQLSjTOwCj_V7O2-p6rMtEn5R9xet809aGnLw6ntY5Q26LHBmRG6QFcBQmACmwh9LIo8AuGNyU-FgH6NJMpWcQUCGwnH08u5UByuhxLcT8zVrsrO5CW3E_8SbPsohNlrygDDXUifSYRjT3J_PYEI; 1P_JAR=2021-03-11-09; GSP=A=L9TKfQ:CPTS=1615455648:LM=1615455648:S=XOa8sXT_VKBBd5rJ; GOOGLE_ABUSE_EXEMPTION=ID=10c1462da530d666:TM=1615457021:C=r:IP=37.204.197.160-:S=APGng0topl9TG3Wh9wLU2dgVCxK0Tq_Weg; SIDCC=AJi4QfEX2OPdMm4uodue45j6qjEtTz5t3Vhsqm6k8pHiNUVHVlY-DVqsRqmeWsL9wwMI3G5OI4MJ; __Secure-3PSIDCC=AJi4QfGyt-CI6FO9yKwDVJozhzNQDCGLBEp-WIXcr26cNmlcrv6GdawcY8T4pCtaz9jeO4iNPng";

    private static final List<String> vertical = Arrays.asList(
            "фаззинг"
//            "фаззер",
//            "генерация+входных+данных",
//            "тестовый+пример",
//            "генерация+начального+числа",
//            "возможность+использования+сбоев",
//            "мутации",
//            "fuzz",
//            "fuzzing",
//            "fuzzer",
//            "input generation",
//            "test case",
//            "seed generation",
//            "crashes exploitability",
//            "mutation"
    );

    private static final List<String> horizontal = Arrays.asList(
            "машинное обучение"
//            "нейронную сеть",
//            "глубокое обучение",
//            "обучение с подкреплением",
//            "генеративную противоборствую сеть",
//            "вложение",
//            "байесовская сеть",
//            "дерево решений",
//            "машина опорных векторов",
//            "генетические алгоритмы",
//            "случайный лес",
//            "machine learning",
//            "neural network",
//            "deep learning",
//            "reinforcement learning",
//            "generative adversarial network",
//            "embedding",
//            "bayesian network",
//            "decision tree",
//            "support vector machine",
//            "genetic algorithms",
//            "random forest",
//            "symbolic execution",
//            "concolic execution",
//            "SMT"
    );

    public static void main(String[] args) {
        new GoogleScholarParsing().makeMagic();
    }

    public void makeMagic() {
        Set<String> resultSet = new HashSet<>();

        int emptyCount = 0;
        for (String hor : horizontal) {
            for (String ver : vertical) {
                Set hrefSet = getHrefSet(hor, ver);

                if (hrefSet.isEmpty()) {
                    emptyCount++;
                    if (emptyCount > 5) {
                        break;
                    }
                } else {
                    emptyCount = 0;
                }

                resultSet.addAll(hrefSet);
                randomWait();
            }
            if (emptyCount > 5) {
                System.out.println("Google stop me");
                break;
            }
        }

        System.out.println("=".repeat(13));
        resultSet.forEach(System.out::println);
        System.out.println("=".repeat(13));
    }


    public Set<String> getHrefSet(String hor, String ver) {
        String request = "\"" +
                ver +
                "\" AND \"" +
                hor +
                "\"";

        String requestEncoded = encode(request);
        Document doc = get(requestEncoded);

        if (doc == null) {
            return Collections.emptySet();
        }

        Set hrefSet = readHrefSet(doc);

        System.out.println("mid result for: " + request);
        System.out.println(requestEncoded);
        System.out.println(hrefSet);
        return hrefSet;
    }

    private Set<String> readHrefSet(Document doc) {
        return doc.getElementsByClass("gs_r gs_or gs_scl")
                .stream()
                .map(element -> element.getElementsByClass("gs_rt")
                        .select("a")
                        .attr("href"))
                .collect(Collectors.toSet());
    }

    public Connection connect(String request) {
        HashMap<String, String> params = new HashMap<>() {{
            put("hl", "ru");
            put("as_sdt", "0%2C5");
            put("q", request);
        }};

        Connection connection = Jsoup.connect("https://scholar.google.com/scholar");
        connection.userAgent(USER_AGENT);
        connection.header("Cookie", COOKIE);
        connection.data(params);

        return connection;
    }

    public Document get(String request) {
        try {
            return connect(request).get();
        } catch (HttpStatusException hse) {
            hse.printStackTrace();
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void randomWait() {
        Random rand = new Random();
        sleep(rand.nextInt(7000) + 3000);
    }

    private String encode(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }
}
