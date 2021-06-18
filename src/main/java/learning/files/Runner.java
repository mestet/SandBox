package learning.files;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Runner {

    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\mvlasov\\Projects\\SandBox\\src\\main\\resources\\blank.pdf");
        byte[] bytes = FileUtils.readFileToByteArray(file);

        System.out.println(Arrays.toString(bytes));

    }
}
