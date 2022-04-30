package gym.gymbackend.service;

import gym.gymbackend.dto.MembershipDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.RecordNotFoundException;
import gym.gymbackend.model.Membership;
import gym.gymbackend.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipImplementation implements MembershipService {

    @Autowired
    private MembershipRepository repos;

    public boolean membershipExists(String name) {
        return repos.findById(name).isPresent();
    }

    @Override
    public Membership getMembership(String name) {
        if (!membershipExists(name)) {
            throw new RecordNotFoundException("Membership does not exist");
        }
        return repos.findById(name).get();
    }

    @Override
    public List<Membership> getMemberships() {
        return repos.findAll();
    }

    @Override
    public void createMembership(MembershipDto membershipDto) {
        if (membershipExists(membershipDto.getName())) {
            throw new BadRequestException("Membership already exists");
        }
        try {
            Membership membership = new Membership();
            membership.setName(membershipDto.getName());
            membership.setPrice(membershipDto.getPrice());
            membership.setWeight(membershipDto.getWeight());
            repos.save(membership);
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create employee. " + ex.getMessage());
        }
    }

    @Override
    public void deleteMembership(String name) {
        if (membershipExists(name)) {
            repos.deleteById(name);
        } else {
            throw new RecordNotFoundException("Membership does not exist");
        }
    }

    @Override
    public void updateMembership(MembershipDto membershipDto) {
        Membership membership = getMembership(membershipDto.getName());
        if (!membershipExists(membershipDto.getName())) {
            throw new RecordNotFoundException("Membership does not exist");
        }
        membership.setPrice(membershipDto.getPrice());
        membership.setWeight(membershipDto.getWeight());
        repos.save(membership);
    }
}
