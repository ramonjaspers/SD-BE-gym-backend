package gym.gymbackend.repository;

import gym.gymbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Employee, String> {}
