package de.edlly.key.client.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ClientWordMap {
    private Long postId;
    private Map<String, Integer> wordMap;
}
