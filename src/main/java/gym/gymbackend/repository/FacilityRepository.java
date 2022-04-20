package gym.gymbackend.repository;

import gym.gymbackend.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, String> {}
