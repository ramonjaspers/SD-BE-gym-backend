package gym.gymbackend.service;

import gym.gymbackend.dto.ExerciseMuscleDto;
import gym.gymbackend.enums.Muscle;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.RecordNotFoundException;
import gym.gymbackend.model.Activity;
import gym.gymbackend.model.ExerciseMuscle;
import gym.gymbackend.repository.ActivityRepository;
import gym.gymbackend.repository.ExerciseMuscleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseMuscleImplementation implements ExerciseMuscleService {

    @Autowired
    private ExerciseMuscleRepository repos;
    @Autowired
    private ActivityRepository activityRepository;

    public boolean exerciseMuscleExists(Long id) {
        return repos.findById(id).isPresent();
    }

    @Override
    public List<ExerciseMuscle> getExerciseMuscles() {
        return repos.findAll();
    }

    @Override
    public ExerciseMuscle getExerciseMuscle(Long id) {
        if (!exerciseMuscleExists(id)) {
            throw new RecordNotFoundException();
        }
        return repos.findById(id).get();
    }

    @Override
    public List<ExerciseMuscle> getExerciseMuscleByActivity(String activityName) {
        Optional<Activity> activity = activityRepository.findById(activityName);
        if (activity.isEmpty()) {
            throw new RecordNotFoundException("Activity name does not exists");
        }
        return repos.findExerciseMuscleByActivity(activity.get());

    }

    @Override
    public void createExerciseMuscle(ExerciseMuscleDto muscleDto) {
        Optional<Activity> activity = activityRepository.findById(muscleDto.getActivity());
        if (activity.isEmpty()) {
            throw new RecordNotFoundException("Activity does not exists");
        }
        try {
            List<ExerciseMuscle> currExerciseMuscles = repos.findExerciseMuscleByActivity(activity.get());
            List<Muscle> muscles = muscleDto.getMuscles();
            //remove all already existing muscles from an activity in the request
            for (ExerciseMuscle currExerciseMuscle : currExerciseMuscles) {
                muscles.remove(currExerciseMuscle.getMuscle());
            }
            for (Muscle muscle : muscles) {
                ExerciseMuscle newMuscle = new ExerciseMuscle();
                newMuscle.setMuscle(muscle);
                newMuscle.setActivity(activity.get());
                repos.save(newMuscle);
            }
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create muscle. " + ex.getMessage());
        }
    }

    @Override
    public void deleteExerciseMuscle(Long id) {
        if (exerciseMuscleExists(id)) {
            repos.deleteById(id);
        } else {
            throw new RecordNotFoundException("Muscle does not exist");
        }
    }

    @Override
    public void deleteExerciseMuscleByActivity(String activityName) {
        Optional<Activity> activity = activityRepository.findById(activityName);
        if (activity.isEmpty()) {
            throw new RecordNotFoundException("Activity does not exists");
        }
        List<ExerciseMuscle> exerciseMuscles = repos.findExerciseMuscleByActivity(activity.get());
        repos.deleteAll(exerciseMuscles);
    }

    @Override
    public void updateExerciseMuscle(Long id, Muscle muscle) {
        if (!exerciseMuscleExists(id)) {
            throw new RecordNotFoundException("Muscle does not exists");
        }
        try {
            ExerciseMuscle exerciseMuscle = repos.findById(id).get();
            exerciseMuscle.setMuscle(muscle);
            repos.save(exerciseMuscle);
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create muscle. " + ex.getMessage());
        }
    }
}
