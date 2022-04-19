package gym.gymbackend.service;

import gym.gymbackend.dto.FacilityDto;
import gym.gymbackend.model.Facility;

import java.util.List;

public interface FacilityService {

    public List<Facility> getFacilities();
    public FacilityDto getFacility(String Facility);
    public Facility createExercise(FacilityDto facilityDto);
    public Boolean deleteExercise(String name);
    public Facility updateExercise(FacilityDto facilityDto);
}
