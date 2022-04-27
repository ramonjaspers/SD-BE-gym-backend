package gym.gymbackend.controller;

import gym.gymbackend.dto.ExerciseMuscleDto;
import gym.gymbackend.dto.MembershipDto;
import gym.gymbackend.dto.WorkoutDto;
import gym.gymbackend.enums.Muscle;
import gym.gymbackend.model.ExerciseMuscle;
import gym.gymbackend.model.Workout;
import gym.gymbackend.service.ExerciseMuscleService;
import gym.gymbackend.service.WorkoutService;
import org.hibernate.jdbc.Work;
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
            List<Workout> workout = service.getWorkoutsByPerson(username);
            return new ResponseEntity<>(workout, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No workouts found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "person/{username}")
    public ResponseEntity<Object> createWorkout(@PathVariable String username, @Valid @RequestBody WorkoutDto workoutDto, BindingResult br) {
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            service.createWorkout(username, workoutDto);
            return new ResponseEntity<>("Membership created", HttpStatus.CREATED);
        }catch (Exception e) {
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateWorkout(@PathVariable Long id, @Valid @RequestBody WorkoutDto workoutDto) {
        try {
            service.updateWorkout(id, workoutDto);
            return new ResponseEntity<>("Workout updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
