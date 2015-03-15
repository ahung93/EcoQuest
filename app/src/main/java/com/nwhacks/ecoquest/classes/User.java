package com.nwhacks.ecoquest.classes;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private int totalPoints;

    private ArrayList<Challenge> allChallenges;

    private ArrayList<User> friends;

    public User(String name, String email){
        this.name = name;
        this.email = email;
        this.totalPoints = 0;
    }

    public User(String name, String email, ArrayList<User> friends, ArrayList<Challenge> challenges, int totalPoints) {
        this.name = name;
        this.email = email;
        this.friends = friends;
        this.allChallenges = challenges;
        this.totalPoints = totalPoints;
    }

    public void startNewChallenge(Challenge c) throws UnExpectedStateException{
        c.startChallenge(this);
        addNewChallenge(c);
    }

    // Add a new challenge to the list of challenges
    public void addNewChallenge(Challenge c) {
        getAllChallenges().add(c);
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    // Go through all completed and failed challenges, and sum up the points
    // And also return the total points as well
    public int updateTotalPoints() {
        int sum = 0;
        for (Challenge c : getAllCompletedChallenges()) {
            sum += c.getRewardPoints();
        }
        for (Challenge c : getAllFailedChallenges()) {
            sum -= c.getRewardPoints();
        }
        totalPoints = Math.max(0, sum);
        return Math.max(0, sum);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public ArrayList<Challenge> getAllChallenges() {
        return allChallenges;
    }

    public ArrayList<Challenge> getAllCompletedChallenges() {
        ArrayList<Challenge> completedChallenges = new ArrayList<Challenge>();
        for (Challenge c : getAllChallenges()) {
            if (c.getCurrentState() == Challenge.State.COMPLETED)
                completedChallenges.add(c);
        }
        return completedChallenges;
    }

    public ArrayList<Challenge> getAllInProgressChallenges() {
        ArrayList<Challenge> inProgressChallenges = new ArrayList<Challenge>();
        for (Challenge c : getAllChallenges()) {
            if (c.getCurrentState() == Challenge.State.IN_PROGRESS)
                inProgressChallenges.add(c);
        }
        return inProgressChallenges;
    }

    public ArrayList<Challenge> getAllFailedChallenges() {
        ArrayList<Challenge> completedChallenges = new ArrayList<Challenge>();
        for (Challenge c : getAllChallenges()) {
            if (c.getCurrentState() == Challenge.State.FAILED)
                completedChallenges.add(c);
        }
        return completedChallenges;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }
}
