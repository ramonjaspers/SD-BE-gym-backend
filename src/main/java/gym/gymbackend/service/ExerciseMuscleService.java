package gym.gymbackend.service;

import gym.gymbackend.dto.ExerciseMuscleDto;
import gym.gymbackend.enums.Muscle;
import gym.gymbackend.model.ExerciseMuscle;

import java.util.List;

public interface ExerciseMuscleService {
    /**
     * get exercise muscles
     *
     * @return {@link List}
     * @see List
     * @see ExerciseMuscle
     */
    List<ExerciseMuscle> getExerciseMuscles();

    /**
     * get exercise muscle by id
     *
     * @param id id
     * @return {@link ExerciseMuscle}
     * @see ExerciseMuscle
     */
    ExerciseMuscle getExerciseMuscle(Long id);

    /**
     * get exercise muscle by activity
     *
     * @param activityName activityName
     * @return {@link List}
     * @see List
     * @see ExerciseMuscle
     */
    List<ExerciseMuscle> getExerciseMuscleByActivity(String activityName);

    /**
     * create exercise muscle
     *
     * @param muscleDto muscleDto
     */
    void createExerciseMuscle(ExerciseMuscleDto muscleDto);

    /**
     * delete exercise muscle by id
     *
     * @param id id
     */
    void deleteExerciseMuscle(Long id);

    /**
     * delete exercise muscle by activity
     *
     * @param activityName activityName
     */
    void deleteExerciseMuscleByActivity(String activityName);

    /**
     * update exercise muscle by id
     *
     * @param id     id
     * @param muscle muscle
     */
    void updateExerciseMuscle(Long id, Muscle muscle);
}
