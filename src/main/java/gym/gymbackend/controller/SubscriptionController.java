package gym.gymbackend.controller;

import gym.gymbackend.dto.SubscriptionDto;
import gym.gymbackend.model.Subscription;
import gym.gymbackend.service.SubscriptionService;
import gym.gymbackend.utils.BindingResultErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/subscriptions")
public class SubscriptionController {
    private final SubscriptionService service;

    @Autowired
    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Subscription>> getSubscriptions() {
        List<Subscription> subscriptions = service.getSubscriptions();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> getSubscription(@PathVariable String username) {
        try {
            Subscription subscription = service.getSubscription(username);
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No subscription found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{username}")
    public ResponseEntity<Object> createSubscription(@PathVariable String username, @Valid @RequestBody SubscriptionDto subscriptionDto, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(BindingResultErrorHandler.bindingErrorsToString(br), HttpStatus.BAD_REQUEST);
        }
        try {
            service.createSubscription(username, subscriptionDto);
            return new ResponseEntity<>("New subscription created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteSubscription(@PathVariable String username) {
        try {
            service.getSubscription(username);
            service.deleteSubscription(username);
            return new ResponseEntity<>("Subscription removed from: " + username, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<Object> updateSubscription(@PathVariable String username, @Valid @RequestBody SubscriptionDto subscriptionDto) {
        try {
            service.updateSubscription(username, subscriptionDto);
            return new ResponseEntity<>(" Subscription fields updated from: " + username, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
