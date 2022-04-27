package gym.gymbackend.service;

import gym.gymbackend.dto.SubscriptionDto;
import gym.gymbackend.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    /**
     * get subscriptions
     *
     * @return {@link List}
     */
    List<Subscription> getSubscriptions();

    /**
     * get subscription by username
     *
     * @param username username
     * @return {@link Subscription}
     */
    Subscription getSubscription(String username);

    /**
     * create subscription by username
     *
     * @param username        username
     * @param subscriptionDto subscriptionDto
     */
    void createSubscription(String username, SubscriptionDto subscriptionDto);

    /**
     * update subscription by username
     *
     * @param username        username
     * @param subscriptionDto subscriptionDto
     */
    void updateSubscription(String username, SubscriptionDto subscriptionDto);

    /**
     * delete subscription by username
     *
     * @param username username
     */
    void deleteSubscription(String username);
}
