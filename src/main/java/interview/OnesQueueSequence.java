package interview;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Требуется найти в бинарном векторе самую длинную последовательность единиц и вывести её длину.
 * Желательно получить решение, работающее за линейное время и при этом проходящее по входному массиву только один раз.
 *
 * Первая строка входного файла содержит одно число n, n ≤ 10000.
 * Каждая из следующих n строк содержит ровно одно число — очередной элемент массива.
 */

public class OnesQueueSequence {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int amount = Integer.parseInt(reader.readLine()); // can be ignored
        String line;
        int maxLength = 0;
        int count = 0;

        while ((line = reader.readLine()) != null) {
            if (line.equals("1")) {
                count++;
                if (count > maxLength) maxLength = count;
            } else {
                if (count > maxLength) maxLength = count;
                count = 0;
            }
        }

        System.out.println(maxLength);
    }
}
