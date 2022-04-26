package gym.gymbackend.service;

import gym.gymbackend.dto.ExerciseMuscleDto;
import gym.gymbackend.enums.Muscle;
import gym.gymbackend.model.ExerciseMuscle;

import java.util.List;

public interface ExerciseMuscleService {
    List<ExerciseMuscle> getExerciseMuscles();

    ExerciseMuscle getExerciseMuscle(Long id);

    List<ExerciseMuscle> getExerciseMuscleByActivity(String activityName);

    void createExerciseMuscle(ExerciseMuscleDto muscleDto);

    void deleteExerciseMuscle(Long id);

    void deleteExerciseMuscleByActivity(String activityName);

    void updateExerciseMuscle(Long id, Muscle muscle);
}
