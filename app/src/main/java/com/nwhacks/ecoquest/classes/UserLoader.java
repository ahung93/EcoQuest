package com.nwhacks.ecoquest.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by William on 2015-03-14.
 */
public class  UserLoader {

    public static String[] firstNames = {"Bob", "Alvin", "Nena", "Nick", "Dude"};
    public static String[] lastNames = {"Hung", "Nguyen", "Pezarro", "Smith", "Flawd"};

    public static User createRandomFriend() {
        int randomFirst = (int) ((Math.random() * 5) % 5 - 1);
        int randomLast = (int) ((Math.random() * 5) % 5 - 1);
        User user = new User(firstNames[randomFirst], lastNames[randomLast]);
        user.addNewChallenge(new Challenge(Challenge.State.COMPLETED, "Eat 2 Bananas", "Ate 2 Bananas", Challenge.Type.NUTRITION, 4 * 1000 * 60 * 60, randomFirst, 5));
        user.addNewChallenge(new Challenge(Challenge.State.COMPLETED, "Recycle 2 cans", "Recycled 2 Cans", Challenge.Type.WASTE_AND_ENERGY_REDUCTION, 1 * 1000 * 60 * 60, randomLast, 5));
        return user;
    }

    public static User createDefaultUser() {
        User user = new User("Will", "will@will.com");
        List<Challenge> completedChallenges = new ArrayList<>();
        completedChallenges.add(new Challenge(Challenge.State.COMPLETED, "Drive 2 Cars", "Drove 2 cars", Challenge.Type.TRANSPORTATION, 6 * 1000 * 60 * 60, 5, 5));
        completedChallenges.add(new Challenge(Challenge.State.COMPLETED, "Compost 3 bananas", "Composted 3 Bananas", Challenge.Type.WASTE_AND_ENERGY_REDUCTION, 2 * 1000 * 60 * 60, 5, 5));
        completedChallenges.add(new Challenge(Challenge.State.COMPLETED, "Let Clothes Dry Outside", "During sunny days to dry clothes instead of using drying machine", Challenge.Type.WASTE_AND_ENERGY_REDUCTION, 30 * 1000 * 60, 5, 5));

        User friend1 = createRandomFriend();
        User friend2 = createRandomFriend();
        user.addFriend(friend1);
        user.addFriend(friend2);

        return user;
    }

}
