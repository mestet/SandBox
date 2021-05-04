package utils.files;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileDuplicateStringRemover {

    public static void main(String[] args) {
        new FileDuplicateStringRemover().doTheThing();
    }

    public void doTheThing() {
        Set<String> strings = readFileAsStringSet("/raw.txt");

        strings.forEach(System.out::println);

    }

    private String readFileFromResources(String fileName) {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (InputStream is = FileDuplicateStringRemover.class.getResourceAsStream(fileName)){
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultStringBuilder.toString();
    }

    private Set<String> readFileAsStringSet(String fileName) {
        Set<String> resultSet = new HashSet<>();
        try (InputStream is = FileDuplicateStringRemover.class.getResourceAsStream(fileName)){
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                resultSet.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

}
