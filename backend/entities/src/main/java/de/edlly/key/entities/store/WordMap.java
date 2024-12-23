package de.edlly.key.entities.store;

import de.edlly.key.entities.wp.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class WordMap {
    private Post post;
    private Map<Integer, String> words = new HashMap<>();
}
