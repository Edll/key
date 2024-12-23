package de.edlly.key.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.edlly.key.entities.store.WordMap;
import de.edlly.key.services.listener.StoreListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class WordCountMapperStore {

    @Lazy
    private final List<StoreListener> storeListeners;

    // Cache to prevent OOM failure
    private final Cache<Long, WordMap> cache = CacheBuilder.newBuilder().maximumSize(10000).build();

    public WordCountMapperStore(List<StoreListener> storeListeners) {
        this.storeListeners = storeListeners;
    }

    public void addWordMap(WordMap wordMap) {
        cache.put(wordMap.getPost().getId(), wordMap);

        if(storeListeners != null) {
            storeListeners.forEach(StoreListener::onAddData);
        }
    }

    public Optional<WordMap> getWordMap(Long postId) {
        return Optional.ofNullable(cache.asMap().get(postId));
    }

    public List<Long> getAllPostIdsInCache(Long postId) {
        return new ArrayList<>(cache.asMap().keySet());
    }
}
