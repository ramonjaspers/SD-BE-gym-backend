package gym.gymbackend.controller;

import gym.gymbackend.dto.WorkoutDto;
import gym.gymbackend.model.Workout;
import gym.gymbackend.service.WorkoutService;
import gym.gymbackend.utils.BindingResultErrorHandler;
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
@RequestMapping(value = "/workouts")
public class WorkoutController {

    WorkoutService service;

    @Autowired
    public WorkoutController(WorkoutService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Workout>> getWorkouts() {
        List<Workout> workout = service.getWorkouts();
        return new ResponseEntity<>(workout, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getWorkout(@PathVariable Long id) {
        try {
            Workout workout = service.getWorkout(id);
            return new ResponseEntity<>(workout, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No workout found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/person/{username}")
    public ResponseEntity<Object> getWorkoutsByPerson(@PathVariable String username) {
        try {
            List<Workout> workout = service.getWorkoutsByUsername(username);
            return new ResponseEntity<>(workout, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No workouts found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "person/{username}")
    public ResponseEntity<Object> createWorkout(@PathVariable String username, @Valid @RequestBody WorkoutDto workoutDto, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(BindingResultErrorHandler.bindingErrorsToString(br), HttpStatus.BAD_REQUEST);
        }
        try {
            service.createWorkout(username, workoutDto);
            return new ResponseEntity<>("Workout created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteWorkout(@PathVariable Long id) {
        try {
            service.deleteWorkout(id);
            return new ResponseEntity<>("Workout " + id + " removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/person/{username}")
    public ResponseEntity<Object> deleteWorkoutByPerson(@PathVariable String username) {
        try {
            service.deleteWorkoutByUsername(username);
            return new ResponseEntity<>("workouts from " + username + " removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/{id}/newName/{newName}")
    public ResponseEntity<Object> updateWorkoutName(@PathVariable Long id, @PathVariable String newName) {
        try {
            service.updateWorkoutName(id, newName);
            return new ResponseEntity<>("Workout name updated to " + newName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{id}/activity/{activity}")
    public ResponseEntity<Object> addActivityToWorkout(@PathVariable Long id, @PathVariable String activity) {
        try {
            service.addActivityToWorkout(id, activity);
            return new ResponseEntity<>(activity + " added to the workout", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}/activity/{activity}")
    public ResponseEntity<Object> removeActivityFromWorkout(@PathVariable Long id, @PathVariable String activity) {
        try {
            service.removeActivityFromWorkout(id, activity);
            return new ResponseEntity<>(activity + " removed from workout", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
