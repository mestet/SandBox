import org.springframework.context.annotation.Configuration;
import utils.Hero;


@Configuration
public class Application {

    public static void main(String[] args) {
    }

    private static void printHero() {
        Hero hero = new Hero("Gilraen", "10C9E400");
        System.out.println("Hero " + hero.getName() + " attributes: ");
        System.out.println(hero.toString());
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
