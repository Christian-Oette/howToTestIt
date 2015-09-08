package de.oette.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import static org.junit.Assert.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

public class RestClientTest {

    private ClientAndServer mockServer;

    @Before
    public void startProxy() {
        mockServer = startClientAndServer(1080);

        new MockServerClient("127.0.0.1", 1080).when(
                HttpRequest.request()
                        .withMethod("GET")

        ).respond(HttpResponse.response()
                .withBody("Hello Client"));
    }

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this);

    @After
    public void stopProxy() {
        mockServer.stop();
    }

    @Test
    public void test() throws Exception {
        RestClient restClient = new RestClient();
        String content = restClient.sendGetRequestAndReceiveBody("http://localhost:1080");
        assertEquals(content, "Hello Client");
    }
}
