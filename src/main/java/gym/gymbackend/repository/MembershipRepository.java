package gym.gymbackend.repository;

import gym.gymbackend.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, String> {}
