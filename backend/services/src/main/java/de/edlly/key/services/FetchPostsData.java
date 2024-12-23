package de.edlly.key.services;

import de.edlly.key.entities.wp.Posts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@Slf4j
public class FetchPostsData {

    @Value("${de.edlly.key.wordpress.api.endpoint}")
    private String endpoint;

    private final RestTemplate restTemplate;

    public FetchPostsData(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Posts> getPostsData() {
        try {
            log.info("Fetching posts data from {}...", endpoint);
            Posts posts = restTemplate.getForObject(endpoint, Posts.class);
            if (posts == null) {
                log.error("No posts found on endpoint: {}", endpoint);
                return Optional.empty();
            }
            return Optional.of(posts);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

}
