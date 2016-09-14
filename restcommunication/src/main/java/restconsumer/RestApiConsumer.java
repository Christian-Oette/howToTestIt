package restconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestApiConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${restUrl}")
    private String url;

    public String getList() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/plain");
        HttpEntity<String> requestEntity = new HttpEntity<String>("Headers", httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return exchange.getBody();
    }
}
