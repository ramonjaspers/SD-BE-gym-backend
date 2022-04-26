package gym.gymbackend.controller;

import gym.gymbackend.dto.ActivityDto;
import gym.gymbackend.model.Activity;
import gym.gymbackend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/activities")
public class ActivityController {

    ActivityService service;

    @Autowired
    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Activity>> getActivities() {
        List<Activity> activities = service.getActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @GetMapping(value = "/{activityName}")
    public ResponseEntity<Object> getActivity(@PathVariable String activityName) {
        try {
            Activity activities = service.getActivity(activityName);
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No activity found", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "")
    public ResponseEntity<Object> createActivity(@Valid @RequestBody ActivityDto activityDto, BindingResult br) {
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            service.createActivity(activityDto);
            return new ResponseEntity<>("Activity created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping(value = "/{activityName}")
    public ResponseEntity<Object> deleteActivity(@PathVariable String activityName) {
        try {
            service.deleteActivity(activityName);
            return new ResponseEntity<>("Activity removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed removing activity, check if there are Exercise muscles connected to this activity and remove these first", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{activityName}")
    public ResponseEntity<Object> updateActivity(@PathVariable String activityName, @Valid @RequestBody ActivityDto activityDto) {
        service.updateActivity(activityName, activityDto);
        return new ResponseEntity<>( " fields updated", HttpStatus.OK);
    }

}
