package de.edlly.key.services;

import de.edlly.key.entities.wp.Posts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TestServiceConfiguration {
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate() {
            @Override
            public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
                return (T) new Posts();
            }


        };
    }
}
