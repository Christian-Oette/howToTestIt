package restconsumer;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestApiConsumerTest.TestContext.class, RestApiConsumer.class})
public class RestApiConsumerTest {

    public static final String REST_API_TEST_URL = "http://localhost:8089/resource/list";

    @Autowired
    private RestApiConsumer testee;

    @Test
    public void testSuccessfulResult() {
        WireMockServer wireMockServer = new WireMockServer(8089);
        try {
            wireMockServer.start();
            wireMockServer.stubFor(get(urlEqualTo("/resource/list"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/plain")
                            .withBody("Hello World")));


            String result = testee.getList();
            assertThat(result).isEqualTo("Hello World");
        } finally {
            wireMockServer.stop();
        }
    }

    @Test(expected = HttpClientErrorException.class)
    public void test403() {
        WireMockServer wireMockServer = new WireMockServer(8089);
        try {
            wireMockServer.start();
            wireMockServer.stubFor(get(urlEqualTo("/resource/list"))
                    .willReturn(aResponse()
                            .withStatus(403)));
            testee.getList();
        } finally {
            wireMockServer.stop();
        }
    }


    public static class TestContext {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

        @Bean
        public static PropertySourcesPlaceholderConfigurer properties() throws Exception {
            final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            Properties properties = new Properties();
            properties.setProperty("restUrl", REST_API_TEST_URL);
            pspc.setProperties(properties);
            return pspc;
        }
    }
}
