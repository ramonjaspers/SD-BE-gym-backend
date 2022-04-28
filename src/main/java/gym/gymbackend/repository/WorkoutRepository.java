package gym.gymbackend.repository;

import gym.gymbackend.model.Person;
import gym.gymbackend.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findWorkoutsByPerson(Person person);
}
