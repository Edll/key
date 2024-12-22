package de.edlly.key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FetchPostsData {

    @Value("${de.edlly.key.wordpress.api.endpoint}")

    private RestTemplate restTemplate = new RestTemplate();


}
