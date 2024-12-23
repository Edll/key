package de.edlly.key.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.edlly.key.entities.store.WordMap;

import java.util.ArrayList;
import java.util.List;

public class WordCountMapperStore {

    // Cache to prevent OOM failure
    private final Cache<Long, WordMap> cache = CacheBuilder.newBuilder().maximumSize(10000).build();

    public void addWordMap(WordMap wordMap) {
        cache.put(wordMap.getPost().getId(), wordMap);
    }

    public WordMap getWordMap(Long postId) {
        return cache.asMap().get(postId);
    }

    public List<Long> getAllPostIdsInCache(Long postId) {
        return new ArrayList<>(cache.asMap().keySet());
    }
}
