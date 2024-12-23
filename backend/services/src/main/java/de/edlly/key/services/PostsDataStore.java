package de.edlly.key.services;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.edlly.key.entities.wp.Post;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class PostsDataStore {

    // Cache to prevent OOM failure
    private final Cache<Long, Post> cache = CacheBuilder.newBuilder().maximumSize(10000).build();

    public void addPost(Post post) {
        cache.put(post.getId(), post);
    }

    public Stream<Post> getPosts() {
        return cache.asMap().values().stream();
    }

}
