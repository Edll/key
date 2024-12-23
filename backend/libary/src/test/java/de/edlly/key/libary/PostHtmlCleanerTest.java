package de.edlly.key.libary;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PostHtmlCleanerTest {

    @Test
    void cleanPosts() throws Exception {
        PostHtmlCleaner postHtmlCleaner = new PostHtmlCleaner();
        URL resource = getClass().getResource("/firstpostContent.txt");
        assertNotNull(resource);
        String strings = Files.readString(Paths.get(resource.toURI()), StandardCharsets.UTF_8);

        Optional<String> actual = postHtmlCleaner.cleanPostString(strings);

        assertTrue(actual.isPresent());
        assertNotNull(actual.get());

        URL resourceCleanExpected = getClass().getResource("/cleanPostResult.txt");
        assertNotNull(resourceCleanExpected);
        String cleanExpected = Files.readString(Paths.get(resourceCleanExpected.toURI()), StandardCharsets.UTF_8);

        assertEquals(cleanExpected, actual.get());
    }
}