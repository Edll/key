package de.edlly.key.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class PostContent {
    @JsonProperty("rendered")
    private String rendered;

    @JsonProperty("protected")
    private Boolean protectedContent;
}
