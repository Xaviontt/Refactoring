package blps.lab2.controller.topic;

import blps.lab2.dto.responses.topic.TopicView;
import blps.lab2.model.topic.Topic;
import blps.lab2.model.user.User;
import blps.lab2.service.rate.RateService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/rate")
public class RateController {
    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping("/{id}")
    public TopicView rateTopic(@PathVariable String id) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Topic topic = rateService.rateTopic(user.getId(), Long.parseLong(id));
            return TopicView.fromTopic(topic);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
