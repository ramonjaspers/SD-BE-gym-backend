package gym.gymbackend.repository;

import gym.gymbackend.model.Activity;
import gym.gymbackend.model.ExerciseMuscle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseMuscleRepository extends JpaRepository<ExerciseMuscle, Long> {
    List<ExerciseMuscle> findExerciseMuscleByActivity(Activity activity);
}
