package de.edlly.key;

import com.google.common.util.concurrent.AbstractScheduledService;
import de.edlly.key.entities.wp.Posts;

import de.edlly.key.services.FetchPostsData;
import de.edlly.key.services.PostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PostsFetchScheduler {

    private final FetchPostsData fetchPostsData;
    private final PostProcessor postProcessor;

    public PostsFetchScheduler(
            @Autowired FetchPostsData fetchPostsData,
            @Autowired PostProcessor postProcessor
            ) {
        this.fetchPostsData = fetchPostsData;
        this.postProcessor = postProcessor;
    }

  //  @Scheduled(fixedDelay = 5000)
    public void scheduled() {
        log.info("Fetching posts");
        Optional<Posts> postsData = fetchPostsData.getPostsData();
        if (postsData.isEmpty()) {
            log.error("No posts data found");
        } else {
            log.info("Posts data: {}", postsData.get().size());
            postProcessor.process(postsData.get());
        }
    }
}

