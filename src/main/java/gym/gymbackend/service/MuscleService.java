package gym.gymbackend.service;

import gym.gymbackend.dto.MuscleDto;
import gym.gymbackend.enums.Muscle;
import gym.gymbackend.model.Exercise;

import java.util.List;

public interface MuscleService {

    public List<MuscleDto> getMuscles();
    public MuscleDto getExercise(String person);
    public Muscle createExercise(MuscleDto muscleDto);
    public Boolean deleteExercise(String username);
    public Exercise updateExercise(MuscleDto muscleDto);
}
