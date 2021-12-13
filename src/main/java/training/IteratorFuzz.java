package training;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class IteratorFuzz {

    public static void main(String[] args) {

        streamPeek();
    }

    static void streamPeek() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 1, 2, 3);
        stream.parallel()
                .distinct()
                .peek(i -> System.out.print(i + " "))
                .filter(element -> element > 2)
                .peek(i -> System.out.print(i + " "))
                .map(element -> element * 10)
                .peek(i -> System.out.print(i + " "))
                .count();
    }


    static void iteratorFor() {

        List<String> stringList = new ArrayList<String>() {{
            add("one");
            add("two");
            add("three");
        }};

        for (Iterator<String> iterator = stringList.iterator(); iterator.hasNext();) {
            String current = iterator.next();
            System.out.println("Current = " + current);
            if (current.equals("two")) {
                System.out.println("Remove two");
                iterator.remove();
            }
        }

        System.out.println(stringList);

    }
}
