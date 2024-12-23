
package de.edlly.key.entities.wp;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Post {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("content")
    private PostContent postContent;

    @JsonProperty("modified")
    private Date modified;

    @JsonProperty("date")
    private Date date;

}
