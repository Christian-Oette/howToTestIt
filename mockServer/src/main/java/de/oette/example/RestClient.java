package de.oette.example;


import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class RestClient {

    public String sendGetRequestAndReceiveBody(String uri) throws IOException {
        Content content = Request.Get(uri)
                .execute().returnContent();

        return content.asString();
    }
}
