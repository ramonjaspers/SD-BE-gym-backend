package gym.gymbackend.repository;

import gym.gymbackend.model.PlannedActivity;
import gym.gymbackend.model.PlannedActivityKey;
import gym.gymbackend.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlannedActivityRepository extends JpaRepository<PlannedActivity, PlannedActivityKey> {
    List<PlannedActivity> getPlannedActivitiesByWorkout(Workout workout);
}
