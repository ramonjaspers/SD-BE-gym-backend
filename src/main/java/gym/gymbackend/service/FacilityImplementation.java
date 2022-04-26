package gym.gymbackend.service;

import gym.gymbackend.dto.FacilityDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.RecordNotFoundException;
import gym.gymbackend.model.Facility;
import gym.gymbackend.model.Membership;
import gym.gymbackend.repository.FacilityRepository;
import gym.gymbackend.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityImplementation implements FacilityService {

    @Autowired
    private FacilityRepository repos;
    @Autowired
    private MembershipRepository membershipRepository;

    public boolean facilityExists(Long id) {
        return repos.findById(id).isPresent();
    }

    @Override
    public List<Facility> getFacilities() {
        return repos.findAll();
    }

    @Override
    public List<Facility> getFacilitiesByMembership(String membershipName) {
        Optional<Membership> membership = membershipRepository.findById(membershipName);
        if (membership.isEmpty()) {
            throw new RecordNotFoundException("Membership name does not exists");
        }
        return repos.findFacilitiesByMinimumMembership(membership.get());
    }


    @Override
    public Facility getFacility(Long id) {
        if (!facilityExists(id)) {
            throw new RecordNotFoundException("Facility does not exist");
        }
        return repos.findById(id).get();
    }

    @Override
    public void createFacility(FacilityDto facilityDto) {
        Optional<Membership> membership = membershipRepository.findById(facilityDto.getMinimumMembership());
        if (membership.isEmpty()) {
            throw new RecordNotFoundException("Membership does not exists");
        }
        try {
            Facility facility = new Facility();
            facility.setName(facilityDto.getName());
            facility.setMinimumMembership(membership.get());
            repos.save(facility);
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create facility. " + ex.getMessage());
        }
    }

    @Override
    public void deleteFacility(Long id) {
        if (facilityExists(id)) {
            repos.deleteById(id);
        } else {
            throw new RecordNotFoundException("Facility does not exist");
        }
    }

    @Override
    public void updateFacility(Long id, FacilityDto facilityDto) {
        if (!facilityExists(id)) {
            throw new RecordNotFoundException("Facility does not exist");
        }
        Optional<Membership> membership = membershipRepository.findById(facilityDto.getMinimumMembership());
        if (membership.isEmpty()) {
            throw new RecordNotFoundException("Membership does not exists");
        }
        Facility facility = repos.findById(id).get();
        facility.setName(facilityDto.getName());
        facility.setMinimumMembership(membership.get());
        repos.save(facility);
    }
}
