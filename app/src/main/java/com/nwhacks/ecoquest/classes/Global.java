package com.nwhacks.ecoquest.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by William on 2015-03-15.
 */
public class Global {
    public static String[] firstNames = {"Bob", "Alvin", "Nena", "Nick", "Dude", "Best friend", "Cool friend", "Friend"};
    public static String[] lastNames = {"Hung", "Nguyen", "Pezarro", "Smith", "Flawd"};

    public static User mainUser = createDefaultUser();

    public static List<Challenge> allChallenges = ChallengeLoader.getAvailableChallenges();

    public static List<Challenge> getAllChallenges() {
        return ChallengeLoader.getAvailableChallenges();
    }

    public static List<Challenge> getTransportationChallenges() {
        List<Challenge> ret = new ArrayList<>();
        for (Challenge c : getAllChallenges()) {
            if (c.getType() == Challenge.Type.TRANSPORTATION) {
                ret.add(c);
            }
        }
        return ret;
    }

    public static List<Challenge> getWasteAndEnergyChallenges() {
        List<Challenge> ret = new ArrayList<>();
        for (Challenge c : getAllChallenges()) {
            if (c.getType().equals(Challenge.Type.WASTE_AND_ENERGY_REDUCTION)) {
                ret.add(c);
            }
        }
        return ret;
    }

    public static List<Challenge> getNutritionChallenges() {
        List<Challenge> ret = new ArrayList<>();
        for (Challenge c : getAllChallenges()) {
            if (c.getType() == Challenge.Type.NUTRITION) {
                ret.add(c);
            }
        }
        return ret;
    }

    public static User createRandomFriend() {
        int randomFirst = (int) ((Math.random() * firstNames.length) % firstNames.length);
        int randomLast = (int) ((Math.random() * lastNames.length) % lastNames.length);
        User user = new User(firstNames[randomFirst], lastNames[randomLast]);
        Challenge c1 = randomChallenge();
        c1.completeChallenge();
        user.addNewChallenge(c1);
        c1 = randomChallenge();
        c1.completeChallenge();
        user.addNewChallenge(c1);
        user.addNewChallenge(new Challenge(Challenge.State.COMPLETED, "Recycle 2 cans", "Recycled 2 Cans", Challenge.Type.WASTE_AND_ENERGY_REDUCTION, 1 * 1000 * 60 * 60, randomLast, 5));
        user.updateTotalPoints();
        return user;
    }

    private static User createDefaultUser() {
        User user = new User("Will", "will@will.com");
        List<Challenge> completedChallenges = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            user.addNewChallenge(randomStateChallenge());
        }

        for (int j = 0; j < 3; j++) {
            User friend1 = createRandomFriend();
            user.addFriend(friend1);
        }
        user.updateTotalPoints();
        return user;
    }

    public static Challenge randomStateChallenge(){
        int rcNum = (int)((Math.random() * getAllChallenges().size()) % getAllChallenges().size());
        Challenge rc = getAllChallenges().get(rcNum);

        int rand = (int) (Math.random() * 3) % 3;
        switch (rand) {
            case 0: // Still in progress
                rc.startChallenge(mainUser);
                break;
            case 1: // Failed
                rc.failChallenge();
                break;
            case 2: //
                rc.completeChallenge();
                break;
            default:
                //rc.setCurrentState(Challenge.State.OPEN);
                break;
        }
        return rc;
    }

    public static Challenge randomChallenge(){
        return getAllChallenges().get((int)((Math.random() * getAllChallenges().size()) % getAllChallenges().size()));
    }

}
