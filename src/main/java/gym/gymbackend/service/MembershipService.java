package gym.gymbackend.service;

import gym.gymbackend.dto.MembershipDto;
import gym.gymbackend.dto.SubscriptionDto;
import gym.gymbackend.model.Membership;
import gym.gymbackend.model.Subscription;

import java.util.List;

public interface MembershipService {
    public List<Membership> getMemberships();
    public Membership getMembership(String username);
    void createMembership(MembershipDto membershipDto);
    void updateMembership(String name, MembershipDto membershipDto);
    void deleteMembership(String username);
}
