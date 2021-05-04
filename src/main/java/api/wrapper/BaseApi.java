package api.wrapper;

import api.utils.HttpHeaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

public class BaseApi {

    @Autowired
    protected RestTemplate restTemplate;


    protected <T> ResponseEntity<T> exchange(URI uri, HttpMethod method, HttpEntity<?> requestEntity,
                                             ParameterizedTypeReference<T> responseType) {
        ResponseEntity<T> responseEntity;
        try {
            responseEntity = restTemplate.exchange(uri, method, requestEntity, responseType);
        } catch (RestClientException e) {
            throw new RuntimeException("Ошибка при обработке ответа от сервиса", e);
        }

        return responseEntity;
    }

    protected <T> ResponseEntity<T> exchange(URI uri, HttpMethod method, HttpEntity<?> requestEntity,
                                             Class<T> responseTypeClass) {
        return exchange(uri, method, requestEntity, ParameterizedTypeReference.forType(responseTypeClass));
    }


    protected <T> ResponseEntity<T> sendPostRequest(String url, Class<T> clazz, Object request,
                                                    HashMap<String, ?> parameters, HttpHeaders headers) {
        if (headers == null) {
            headers = new HttpHeaders();
        }
        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        URI uri = getUriWithParams(url, parameters);
        return exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                clazz);
    }

    protected <T> ResponseEntity<T> sendGetRequest(String url,
                                                   ParameterizedTypeReference<T> classReference,
                                                   HashMap<String, ?> queryStringParams) {
        URI uri = getUriWithParams(url, queryStringParams);
        HttpHeaders headers = new HttpHeaderBuilder().withAllJson().build();
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        return exchange(
                uri,
                HttpMethod.GET,
                requestEntity,
                classReference);
    }

    protected URI getUriWithParams(String url, @Nullable HashMap<String, ?> queryParams) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        return getUriWithParams(uriComponentsBuilder, ofNullable(queryParams).orElse(new HashMap<>()));
    }

    private URI getUriWithParams(UriComponentsBuilder uriBuilder, @NonNull HashMap<String, ?> queryParams) {
        for (Map.Entry<String, ?> entry : queryParams.entrySet()) {
            if (nonNull(entry.getValue())) {
                addParameterToUri(uriBuilder, entry.getKey(), entry.getValue());
            }
        }
        return uriBuilder.build(true).toUri();
    }

    private void addParameterToUri(UriComponentsBuilder uriBuilder, String key, @NonNull Object value) {
        if (value instanceof Collection) {
            Collection collection = (Collection) value;
            if (collection.isEmpty()) {
                value = null;
            } else {
                value = new ArrayList<>(collection).stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","));
            }
        }
        uriBuilder.queryParam(key, value);
    }
}
