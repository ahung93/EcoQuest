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
    private long timeLimit;

    private User currentUser;

    // Default Constructor for all fields
    public Challenge(State currentState, String title, String description, Type type, int progress, long limit, int reward, int penalty, ArrayList<Bitmap> photos, Timestamp startTime, Timestamp endTime, User user) {
        this.currentState = currentState;
        this.title = title;
        this.description = description;
        this.type = type;
        this.currentProgress = progress;
        this.timeLimit = limit;
        this.rewardPoints = reward;
        this.penaltyPoints = penalty;
        this.photos = photos;
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentUser = user;
    }

    // Constructor for the Challenge Class (OPEN)
    public Challenge(State currentState, String newTitle, String newDescription, Type newType, long limit, int newReward, int newPenalty){
        this.currentState = currentState;
        this.title = newTitle;
        this.description = newDescription;
        this.type = newType;
        this.timeLimit = limit;
        this.rewardPoints = newReward;
        this.penaltyPoints = newPenalty;
        this.currentProgress = 0;
    }

    // For testing purposes
    public Challenge(State currentState, String newTitle, String newDescription, Type newType, int newReward, int newPenalty){
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
    public void startChallenge(User user) { // throws UnExpectedStateException{
        //if (currentState != State.OPEN)
            //throw new UnExpectedStateException();
        startTime = new Timestamp(System.currentTimeMillis());
        currentState = State.IN_PROGRESS;
        currentUser = user;
    }

    public void updateChallenge(int newProgress, Bitmap photo)  throws UnExpectedStateException{
        photos.add(photo);
        currentProgress = newProgress;
        if (currentProgress >= 100) {
            completeChallenge();
        }
    }

    public void completeChallenge() {//throws UnExpectedStateException{
        long currentTime = System.currentTimeMillis();
        if (currentTime - startTime.getTime() > timeLimit) {
            failChallenge();
            //throw new UnExpectedStateException("Challenge has expired");
        }
        endTime = new Timestamp(System.currentTimeMillis());
        currentState = State.COMPLETED;
        currentUser.updateTotalPoints();
    }

    public void completeChallenge(Bitmap photo) throws UnExpectedStateException{
        completeChallenge();
        photos.add(photo);
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

    public long getTimeLimit() {
        return timeLimit;
    }
}
