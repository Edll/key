package de.edlly.key.services;


import de.edlly.key.entities.store.WordMap;
import de.edlly.key.entities.wp.Post;
import de.edlly.key.entities.wp.Posts;
import de.edlly.key.libary.PostHtmlCleaner;
import de.edlly.key.libary.WordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class PostProcessor {

    private final WordCountMapperStore wordCountMapperStore;

    public PostProcessor(@Autowired WordCountMapperStore wordCountMapperStore) {
        this.wordCountMapperStore = wordCountMapperStore;
    }

    public void process(Posts posts) {
        posts.forEach(post -> {
            boolean processPost = isPostToProcessed(post);

            if(processPost) {
                processPost(post);
            }
        });
    }

    protected boolean isPostToProcessed(Post post) {
        boolean processPost = false;
        Optional<WordMap> wordMap = wordCountMapperStore.getWordMap(post.getId());
        if (wordMap.isPresent()) {
          if(  wordMap.get().getPost().getModified().getTime() != post.getModified().getTime()){
              processPost = true;
          }
        }else{
            processPost = true;
        }
        return processPost;
    }

    protected void processPost(Post post) {
        PostHtmlCleaner postHtmlCleaner = new PostHtmlCleaner();
        Optional<String> cleanStringOpt = postHtmlCleaner.cleanPosts(post);

        if(cleanStringOpt.isEmpty()) {
            log.error("Post {} has no clean text", post.getId());
            return;
        }

        String cleanString = cleanStringOpt.get();
        WordMapper wordMapper = new WordMapper();
        Map<String, Integer> mappedWords =
                wordMapper.getWordMap(cleanString);

        WordMap wordMap = new WordMap();
        wordMap.setPost(post);
        wordMap.setWords(mappedWords);

        wordCountMapperStore.addWordMap(wordMap);
    }

}
