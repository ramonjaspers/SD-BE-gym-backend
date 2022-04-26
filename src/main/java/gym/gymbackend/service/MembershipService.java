package gym.gymbackend.service;

import gym.gymbackend.dto.MembershipDto;
import gym.gymbackend.model.Membership;

import java.util.List;

public interface MembershipService {
    /**
     * get memberships
     *
     * @return {@link List}
     * @see List
     * @see Membership
     */
    List<Membership> getMemberships();

    /**
     * get membership by username
     *
     * @param username username
     * @return {@link Membership}
     * @see Membership
     */
    Membership getMembership(String username);

    /**
     * create membership
     *
     * @param membershipDto membershipDto
     */
    void createMembership(MembershipDto membershipDto);

    /**
     * update membership by username
     *
     * @param username
     * @param membershipDto membershipDto
     */
    void updateMembership(String username, MembershipDto membershipDto);

    /**
     * delete membership by username
     *
     * @param username
     */
    void deleteMembership(String username);
}
