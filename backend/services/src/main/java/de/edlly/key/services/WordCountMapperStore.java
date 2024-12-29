package de.edlly.key.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.edlly.key.entities.store.WordMap;
import de.edlly.key.services.listener.IStoreListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class WordCountMapperStore {

    @Lazy
    private final List<IStoreListener> IStoreListeners;

    // Cache to prevent OOM failure
    private final Cache<Long, WordMap> cache = CacheBuilder.newBuilder().maximumSize(10000).build();

    public WordCountMapperStore(List<IStoreListener> IStoreListeners) {
        this.IStoreListeners = IStoreListeners;
    }

    public void addWordMap(WordMap wordMap) {
        cache.put(wordMap.getPost().getId(), wordMap);

        if(IStoreListeners != null) {
            IStoreListeners.forEach(IStoreListener::onAddData);
        }
    }

    public Optional<WordMap> getWordMap(Long postId) {
        return Optional.ofNullable(cache.asMap().get(postId));
    }

    public List<Long> getAllPostIdsInCache() {
        return new ArrayList<>(cache.asMap().keySet());
    }
}
