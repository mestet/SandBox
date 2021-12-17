package interview;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IntegerDuplicatesByChunks {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 32);
        int n = Integer.parseInt(reader.readLine());

        long p = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long c = Long.parseLong(reader.readLine());
            if (c != p) {
                p = c;
                System.out.println(p);
            }
        }
    }
}
