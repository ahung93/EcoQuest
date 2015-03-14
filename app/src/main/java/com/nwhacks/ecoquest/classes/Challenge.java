package com.nwhacks.ecoquest.classes;

import android.graphics.Bitmap;

import java.sql.Timestamp;
import java.lang.System;
import java.util.ArrayList;

public class Challenge {
    public enum State {
        OPEN,
        IN_PROGRESS,
        COMPLETED,
        FAILED
    }

    public enum Type {
        TRANSPORTATION,
        NUTRITION,
        WASTE_AND_ENERGY_REDUCTION,
    }

    private State currentState;

    private String title;
    private String description;
    private Type type;

    private int rewardPoints;
    private int penaltyPoints;

    private int currentProgress;
    private ArrayList<Bitmap> photos;

    private Timestamp startTime;
    private Timestamp endTime;

    private User currentUser;

    // Default Constructor for all fields
    public Challenge(State currentState, String title, String description, Type type, int progress, int reward, int penalty, ArrayList<Bitmap> photos, Timestamp startTime, Timestamp endTime, User user) {
        this.currentState = currentState;
        this.title = title;
        this.description = description;
        this.type = type;
        this.currentProgress = progress;
        this.rewardPoints = reward;
        this.penaltyPoints = penalty;
        this.photos = photos;
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentUser = user;
    }

    // Constructor for the Challenge Class (OPEN)
    public void Challenge(State currentState, String newTitle, String newDescription, Type newType, int newReward, int newPenalty){
        this.currentState = currentState;
        this.title = newTitle;
        this.description = newDescription;
        this.type = newType;
        this.rewardPoints = newReward;
        this.penaltyPoints = newPenalty;
        this.currentProgress = 0;
    }

    //===========================================================
    // Mutator Methods
    public void startChallenge(User user) throws UnExpectedStateException{
        if (currentState != State.OPEN)
            throw new UnExpectedStateException();
        startTime = new Timestamp(System.currentTimeMillis());
        currentState = State.IN_PROGRESS;
        currentUser = user;
    }

    public void updateChallenge(int newProgress, Bitmap photo) {
        photos.add(photo);
        currentProgress = newProgress;
        if (currentProgress >= 100) {
            completeChallenge();
        }
    }

    public void completeChallenge(){
        endTime = new Timestamp(System.currentTimeMillis());
        currentState = State.COMPLETED;
        currentUser.updateTotalPoints();
    }

    public void completeChallenge(Bitmap photo){
        photos.add(photo);
        endTime = new Timestamp(System.currentTimeMillis());
        currentState = State.COMPLETED;
        currentUser.updateTotalPoints();
    }

    public void failChallenge(){
        currentState = State.FAILED;
        currentUser.updateTotalPoints();
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

    public Type getType(){
        return type;
    }

    public int getCurrentProgress(){
        return currentProgress;
    }

    public int getRewardPoints(){
        return rewardPoints;
    }

    public int getPenaltyPoints(){
        return penaltyPoints;
    }

    public long getStartTime(){
        return startTime.getTime();
    }

    public long getEndTime(){
        return endTime.getTime();
    }
}
