package de.edlly.key.client.websocket;

import de.edlly.key.client.entities.ClientWordMap;
import de.edlly.key.entities.store.WordMap;
import de.edlly.key.entities.wp.Post;
import de.edlly.key.entities.wp.Posts;
import de.edlly.key.services.FetchPostsData;
import de.edlly.key.services.PostProcessor;
import de.edlly.key.services.WordCountMapperStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
public class ClientController {

    @Autowired
    FetchPostsData fetchPostsData;
    @Autowired
    PostProcessor postProcessor;

    @Autowired
    WordCountMapperStore store;

    @MessageMapping("/init")
    @SendTo("/topic/word-map")
    public Map<Long, ClientWordMap> init(String request) {

        log.info("Fetching posts");
        Optional<Posts> postsData = fetchPostsData.getPostsData();
        if (postsData.isEmpty()) {
            log.error("No posts data found");
        } else {
            log.info("Posts data: {}", postsData.get().size());
            postProcessor.process(postsData.get());
        }
        Map<Long, ClientWordMap> data = new HashMap<>();
        store.getAllPostIdsInCache().forEach(id -> {
                    Optional<WordMap> wordMapOpt = store.getWordMap(id);
                    if (wordMapOpt.isPresent()) {
                        WordMap storeWordMap = wordMapOpt.get();
                        ClientWordMap clientWordMap = storeWordMapToClientWordMap(storeWordMap);
                        data.put(id, clientWordMap);
                    }
                }
        );
        return data;
    }

    private ClientWordMap storeWordMapToClientWordMap(WordMap storeWordMap) {
        ClientWordMap clientWordMap = new ClientWordMap();
        Post post = storeWordMap.getPost();
        if (post != null) {
            clientWordMap.setPostId(storeWordMap.getPost().getId());
            if (post.getDate() != null) {
                clientWordMap.setPostDate(post.getDate().getTime());
            }
            clientWordMap.setPostLink(post.getLink());
            if (post.getTitle() != null) {
                clientWordMap.setPostTitle(post.getTitle().getRendered());
            }
        }
        clientWordMap.setWordMap(storeWordMap.getWords());
        return clientWordMap;
    }
}