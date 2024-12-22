package de.edlly.key;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WordMapperTest {

    @Test
    void getWordMap() throws Exception {
        URL resourceCleanExpected = getClass().getResource("/cleanPostResult.txt");
        assertNotNull(resourceCleanExpected);
        String cleanExpected = Files.readString(Paths.get(resourceCleanExpected.toURI()), StandardCharsets.UTF_8);

        WordMapper wordMapper = new WordMapper();
        Map<String, Integer> wordMap = wordMapper.getWordMap(cleanExpected);
        assertNotNull(wordMap);
        assertEquals(397, wordMap.size());
        assertEquals(1, wordMap.get("weihnachtskarten"));
        assertEquals(30, wordMap.get("und"));

    }
}