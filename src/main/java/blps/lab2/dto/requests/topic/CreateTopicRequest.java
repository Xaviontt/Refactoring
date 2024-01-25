package blps.lab2.dto.requests.topic;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateTopicRequest implements Serializable {
    private String title;
    private String description;
    private String content;
    private String category;
}
