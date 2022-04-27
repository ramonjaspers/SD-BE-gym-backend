package gym.gymbackend.service;

import gym.gymbackend.dto.WorkoutDto;
import gym.gymbackend.model.Workout;

import java.util.List;

public interface WorkoutService {
    /**
     * get Workout
     *
     * @return {@link List}
     */
    List<Workout> getWorkouts();

    /**
     * get Workout by username
     *
     * @param username username
     * @return {@link List}
     */
    List<Workout> getWorkoutsByPerson(String username);

    /**
     * get Workout by username
     *
     * @param id id
     */
    Workout getWorkout(Long id);

    /**
     * create Workout
     *
     * @param workoutDto workoutDto
     */
    void createWorkout(String username, WorkoutDto workoutDto);

    /**
     * update Workout by id
     *
     * @param id         id
     * @param workoutDto workoutDto
     */
    void updateWorkout(Long id, WorkoutDto workoutDto);

    /**
     * delete Workouts by username
     *
     * @param username username
     */
    void deleteWorkoutByUsername(String username);

    /**
     * delete Workout by id
     *
     * @param id id
     */
    void deleteWorkout(Long id);
}
