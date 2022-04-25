package gym.gymbackend.service;

import gym.gymbackend.dto.MembershipDto;
import gym.gymbackend.model.Membership;

import java.util.List;

public interface MembershipService {
    List<Membership> getMemberships();

    Membership getMembership(String username);

    void createMembership(MembershipDto membershipDto);

    void updateMembership(String name, MembershipDto membershipDto);

    void deleteMembership(String username);
}
