package learning.leetcode;

/**
 * Separate string with whitespaces with provided
 * beautify("18446744073709551616", 4) -> "1844 6744 0737 0955 1616"
 * beautify("18446744073709551616", 3) -> "18 446 744 073 709 551 616"
 */

public class StringSpaceBeautifier {

    public static void main(String[] args) {
        System.out.println(new StringSpaceBeautifier()
                .beautify("18446744073709551616", 3));
    }


    public String beautify(String inputString, int blockSize) {
        if (inputString == null
                || inputString.isEmpty()
                || inputString.isBlank()) {
            return inputString;
        }

        char[] chars = inputString.toCharArray();

        StringBuilder bld = new StringBuilder();

        int ln = chars.length;
        int nextSpacePos = (ln % blockSize) - 1;

        if (nextSpacePos <= 0) {
            nextSpacePos = blockSize - 1;
        }

        for (int i = 0; i < ln; i++) {
            bld.append(chars[i]);

            if (i == nextSpacePos && i < ln - blockSize) {
                bld.append(' ');
                nextSpacePos += blockSize;
            }
        }
        return bld.toString();
    }
}
