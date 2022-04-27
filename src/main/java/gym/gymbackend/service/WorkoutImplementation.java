package gym.gymbackend.service;

import gym.gymbackend.dto.WorkoutDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.PersonNotFoundException;
import gym.gymbackend.exceptions.RecordNotFoundException;
import gym.gymbackend.model.*;
import gym.gymbackend.repository.ActivityRepository;
import gym.gymbackend.repository.PersonRepository;
import gym.gymbackend.repository.PlannedActivityRepository;
import gym.gymbackend.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * workout implementation
 */
@Service
public class WorkoutImplementation implements WorkoutService {

    @Autowired
    private WorkoutRepository repos;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private PlannedActivityRepository plannedActivityRepository;


    /**
     * Check if Workout exists
     *
     * @param id id
     * @return {@link boolean}
     */
    public boolean workoutExists(Long id) {
        return repos.findById(id).isPresent();
    }

    /**
     * get all Workouts
     *
     * @return {@link List}
     */
    @Override
    public List<Workout> getWorkouts() {
        return repos.findAll();
    }

    /**
     * get all workouts from a person
     *
     * @param username username
     * @return {@link List}
     */
    @Override
    public List<Workout> getWorkoutsByPerson(String username) {
        Optional<Person> person = personRepository.findById(username);
        if (person.isEmpty()) {
            throw new PersonNotFoundException(username);
        }
        //TODO fix this and DELETES + PATCH, daarna eenmalig nog de requests doorlopen!
        return repos.findWorkoutsByPerson(person.get());
    }

    /**
     * get Workout by username
     *
     * @param id id
     */
    @Override
    public Workout getWorkout(Long id) {
        if (!workoutExists(id)) {
            throw new RecordNotFoundException("Workout does not exist");
        }
        return repos.findById(id).get();
    }

    /**
     * create Workout
     *
     * @param workoutDto workoutDto
     */
    @Override
    public void createWorkout(String username, WorkoutDto workoutDto) {
        Optional<Person> personOptional = personRepository.findById(username);
        // check for valid person
        if (personOptional.isEmpty()){
            throw new PersonNotFoundException(username);
        }

        Person person = personOptional.get();
        Membership membership = person.getSubscription().getMembership();
        List<Activity> activityList = new ArrayList<>();
        List<String> activityNames = workoutDto.getActivities();
        // Check if activities are set
        if (activityNames.size() < 1) {
            throw new BadRequestException("No activities for workout given");
        }

        for (String activityName : activityNames) {
            Optional<Activity> activityOptional = activityRepository.findById(activityName);
            // check if activity exists
            if (activityOptional.isEmpty()) {
                throw new BadRequestException("Activity " + activityName + " not found");
            }

            Activity activity = activityOptional.get();
            //Check if person has the right membership
            if (membership.getWeight() < activity.getFacility().getMinimumMembership().getWeight()) {
                throw new BadRequestException("Membership: " + activity.getFacility().getMinimumMembership().getName() + " is required for " + activityName);
            }

            //add activity into list to be later added
            activityList.add(activity);
        }

        try {
            Workout workout = new Workout();
            workout.setName(workoutDto.getName());
            workout.setPerson(person);
            repos.save(workout);
            for (Activity activity : activityList) {
                PlannedActivity plannedActivity = new PlannedActivity();
                plannedActivity.setActivity(activity);
                plannedActivity.setWorkout(workout);
                plannedActivityRepository.save(plannedActivity);
            }
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create workout: " + ex.getMessage());
        }
    }

    /**
     * update Workout by id
     *
     * @param id         id
     * @param workoutDto workoutDto
     */
    @Override
    public void updateWorkout(Long id, WorkoutDto workoutDto) {

    }

    /**
     * delete Workouts by username
     *
     * @param username username
     */
    @Override
    public void deleteWorkoutByUsername(String username) {

    }

    /**
     * delete Workout by id
     *
     * @param id id
     */
    @Override
    public void deleteWorkout(Long id) {

    }
}
