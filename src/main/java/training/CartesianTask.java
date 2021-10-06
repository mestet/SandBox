package training;

import java.util.*;
import java.util.stream.Collectors;

public class CartesianTask {

    public static void main(String[] args) {

        new CartesianTask().printAllPermutations("abcd");
        new CartesianTask().printAllPermutations("aaa");

    }

    private void printAllPermutations(String inputString) {
        List<Character> characterList = inputString.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        List<List<Character>> cartesianList = cartesian(characterList);
        for (List<Character> row : cartesianList) {
            StringBuilder sb = new StringBuilder();
            for (int i = row.size() - 1; i >= 0; i--) {
                sb.append(row.get(i));
            }
            System.out.println(sb);
        }
    }

    /**
     * Makes two-dimensional collection containing all possible combinations
     * of elements from the provided collection.
     * Duplicates are not ignored.
     *
     * @param inputList collection of any elements
     * @param <T>       any Object
     * @return cartesian collection
     */
    private <T> List<List<T>> cartesian(List<T> inputList) {
        int ln = inputList.size();

        List<List<T>> result = new ArrayList<>();
        if (ln > 2) {
            for (int i = 0; i < ln; i++) {
                T head = inputList.get(i);
                List<T> tail = copyIgnoreOneByIndex(inputList, i);
                List<List<T>> rowTails = cartesian(tail);
                rowTails.forEach(rowTail -> rowTail.add(head));
                result.addAll(rowTails);
            }
        } else if (ln == 2) {
            result.add(new ArrayList<T>() {{
                add(inputList.get(0));
                add(inputList.get(1));
            }});
            result.add(new ArrayList<T>() {{
                add(inputList.get(1));
                add(inputList.get(0));
            }});
        }
        return result;
    }

    private <T> List<T> copyIgnoreOneByIndex(List<T> list, int index) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i != index) {
                result.add(list.get(i));
            }
        }
        return result;
    }
}
