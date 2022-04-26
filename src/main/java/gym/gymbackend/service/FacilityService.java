package gym.gymbackend.service;

import gym.gymbackend.dto.FacilityDto;
import gym.gymbackend.model.Facility;

import java.util.List;

public interface FacilityService {
    List<Facility> getFacilities();

    Facility getFacility(Long id);

    List<Facility> getFacilitiesByMembership(String membership);

    void createFacility(FacilityDto facilityDto);

    void deleteFacility(Long id);

    void updateFacility(Long id, FacilityDto facilityDto);
}
