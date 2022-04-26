package gym.gymbackend.repository;

import gym.gymbackend.model.Facility;
import gym.gymbackend.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findFacilitiesByMembership(Membership membership);
}
