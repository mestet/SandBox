import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;


@Configuration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rt = new RestTemplate();

        // Mapping objects to JSON
        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        // Custom error handling
        rt.setErrorHandler(new DefaultResponseErrorHandler() {
            protected boolean hasError(HttpStatus statusCode) {
                return false;
            }
        });

        return rt;
    }
}
