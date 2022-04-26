package gym.gymbackend.service;

import gym.gymbackend.dto.ActivityDto;
import gym.gymbackend.model.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> getActivities();

    Activity getActivity(String name);

    void createActivity(ActivityDto activityDto);

    void deleteActivity(String name);

    void updateActivity(String name, ActivityDto activityDto);
}
