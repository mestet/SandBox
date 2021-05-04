package api.wrapper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Component
public class GoogleScholarApi extends BaseApi {

    private final String googleScholarUrl = "https://scholar.google.com/scholar_complete";

    public ResponseEntity<String> getResponse(String request) {
        return sendGetRequest(googleScholarUrl,
                new ParameterizedTypeReference<>() {
                },
                new HashMap<String, String>() {{
                    put("hl", "ru");
                    put("as_sdt", "");
                    put("q", request);
                    put("btnG", "");
                }});
    }

}
