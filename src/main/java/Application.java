import org.springframework.context.annotation.Configuration;
import utils.Hero;

import java.util.*;
import java.util.stream.Stream;

@Configuration
public class Application {

    public static void main(String[] args) {


    }

    private static void printHero() {
        Hero hero = new Hero("Gilraen", "10C9E400");
        System.out.println("Hero " + hero.getName() + " attributes: ");
        System.out.println(hero.toString());
        double a =+ 0.5;
        a =+ 1;

        Stream<String> as = new ArrayList<String>(Arrays.asList("aa", "aaa", "ccc", "dddd", "aba"))
                .stream()
                .filter(x -> {
                            System.out.println(x.length());
                            return true;
                        }
                ).sorted();
        
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate rt = new RestTemplate();
//
//        // Mapping objects to JSON
//        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//        // Custom error handling
//        rt.setErrorHandler(new DefaultResponseErrorHandler() {
//            protected boolean hasError(HttpStatus statusCode) {
//                return false;
//            }
//        });
//
//        return rt;
//    }
}
