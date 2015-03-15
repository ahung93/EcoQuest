package com.example.regular.ecoquest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nwhacks.ecoquest.classes.Challenge;
import com.nwhacks.ecoquest.classes.User;

import java.util.ArrayList;
import java.util.List;


public class ChallengeListCurrent extends ActionBarActivity {
    private List<Challenge> currentChallenges = new ArrayList<Challenge>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_list_current);
        Log.i("ChallengeListCurrent", "Initialized onCreate");
        populateChallengeList();
        Log.i("ChallengeListCurrent", "Populated Challenge List");
        populateChallengeView();
        Log.i("ChallengeListCurrent", "Created the list view");
        registerClickCallback();
        Log.i("ChallengeListCurrent", "Finished doing register callback");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateChallengeList(){
        currentChallenges.add(new Challenge(Challenge.State.IN_PROGRESS, "Eat 2 Bananas", "Ate 2 Bananas", Challenge.Type.NUTRITION , 4 * 1000 * 60 * 60, 5, 5 ));
        currentChallenges.add(new Challenge(Challenge.State.IN_PROGRESS, "Drive 2 Cars", "Drove 2 cars", Challenge.Type.TRANSPORTATION , 6 * 1000 * 60 * 60, 5, 5 ));
        currentChallenges.add(new Challenge(Challenge.State.IN_PROGRESS, "Compost 3 bananas", "Composted 3 Bananas", Challenge.Type.WASTE_AND_ENERGY_REDUCTION , 2 * 1000 * 60 * 60, 5, 5 ));
        currentChallenges.add(new Challenge(Challenge.State.IN_PROGRESS, "Recycle 2 cans", "Recycled 2 Cans", Challenge.Type.WASTE_AND_ENERGY_REDUCTION , 1 * 1000 * 60 * 60, 5, 5 ));
        currentChallenges.add(new Challenge(Challenge.State.IN_PROGRESS, "Let Clothes Dry Outside", "You sunny days to dry clothes instead of using drying machine", Challenge.Type.WASTE_AND_ENERGY_REDUCTION , 30 * 1000 * 60, 5, 5 ));
        currentChallenges.add(new Challenge(Challenge.State.IN_PROGRESS, "Eat 2 Bananas", "Ate 2 Bananas", Challenge.Type.NUTRITION , 4 * 1000 * 60 * 60, 5, 5 ));
        currentChallenges.add(new Challenge(Challenge.State.IN_PROGRESS, "Eat 2 Bananas", "Ate 2 Bananas", Challenge.Type.NUTRITION , 4 * 1000 * 60 * 60, 5, 5 ));
        currentChallenges.add(new Challenge(Challenge.State.IN_PROGRESS, "Eat 2 Bananas", "Ate 2 Bananas", Challenge.Type.NUTRITION , 4 * 1000 * 60 * 60, 5, 5 ));
        currentChallenges.add(new Challenge(Challenge.State.IN_PROGRESS, "Eat 2 Bananas", "Ate 2 Bananas", Challenge.Type.NUTRITION , 4 * 1000 * 60 * 60, 5, 5 ));
        currentChallenges.add(new Challenge(Challenge.State.IN_PROGRESS, "Eat 2 Bananas", "Ate 2 Bananas", Challenge.Type.NUTRITION , 4 * 1000 * 60 * 60, 5, 5 ));

        for (Challenge c : currentChallenges){
            c.startChallenge();
        }
    }

    private void populateChallengeView(){
        ArrayAdapter<Challenge> adapter = new MyListAdapter();
        ListView challengeList = (ListView) findViewById(R.id.challengesCurrentList);
        challengeList.setAdapter(adapter);
    }

    private void registerClickCallback() {
        final ListView challengeList = (ListView) findViewById(R.id.challengesCurrentList);
        final ChallengeListCurrent current = this;
        challengeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Intent intent = new Intent(current, ChallengeOpen.class);
                startActivity(intent);
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Challenge>{
        public MyListAdapter(){
            super(ChallengeListCurrent.this, R.layout.challenge_item, currentChallenges);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.challenge_item, parent, false);
            }

            Challenge currentChallenge = currentChallenges.get(position);
            TextView timeText = (TextView) itemView.findViewById(R.id.challenge_item_remaining_time);
            TextView titleText = (TextView) itemView.findViewById(R.id.challenge_item_title);
            TextView descriptionText = (TextView) itemView.findViewById(R.id.challenge_item_description);
            TextView pointsText = (TextView) itemView.findViewById(R.id.challenge_item_points);

            long timeLeft = System.currentTimeMillis() - currentChallenge.getStartTime();
            long hoursLeft = (timeLeft/ (1000 * 60 * 60)) % 24;
            long minutesLeft = (timeLeft / (1000 * 60)) % 60;

            timeText.setText( Long.toString(hoursLeft) + " hours, " + Long.toString(minutesLeft) + " minutes left");
            titleText.setText(currentChallenge.getTitle());
            descriptionText.setText(currentChallenge.getDescription());
            Log.i("getView", "Set getDescription");
            pointsText.setText((Integer.toString(currentChallenge.getRewardPoints())));
            Log.i("getView", "Set getRewardPoints");
            return itemView;
        }

    }
}
