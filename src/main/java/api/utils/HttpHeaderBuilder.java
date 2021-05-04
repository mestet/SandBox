package api.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;

public class HttpHeaderBuilder {

    private HttpHeaders httpHeaders;

    public HttpHeaderBuilder() {
        this.httpHeaders = new HttpHeaders();
    }

    public HttpHeaders build() {
        return httpHeaders;
    }

    public HttpHeaderBuilder withAccept(List<MediaType> mediaTypes) {
        this.httpHeaders.setAccept(mediaTypes);
        return this;
    }

    public HttpHeaderBuilder withContentType(MediaType mediaTypes) {
        this.httpHeaders.setContentType(mediaTypes);
        return this;
    }

    public HttpHeaderBuilder withAllJson() {
        return new HttpHeaderBuilder()
                .withAccept(Collections.singletonList(MediaType.APPLICATION_JSON))
                .withContentType(MediaType.APPLICATION_JSON);
    }
}
