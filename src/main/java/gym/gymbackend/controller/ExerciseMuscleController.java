package gym.gymbackend.controller;

import gym.gymbackend.dto.ExerciseMuscleDto;
import gym.gymbackend.enums.Muscle;
import gym.gymbackend.model.ExerciseMuscle;
import gym.gymbackend.service.ExerciseMuscleService;
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
@RequestMapping(value = "/exerciseMuscles")
public class ExerciseMuscleController {

    ExerciseMuscleService service;

    @Autowired
    public ExerciseMuscleController(ExerciseMuscleService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<ExerciseMuscle>> getExerciseMuscles() {
        List<ExerciseMuscle> exerciseMuscles = service.getExerciseMuscles();
        return new ResponseEntity<>(exerciseMuscles, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getExerciseMuscle(@PathVariable Long id) {
        try {
            ExerciseMuscle exerciseMuscle = service.getExerciseMuscle(id);
            return new ResponseEntity<>(exerciseMuscle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No muscle found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteExerciseMuscle(@PathVariable Long id) {
        try {
            service.deleteExerciseMuscle(id);
            return new ResponseEntity<>(id + " removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateExerciseMuscle(@PathVariable Long id, @Valid @RequestBody Muscle muscle) {
        try {
            service.updateExerciseMuscle(id, muscle);
            return new ResponseEntity<>("Muscle updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/activity/{activityName}")
    public ResponseEntity<Object> getExerciseMuscleByActivity(@PathVariable String activityName) {
        try {
            List<ExerciseMuscle> exerciseMuscles = service.getExerciseMuscleByActivity(activityName);
            return new ResponseEntity<>(exerciseMuscles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createExerciseMuscle(@Valid @RequestBody ExerciseMuscleDto exerciseMuscleDto, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(BindingResultErrorHandler.bindingErrorsToString(br), HttpStatus.BAD_REQUEST);
        }
        try {
            service.createExerciseMuscle(exerciseMuscleDto);
            return new ResponseEntity<>("Exercise muscle created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping(value = "/activity/{activityName}")
    public ResponseEntity<Object> deleteExerciseMuscleByActivity(@PathVariable String activityName) {
        try {
            service.deleteExerciseMuscleByActivity(activityName);
            return new ResponseEntity<>("Exercise muscles removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
