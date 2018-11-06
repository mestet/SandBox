import java.util.List;

public class Application {

    public static void main(String[] args) {

        List<String> generatedPans =  LuhnGenerator.Generate16Pan("220220");
        System.out.println("Stop generating");

    }
}
