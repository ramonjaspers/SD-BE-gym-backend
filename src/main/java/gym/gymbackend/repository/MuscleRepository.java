package gym.gymbackend.repository;

import gym.gymbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuscleRepository extends JpaRepository<Employee, String> {}
