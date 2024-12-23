package de.edlly.key.services;

import de.edlly.key.entities.store.WordMap;
import de.edlly.key.entities.wp.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = WordCountMapperStore.class)
class WordCountMapperStoreTest {

    @Autowired
    WordCountMapperStore store;

    @Test
    public void testAddPostAndGet() {
        WordMap wordMap = createDummyWordMapEntity();

        store.addWordMap(wordMap);

        Optional<WordMap> actual = store.getWordMap(1L);
        assertNotNull(actual);
        assertTrue(actual.isPresent());
        assertEquals(1L, actual.get().getPost().getId());
    }

    @Test
    public void testAddPostAndNotFound() {
        WordMap wordMap = createDummyWordMapEntity();

        store.addWordMap(wordMap);

        Optional<WordMap> actual = store.getWordMap(2L);
        assertNotNull(actual);
        assertFalse(actual.isPresent());
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