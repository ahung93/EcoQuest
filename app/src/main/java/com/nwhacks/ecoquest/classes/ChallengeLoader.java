package com.nwhacks.ecoquest.classes;

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
/**
        try {
            Reader reader = new FileReader("test.csv");
            CSVReader<String[]> csvPersonReader = CSVReaderBuilder.newDefaultReader(reader);
            List<String[]> persons = csvPersonReader.readAll();
            for (String[] strs : persons) {
                System.out.println(strs[1]);
            }
//            CSVReader<Challenge> csvChallengeReader = new CSVReaderBuilder<Challenge>(reader).entryParser(new ChallengeEntryParser()).build();
//            List<Challenge> challenges = csvChallengeReader.readAll();

        } catch (FileNotFoundException e) {
            System.out.println("File not found, exception caught.");
        } catch (IOException e) {
            System.out.println("IO exception caught.");
        }
 **/
        try {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            BufferedReader reader = new BufferedReader(new FileReader("ChallengeTable.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] RowData = line.split("~");
                String title = RowData[1];
                String description = RowData[2];
                String track = RowData[3];
                Challenge.Type type=null;
                if (track=="Transportation"){
                    type = Challenge.Type.TRANSPORTATION;
                } else if (track=="Nutrition"){
                    type = Challenge.Type.NUTRITION;
                } else if (track== "Waste/Energy"){
                    type = Challenge.Type.WASTE_AND_ENERGY_REDUCTION;
                }


                long timelimit = Long.parseLong(RowData[4]);
                int rewardpoints = Integer.parseInt(RowData[5]);
                int penaltypoints = Integer.parseInt(RowData[6]);

                listOfChallenges.add(new Challenge(Challenge.State.OPEN, title, description, type, timelimit, rewardpoints, penaltypoints));
                // do something with "data" and "value"
            }
        }    catch (IOException ex) {
            // handle exception
        }

        return listOfChallenges;
    }

    public static void main(String[] args) {
        System.out.println("HAII DER");
        List<Challenge> challenges = getAvailableChallenges();
        for (Challenge c : challenges){
            System.out.println(c.getDescription());
        }
    }
}
