package gym.gymbackend.service;

import gym.gymbackend.dto.FacilityDto;
import gym.gymbackend.model.Facility;

import java.util.List;

public interface FacilityService {
    /**
     * get facilities
     *
     * @return {@link List}
     */
    List<Facility> getFacilities();

    /**
     * get facility by id
     *
     * @param id id
     * @return {@link Facility}
     */
    Facility getFacility(Long id);

    /**
     * get facilities by membership
     *
     * @param membership membership
     * @return {@link List}
     */
    List<Facility> getFacilitiesByMembership(String membership);

    /**
     * create facility
     *
     * @param facilityDto facilityDto
     */
    void createFacility(FacilityDto facilityDto);

    /**
     * delete facility by id
     *
     * @param id id
     */
    void deleteFacility(Long id);

    /**
     * update facility by id
     *
     * @param id          id
     * @param facilityDto facilityDto
     */
    void updateFacility(Long id, FacilityDto facilityDto);
}
