package gym.gymbackend.service;

import gym.gymbackend.dto.SubscriptionDto;
import gym.gymbackend.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getSubscriptions();

    Subscription getSubscription(String username);

    void createSubscription(String username, SubscriptionDto subscriptionDto);

    void updateSubscription(String username, SubscriptionDto subscriptionDto);

    void deleteSubscription(String username);
}
