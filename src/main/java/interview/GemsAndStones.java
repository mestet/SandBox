package interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Даны две строки строчных латинских символов: строка J и строка S.
 * Символы, входящие в строку J, — «драгоценности», входящие в строку S — «камни».
 * Нужно определить, какое количество символов из S одновременно являются «драгоценностями».
 * Проще говоря, нужно проверить, какое количество символов из S входит в J.
 *
 * На двух первых строках входного файла содержатся две строки строчных латинских символов: строка J и строка S.
 * Длина каждой не превосходит 100 символов.
 */

public class GemsAndStones {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        String J = r.readLine();
        String S = r.readLine();

        int result = 0;
        for (int i = 0; i < S.length(); ++i) {
            char ch = S.charAt(i);
            if (J.indexOf(ch) >= 0) {
                ++result;
            }
        }

        System.out.println(result);
    }
}
