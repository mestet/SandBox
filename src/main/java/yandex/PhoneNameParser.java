package yandex;

import java.util.HashSet;
import java.util.Set;

/**
 * Парсер номера телефона
 * Описание
 * Различные компании любят представлять свои телефонные номера в виде слов для простоты запоминания.
 * Например, +7-926-FLOWERS.
 * Задача - реализовать нормализацию строки и распарсить буквенное представление номера.
 * Нужно постараться заменить все символы-слова на числа.
 * Между словами или цифрами может быть разделитель "-" или " " — если он есть, его нужно убрать.
 * Например,
 * CALL-ME = 225563
 * CALL-1-ME = 2255163
 * Словарь:
 * 2 = A B C
 * 3 = D E F
 * 4 = G H I
 * 5 = J K L
 * 6 = M N O
 * 7 = P Q R S
 * 8 = T U V
 * 9 = W X Y Z
 */

public class PhoneNameParser {

    public static void main(String[] args) {
        PhoneNameParser parser = new PhoneNameParser();

        System.out.println(parser.parse("CALL-ME"));
        System.out.println(parser.parse("CALL-1-ME"));
        System.out.println(parser.parse("+7-926-FLOWERS"));
    }

    private String parse(String phone) {
        StringBuilder builder = new StringBuilder();
        char[] chars = phone.toCharArray();

        for (char s: chars) {
            builder.append(translate(s));
        }

        return builder.toString();
    }

    private char translate(char letter) {
        switch (letter) {
            case 'A':
            case 'B':
            case 'C':
                return '2';
            case 'D':
            case 'E':
            case 'F':
                return '3';
            case 'G':
            case 'H':
            case 'I':
                return '4';
            case 'J':
            case 'K':
            case 'L':
                return '5';
            case 'M':
            case 'N':
            case 'O':
                return '6';
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
                return '7';
            case 'T':
            case 'U':
            case 'V':
                return '8';
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
                return '9';
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
                return letter;
            default:
                return 0;
        }


    }

}
