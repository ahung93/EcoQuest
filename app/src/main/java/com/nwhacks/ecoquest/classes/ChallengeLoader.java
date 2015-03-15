package com.nwhacks.ecoquest.classes;

import android.util.Log;

import com.googlecode.jcsv.reader.CSVEntryParser;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicholas on 3/14/2015.
 */
public class ChallengeLoader {

    private static String STRING_BOYS = "OPEN~Carpool to work/school~A great way to get to know your co-workers and classmates better.~Transportation~604800000~3~0~0\n"+
            "OPEN~Take public transit 3/5 workdays this week~Transit is cheaper, lets you catch up on reading, watching, and listening and is a whole lot less stressful than driving.~Transportation~432000000~6~0~0\n"+
            "OPEN~Bike at least one way to work/school~Get in shape and to your destination at the same time!~Transportation~172800000~3~0~0\n"+
            "OPEN~Accelerate and decelerate gently for a whole week~One of the big differentiating factors for fuel consumption is how quickly you accelerate. Decelerating doesn't directly effect your usage but if you slow more gradually you may be able to keep some of the speed that you've built up.�~Transportation~604800000~5~0~0\n"+
            "OPEN~Cover x KM on x litres of gasoline~Can you beat this challenge? Maybe you'll have to get rid of that old tire in the back and the bike rack ruining your aerodynamic profile that you never use.~Transportation~604800000~2~0~0\n"+
            "OPEN~Beat Google Maps' bike time to work/school~This one is a bit of a toughie but immensely satisfying if successful. If you manage to beat the time that Google Maps estimates that means that you've manage to beat even the almighty supercomputers' expectations.~Transportation~432000000~7~0~0\n"+
            "OPEN~Try an alternate transit route~If you're blessed with an abundance of bus routes then this'll be an interesting challenge for you. Sometimes alternate routes can more efficient at certain times of day, quieter, have a better view or just be more pleasant overall.~Transportation~172800000~3~0~0\n"+
            "OPEN~Get a monthly transit pass~Consider this to be your commitment to joining the (usually) washed and cheerful masses on the bus.~Transportation~432000000~4~0~0\n"+
            "OPEN~Don't exceed the speed limit for an entire day.~If you drive slower, or, at the speed limit say, then you reduce your risk of being in an accident and actually save fuel as, once you reach a certain speed, you start to spend increasing amounts of fuel per extra kilometer.~Transportation~86400000~4~0~0\n"+
            "OPEN~Walk to the park~What better way to take in nature than by walking amongst it?~Transportation~86400000~1~0~0\n"+
            "OPEN~Visit a farmer's market~Farmer's markets are full of delicious surprises and often have better prices for produce as you are cutting out the middleman.~Nutrition~172800000~3~0~0\n"+
            "OPEN~Try out a fair trade product (coffee, chocolate,etc)~Fair trade products are a great way to ensure that farmers in less advantaged regions are getting a fair share of the profits. It doesn't hurt that it usually means that the products are of higher quality as well.~Nutrition~172800000~3~0~0\n"+
            "OPEN~Completely vegetarian for 2 days.~Vegetables are so tasty! Eating a vegetarian diet means less weight, more fibre, stronger bones, and a smaller grocery bill.~Nutrition~172800000~5~0~0\n"+
            "OPEN~Eliminate a processed food product from your diet~Generally, the more processed food becomes the less nutrition you get from it and the greater the impact that it has on the planet.~Nutrition~172800000~4~0~0\n"+
            "OPEN~Eat 15% smaller portions than usual for 5 meals. ~Many of us (myself included) eat past the point of satiation. Try consciously putting a bit less food in your plate and see if you really needed all that food after all.�~Nutrition~432000000~5~0~0\n"+
            "OPEN~Bring a few fruits or some trail mix for a snack~It's easy to reach for a snack in a bag but it's not always the best thing for us. Making your own trail mix or packing fruits involves less packaging and is a lot better for you! Try this two days in a row and see how your energy levels vary.~Nutrition~172800000~5~0~0\n"+
            "OPEN~No red meat for 3 days.~Red meat takes the most resources per pound of meat and has been shown to be the least beneficial type of meat to consume.~Nutrition~259200000~4~0~0\n"+
            "OPEN~No red meat and poultry for 3 days.~Fish are generally regarded as being one of the healthier forms of protein and as long as it is not from an over-fished part of the ocean it is relatively low impact.�~Nutrition~259200000~6~0~0\n"+
            "OPEN~Make a fruit smoothie for breakfast!~There are few better ways to start your day than with something delicious like a strawberry, orange, and banana almond milk smoothie.~Nutrition~86400000~2~0~0\n"+
            "OPEN~Make a home cooked meal.~Cooking at home avoids a great deal of the waste surrounding the food services industry, gives you greater control over the ingredients used and means that you'll have leftovers for later. Yummy!~Nutrition~86400000~2~0~0\n"+
            "OPEN~Eat meat no more than 4 (meals) times this week~It takes 10,000 litres of water to produce one pound of beef compared to 100 litres of water for one pound of wheat. So go hard on the spaghetti but maybe find an alternative for those meatballs?~Nutrition~604800000~6~0~0\n"+
            "OPEN~Don't leave a room with the lights still on for one day.~If you're stumbling around in the dark just ask your cat for directions.~Waste/Energy~86400000~4~0~0\n"+
            "OPEN~Review what is and isn't recyclable in your area.~You'd be surprised at what is and isn't acceptable.~Waste/Energy~86400000~2~0~0\n"+
            "OPEN~Try out a golden shower ~As long as your shower drains effectively you'll be no worse for the wear.~Waste/Energy~86400000~2~0~0\n"+
            "OPEN~No faucet running while you brush your teeth (3 days).~You can use up to 20 litres of water while brushing your teeth if you leave it running.~Waste/Energy~259200000~4~0~0\n"+
            "OPEN~Switch out your incandescent bulbs ~CFL bulbs use 23% of the energy that incandescent bulbs do AND last 6.7 times longer.~Waste/Energy~259200000~5~0~0\n"+
            "OPEN~Bring your used electronics to be recycled responsibly.~Some unscrupulous organizations actually ship your e-waste overseas instead of actually recycling it.~Waste/Energy~604800000~5~0~0\n"+
            "OPEN~Visit the thrift store before buying new goods for a week.~\"Macklemore said it best, \"\"Savin' my money and I'm hella happy that's a bargain, [sir/madam]\"\". You can find some really good deals there!\"~Waste/Energy~604800000~4~0~0\n"+
            "OPEN~\"Plug in any \"\"phantom power\"\" appliances to power bars\"~\"Phantom power is a term for appliances that continue to draw power even when they are \"\"turned off\"\" (e.g. set top boxes).\"~Waste/Energy~259200000~2~0~0\n"+
            "OPEN~Consolidate your next dish washing load.~Your dishes don't need the deluxe treatment. Save on dishwasher liquid, time spent loading and unloading and the bothersome whirring of your dishwasher.~Waste/Energy~86400000~2~0~0\n"+
            "OPEN~Check your windows for drafts.~Your windows are supposed to let you see outside but not let the outside world in. 10-25% of heat exits homes through windows on average, it's an easy area to target for improvement.~Waste/Energy~259200000~4~0~0\n"+
            "OPEN~Bring a resuable water bottle or grocery bag with you.~They're starting to charge you for plastic bags now and bottled water tastes gross anyways!~Waste/Energy~86400000~1~0~0\n";

    public ChallengeLoader(){}

    public int square(int n){
       return n * n;
    }

    //--- Suppose you have input stream `is` of your csv file then:

        public static class ChallengeEntryParser implements CSVEntryParser<Challenge> {
            public Challenge parseEntry(String... data) {
                String title = data[1];
                String description = data[2];
                String track = data[3];
                Challenge.Type type=null;
                if (track=="Transportation"){
                    type = Challenge.Type.TRANSPORTATION;
                } else if (track=="Nutrition"){
                    type = Challenge.Type.NUTRITION;
                } else if (track== "Waste/Energy"){
                    type = Challenge.Type.WASTE_AND_ENERGY_REDUCTION;
                }


                long timelimit = Long.parseLong(data[4]);
                int rewardpoints = Integer.parseInt(data[5]);
                int penaltypoints = Integer.parseInt(data[6]);

                return new Challenge(Challenge.State.OPEN, title, description, type, timelimit, rewardpoints, penaltypoints);
            }


        }

    public static List<Challenge> getAvailableChallenges(){
        List<Challenge> listOfChallenges = new ArrayList<>();

            //BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String[] lines = STRING_BOYS.split(System.getProperty("line.separator"));
           for (String line : lines) {
                System.out.println(line);
                String[] RowData = line.split("~");
                String title = RowData[1];
                String description = RowData[2];
                String track = RowData[3];
                Challenge.Type type=null;
                if (track.equals("Transportation")){
                    type = Challenge.Type.TRANSPORTATION;
                } else if (track.equals("Nutrition")){
                    type = Challenge.Type.NUTRITION;
                } else if (track.equals("Waste/Energy")){
                    type = Challenge.Type.WASTE_AND_ENERGY_REDUCTION;
                }

                long timelimit = Long.parseLong(RowData[4]);
                int rewardpoints = Integer.parseInt(RowData[5]);
                int penaltypoints = Integer.parseInt(RowData[6]);

                listOfChallenges.add(new Challenge(Challenge.State.OPEN, title, description, type, timelimit, rewardpoints, penaltypoints));
                // do something with "data" and "value"
            }

        return listOfChallenges;
    }

    public static void main(String[] args) {
        System.out.println("HAII DER");
        List<Challenge> challenges = getAvailableChallenges();
        for (Challenge c : challenges){
            System.out.println(c.getType());
        }
    }
}
