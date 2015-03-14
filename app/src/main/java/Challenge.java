import java.sql.Timestamp;
import java.lang.System;

public class Challenge {
    public enum State {
        OPEN,
        IN_PROGRESS,
        COMPLETED,
    DROPPED
}
    private State currentState;

    private String title;
    private String description;
    private String type;

    private int progress;
    private int reward;
    private int penalty;

    private Timestamp startTime;
    private Timestamp endTime;

    // Constructor for the Challenge Class
    public void Challenge(String newTitle, String newDescription, String newType, int rewardPoints, int penaltyPoints){
        title = newTitle;
        description = newDescription;
        type = newType;
        reward = rewardPoints;
        penalty = penaltyPoints;
        progress = 0;
        currentState = State.OPEN;
    }

    //===========================================================
    // Mutator Methods
    public void startChallenge(){
        startTime = new Timestamp(System.currentTimeMillis());
        currentState = State.IN_PROGRESS;
    }

    public void dropChallenge(){
        currentState = State.DROPPED;
    }
    public void endChallenge(){
        endTime = new Timestamp(System.currentTimeMillis());
        currentState = State.COMPLETED;
    }

    //===========================================================
    // Accessor Methods
    public State getCurrentState(){
        return currentState;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getType(){
        return type;
    }

    public int getProgress(){
        return progress;
    }

    public int getReward(){
        return reward;
    }

    public int getPenalty(){
        return penalty;
    }

    public long getStartTime(){
        return startTime.getTime();
    }

    public long getEndTime(){
        return endTime.getTime();
    }
}
