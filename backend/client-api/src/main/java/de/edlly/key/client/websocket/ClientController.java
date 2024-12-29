package de.edlly.key.client.websocket;

import de.edlly.key.client.adapter.ClientWordMapAdapter;
import de.edlly.key.client.dto.ClientWordMap;
import de.edlly.key.entities.store.WordMap;
import de.edlly.key.entities.wp.Posts;
import de.edlly.key.services.FetchPostsData;
import de.edlly.key.services.PostProcessor;
import de.edlly.key.services.WordCountMapperStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
public class ClientController {

    final FetchPostsData fetchPostsData;

    final PostProcessor postProcessor;

    final WordCountMapperStore store;

    public ClientController(FetchPostsData fetchPostsData, PostProcessor postProcessor, WordCountMapperStore store) {
        this.fetchPostsData = fetchPostsData;
        this.postProcessor = postProcessor;
        this.store = store;
    }

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
        return getWordMapData();
    }

    private Map<Long, ClientWordMap> getWordMapData() {
        Map<Long, ClientWordMap> data = new HashMap<>();
        store.getAllPostIdsInCache().forEach(id -> {
                    Optional<WordMap> wordMapOpt = store.getWordMap(id);
                    if (wordMapOpt.isPresent()) {
                        WordMap storeWordMap = wordMapOpt.get();
                        ClientWordMap clientWordMap = ClientWordMapAdapter.storeWordMapToClientWordMap(storeWordMap);
                        data.put(id, clientWordMap);
                    }
                }
        );
        return data;
    }

}