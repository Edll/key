package de.edlly.key.client.adapter;

import de.edlly.key.client.dto.ClientWordMap;
import de.edlly.key.entities.store.WordMap;
import de.edlly.key.entities.wp.Post;

import java.util.Map;

public class ClientWordMapAdapter {
    public static ClientWordMap storeWordMapToClientWordMap(WordMap storeWordMap) {
        ClientWordMap clientWordMap = new ClientWordMap();
        Post post = storeWordMap.getPost();
        if (post != null) {
            clientWordMap.setPostId(storeWordMap.getPost().getId());
            if (post.getDate() != null) {
                clientWordMap.setPostDate(post.getDate().getTime());
            }
            clientWordMap.setPostLink(post.getLink());
            if (post.getTitle() != null) {
                clientWordMap.setPostTitle(post.getTitle().getRendered());
            }
        }
        Map<String, Integer> words = storeWordMap.getWords();
        clientWordMap.setWordMap(words);
        clientWordMap.setWordCount(words.size());
        return clientWordMap;
    }
}
