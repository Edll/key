package de.edlly.key.libary;

import de.edlly.key.entities.wp.Post;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;

import java.util.Optional;

@Slf4j
public class PostHtmlCleaner {

    /**
     * remove all none Word content
     *
     * @param post post object
     * @return clean word String
     */
    public Optional<String> cleanPosts(Post post) {
        if (post == null || post.getPostContent() == null || post.getPostContent().getRendered() == null) {
            log.warn("Post content is null or empty");
            return Optional.empty();
        }
        String rendered = post.getPostContent().getRendered();
        return cleanPostString(rendered);
    }

    protected Optional<String> cleanPostString(String rendered) {

        String text = Jsoup.parse(rendered).text();
        log.debug(text);
        return Optional.of(text);
    }
}
