package de.edlly.key.services;

import de.edlly.key.entities.store.WordMap;
import de.edlly.key.entities.wp.Post;
import de.edlly.key.entities.wp.PostContent;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {PostProcessor.class, WordCountMapperStore.class})
class PostProcessorTest {


    @Autowired
    private PostProcessor postProcessor;

    @Test
    void isPostToProcessed() {

        Post post = new Post();
        post.setModified(new Date());
        post.setId(1L);

        boolean postToProcessed = postProcessor.isPostToProcessed(post);
        assertTrue(postToProcessed);
    }

    @Test
    void isPostNotToProcessed() {
        Post post = new Post();
        post.setModified(new Date());
        post.setId(1L);
        WordMap wordMap = new WordMap();
        wordMap.setPost(post);
        PostProcessor _postProcessor = new PostProcessor(new WordCountMapperStore() {
            @Override
            public Optional<WordMap> getWordMap(Long postId) {
                return Optional.of(wordMap);
            }
        });


        boolean postToProcessed = _postProcessor.isPostToProcessed(post);
        assertFalse(postToProcessed);
    }

    @Test
    void processPost() {
        Post post = new Post();
        post.setModified(new Date());
        post.setId(1L);
        PostContent postContent = new PostContent();
        postContent.setRendered("Hello World");
        post.setPostContent(postContent);
        WordMap wordMap = new WordMap();
        wordMap.setPost(post);

        AtomicReference<WordMap> postRef = new AtomicReference<>();

        PostProcessor _postProcessor = new PostProcessor(new WordCountMapperStore() {
            @Override
            public void addWordMap(WordMap wordMap) {
                postRef.set(wordMap);
            }
        });

        _postProcessor.processPost(post);

        assertNotNull(postRef.get());

        assertEquals(1, postRef.get().getWords().get("hello"));
    }
}