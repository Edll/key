package de.edlly.key.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.edlly.key.entities.wp.Posts;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostsDeserializeTest {

    @Test
    public void basicDeserializePostResponse() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        URL resource = getClass().getResource("/posts.json");

        assertNotNull(resource);

        File file = new File(resource.getFile());

        Posts posts = objectMapper.readValue(file, Posts.class);

        assertNotNull(posts);
        assertNotNull(posts.get(0).getPostContent().getRendered());
    }
}
