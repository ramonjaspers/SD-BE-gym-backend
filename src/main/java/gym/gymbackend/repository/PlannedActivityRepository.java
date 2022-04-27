package gym.gymbackend.repository;

import gym.gymbackend.model.PlannedActivity;
import gym.gymbackend.model.PlannedActivityKey;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlannedActivityRepository extends JpaRepository<PlannedActivity, PlannedActivityKey> {
}
