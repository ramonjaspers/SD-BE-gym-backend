package gym.gymbackend.service;

import gym.gymbackend.dto.ActivityDto;
import gym.gymbackend.model.Activity;

import java.util.List;

public interface ActivityService {
    /**
     * get activities
     *
     * @return {@link List}
     */
    List<Activity> getActivities();

    /**
     * get activity by name
     *
     * @param name name
     * @return {@link Activity}
     */
    Activity getActivity(String name);

    /**
     * create activity
     *
     * @param activityDto activityDto
     */
    void createActivity(ActivityDto activityDto);

    /**
     * delete activity by name
     *
     * @param name name
     */
    void deleteActivity(String name);
}
