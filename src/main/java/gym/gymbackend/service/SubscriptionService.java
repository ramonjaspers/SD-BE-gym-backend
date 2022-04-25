package gym.gymbackend.service;

import gym.gymbackend.dto.SubscriptionDto;
import gym.gymbackend.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    public List<Subscription> getSubscriptions();
    public SubscriptionDto getSubscription(String username);
    public Subscription createSubscription(SubscriptionDto subDto);
    public Subscription updateSubscription(SubscriptionDto subDto);
    public Boolean deleteSubscription(String username);
}
