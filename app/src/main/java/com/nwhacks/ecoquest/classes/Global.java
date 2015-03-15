package com.nwhacks.ecoquest.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by William on 2015-03-15.
 */
public class Global {
    public static User mainUser = UserLoader.createDefaultUser();

    public static List<Challenge> allChallenges = ChallengeLoader.getAvailableChallenges();

    public static List<Challenge> getTransportationChallenges() {
        List<Challenge> ret = new ArrayList<>();
        for (Challenge c : allChallenges) {
            if (c.getType() == Challenge.Type.TRANSPORTATION) {
                ret.add(c);
            }
        }
        return ret;
    }

    public static List<Challenge> getWasteAndEnergyChallenges() {
        List<Challenge> ret = new ArrayList<>();
        for (Challenge c : allChallenges) {
            if (c.getType().equals(Challenge.Type.WASTE_AND_ENERGY_REDUCTION)) {
                ret.add(c);
            }
        }
        return ret;
    }

    public static List<Challenge> getNutritionChallenges() {
        List<Challenge> ret = new ArrayList<>();
        for (Challenge c : allChallenges) {
            if (c.getType() == Challenge.Type.NUTRITION) {
                ret.add(c);
            }
        }
        return ret;
    }

}
