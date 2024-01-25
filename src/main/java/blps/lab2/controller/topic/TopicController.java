package blps.lab2.controller.topic;

import blps.lab2.dto.requests.topic.CreateTopicRequest;
import blps.lab2.dto.responses.topic.TopicView;
import blps.lab2.dto.responses.topic.TopicViewPage;
import blps.lab2.model.topic.Topic;
import blps.lab2.model.topic.TopicCategory;
import blps.lab2.model.user.User;
import blps.lab2.service.topic.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/topic")
public class TopicController {
    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";
    private static final String DEFAULT_SORT_BY = "id";
    private static final String DEFAULT_SORT_DIRECTION = "asc";

    private final TopicService topicService;


    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public TopicViewPage searchAllByQuery(
            @RequestParam(value = "query", defaultValue = "", required = false) String query,
            @RequestParam(value = "category", defaultValue = "", required = false) String category,
            @RequestParam(value = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        try {
            Optional<TopicCategory> optionalCategory;
            if (category.isEmpty()) {
                optionalCategory = Optional.empty();
            } else {
                optionalCategory = Optional.of(TopicCategory.valueOf(category.toUpperCase()));
            }
            return topicService.findByQuery(query, pageNumber, pageSize, sortBy, sortDir, optionalCategory);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Topic getTopic(@PathVariable String id) {
        try {
            Optional<Topic> topic = topicService.findById(Long.parseLong(id));
            return topic.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public TopicView createTopic(@RequestBody @Valid CreateTopicRequest req) {
        try {
            Date currentDate = new Date();
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Topic topic = topicService.save(
                    new Topic(
                            req.getTitle(),
                            req.getDescription(),
                            req.getContent(),
                            TopicCategory.valueOf(req.getCategory().toUpperCase()),
                            currentDate,
                            currentDate,
                            0,
                            user
                    )
            );
            return TopicView.fromTopic(topic);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no such topic category");
        }
    }

    @PutMapping("/{id}")
    public Topic updateTopic(@PathVariable String id, @RequestBody CreateTopicRequest req) {
        try {
            Topic topic = topicService.update(Long.parseLong(id), req);
            if (topic == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return topic;
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public Long deleteTopic(@PathVariable String id) {
        try {
            Optional<Long> topicId = topicService.delete(Long.parseLong(id));
            return topicId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}