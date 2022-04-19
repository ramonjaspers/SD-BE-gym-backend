package gym.gymbackend.service;

import gym.gymbackend.dto.SubscriptionDto;
import gym.gymbackend.model.Exercise;
import gym.gymbackend.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    public List<SubscriptionDto> getMuscles();
    public SubscriptionDto getSubscription(String sub);
    public Subscription createSubscription(SubscriptionDto subDto);
    public Boolean deleteSubscription(String username);
    public Subscription updateSubscription(SubscriptionDto subDto);
}
