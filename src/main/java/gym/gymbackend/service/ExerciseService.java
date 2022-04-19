package gym.gymbackend.service;

import gym.gymbackend.dto.ExerciseDto;
import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.model.Exercise;

import java.util.List;

public interface ExerciseService {

    public List<ExerciseDto> getExercises();
    public ExerciseDto getExercise(String person);
    public Exercise createExercise(PersonDto employeeDto);
    public Boolean deleteExercise(String username);
    public Exercise updateExercise(PersonDto employeeDto);
}
