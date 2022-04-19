package gym.gymbackend.service;

import gym.gymbackend.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutImplementation implements WorkoutService {

    private final WorkoutRepository repos;

    public WorkoutImplementation(WorkoutRepository repos) {
        this.repos = repos;
    }


}
