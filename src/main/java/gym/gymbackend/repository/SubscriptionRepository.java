package gym.gymbackend.repository;

import gym.gymbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Person, String> {}
