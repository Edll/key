package de.edlly.key;

import de.edlly.key.client.adapter.ClientWordMapAdapter;
import de.edlly.key.client.dto.ClientWordMap;
import de.edlly.key.entities.store.WordMap;
import de.edlly.key.services.FetchPostsData;
import de.edlly.key.services.PostProcessor;
import de.edlly.key.services.WordCountMapperStore;
import de.edlly.key.services.listener.IStoreListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class StoreAddElementListener implements IStoreListener {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final WordCountMapperStore store;

    public StoreAddElementListener(SimpMessagingTemplate simpMessagingTemplate, @Lazy WordCountMapperStore store) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.store = store;
    }

    @Override
    public void onAddData() {
        log.info("new Data Added to store");

        simpMessagingTemplate.convertAndSend("/topic/update-data", getWordMapData());
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
