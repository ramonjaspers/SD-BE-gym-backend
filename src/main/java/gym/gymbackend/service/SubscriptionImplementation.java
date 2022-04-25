package gym.gymbackend.service;

import gym.gymbackend.dto.SubscriptionDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.PersonNotFoundException;
import gym.gymbackend.exceptions.RecordNotFoundException;
import gym.gymbackend.model.Membership;
import gym.gymbackend.model.Person;
import gym.gymbackend.model.Subscription;
import gym.gymbackend.repository.MembershipRepository;
import gym.gymbackend.repository.PersonRepository;
import gym.gymbackend.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionImplementation implements SubscriptionService {

    @Autowired
    private SubscriptionRepository repos;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    public boolean subscriptionExists(String username) {
        return repos.findById(username).isPresent();
    }

    @Override
    public List<Subscription> getSubscriptions() {
        return repos.findAll();
    }

    @Override
    public Subscription getSubscription(String username) {
        if (!subscriptionExists(username)) {
            throw new RecordNotFoundException("Subscription does not exist");
        }
        return repos.findById(username).get();
    }

    @Override
    public void createSubscription(String username, SubscriptionDto subscriptionDto) {
        if (subscriptionExists(username)) {
            throw new BadRequestException("Membership already exists");
        }
        Optional<Person> personOptional = personRepository.findById(username);
        if (personOptional.isEmpty()) {
            throw new PersonNotFoundException(username);
        }
        Optional<Membership> membership = membershipRepository.findById(subscriptionDto.getMembership());
        if (membership.isEmpty()) {
            throw new RecordNotFoundException("Membership does not exists");
        }
        try {
            Subscription subscription = new Subscription();
            subscription.setMembership(membership.get());
            subscription.setSubscriptionEndDate(subscriptionDto.getSubscriptionEndDate());
            subscription.setSubscriptionStartDate(subscriptionDto.getSubscriptionStartDate());
            subscription.setPerson(personOptional.get());

            repos.save(subscription);
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create subscription. " + ex.getMessage());
        }
    }

    @Override
    public void updateSubscription(String username, SubscriptionDto subscriptionDto) {
        if (!subscriptionExists(username)) {
            throw new RecordNotFoundException("Person has no subscription");
        }
        Optional<Membership> membership = membershipRepository.findById(subscriptionDto.getMembership());
        if (membership.isEmpty()) {
            throw new RecordNotFoundException("Membership does not exists");
        }
        Subscription subscription = repos.findById(username).get();
        subscription.setMembership(membership.get());
        subscription.setSubscriptionEndDate(subscriptionDto.getSubscriptionEndDate());
        subscription.setSubscriptionStartDate(subscriptionDto.getSubscriptionStartDate());
        repos.save(subscription);
    }

    @Override
    public void deleteSubscription(String username) {
        if (subscriptionExists(username)) {
            repos.deleteById(username);
        } else {
            throw new RecordNotFoundException("Person has no subscription");
        }
    }
}
