package de.edlly.key;

import de.edlly.key.entities.wp.Posts;

import de.edlly.key.services.FetchPostsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PostsController {

    private final FetchPostsData fetchPostsData;

    public PostsController(@Autowired FetchPostsData fetchPostsData) {
        this.fetchPostsData = fetchPostsData;
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduled() {
        log.info("Fetching posts");
        Optional<Posts> postsData = fetchPostsData.getPostsData();
        if (postsData.isEmpty()) {
            log.error("No posts data found");
        } else {
            log.info("Posts data: {}", postsData.get());
        }
    }
}
