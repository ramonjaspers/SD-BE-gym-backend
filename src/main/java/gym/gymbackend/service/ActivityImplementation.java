package gym.gymbackend.service;

import gym.gymbackend.dto.ActivityDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.RecordNotFoundException;
import gym.gymbackend.model.Activity;
import gym.gymbackend.model.Facility;
import gym.gymbackend.repository.ActivityRepository;
import gym.gymbackend.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityImplementation implements ActivityService {

    @Autowired
    private ActivityRepository repos;
    @Autowired
    private FacilityRepository facilityRepository;

    public boolean activityExists(String name) {
        return repos.findById(name).isPresent();
    }

    public List<Activity> getActivities() {
        return repos.findAll();
    }

    @Override
    public Activity getActivity(String name) {
        if (!activityExists(name)) {
            throw new RecordNotFoundException("Activity does not exist");
        }
        return repos.findById(name).get();
    }

    @Override
    public void createActivity(ActivityDto activityDto) {
        if (activityExists(activityDto.getName())) {
            throw new BadRequestException("Activity already exists");
        }
        Optional<Facility> facility = facilityRepository.findById(activityDto.getFacilityId());
        if (facility.isEmpty()) {
            throw new BadRequestException("Given facility does not exist");
        }
        try {
            Activity activity = new Activity();
            activity.setName(activityDto.getName());
            activity.setFacility(facility.get());
            repos.save(activity);
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create activity. " + ex.getMessage());
        }
    }

    @Override
    public void deleteActivity(String name) {
        if (activityExists(name)) {
            repos.deleteById(name);
        } else {
            throw new RecordNotFoundException("Activity does not exist");
        }
    }
}
