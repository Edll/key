package de.edlly.key.services;

import de.edlly.key.entities.store.WordMap;
import de.edlly.key.entities.wp.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {WordCountMapperStore.class, DummyStoreListener.class})
class WordCountMapperStoreUpdateDataListenerTest {

    @Autowired
    WordCountMapperStore store;

    @Autowired
    DummyStoreListener listener;


    @Test
    public void testUpdateDataListener() {
        WordMap wordMap = createDummyWordMapEntity();

        assertFalse(listener.isCalled());
        store.addWordMap(wordMap);
        assertTrue(listener.isCalled());
    }

    private WordMap createDummyWordMapEntity() {
        Post post = new Post();
        post.setId(1L);
        WordMap wordMap = new WordMap();
        wordMap.setPost(post);
        wordMap.getWords().put("and", 1);
        return wordMap;
    }

}