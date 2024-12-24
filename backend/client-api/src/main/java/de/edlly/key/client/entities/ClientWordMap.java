package de.edlly.key.client.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ClientWordMap {
    private Long postId;
    private String postTitle;
    private String postAuthor;
    private Long postDate;
    private String postLink;
    private Map<String, Integer> wordMap;
}
