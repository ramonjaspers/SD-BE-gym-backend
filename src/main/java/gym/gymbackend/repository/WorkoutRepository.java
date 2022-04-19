package gym.gymbackend.repository;

import gym.gymbackend.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, String> {}
