package gym.gymbackend.repository;

import gym.gymbackend.model.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employment, String> {}
