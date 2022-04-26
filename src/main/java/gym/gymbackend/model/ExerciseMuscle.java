package gym.gymbackend.model;

import gym.gymbackend.enums.Muscle;

import javax.persistence.*;

@Entity
public class ExerciseMuscle {
    // Multiple muscles can belong to mutliple exercises
    @ManyToOne
    @JoinColumn(name = "activity")
    Activity activity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Muscle muscle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Muscle getMuscle() {
        return muscle;
    }

    public void setMuscle(Muscle muscle) {
        this.muscle = muscle;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activities) {
        this.activity = activities;
    }
}
