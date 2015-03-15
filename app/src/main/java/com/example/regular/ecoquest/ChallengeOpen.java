package com.example.regular.ecoquest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nwhacks.ecoquest.classes.Challenge;


public class ChallengeOpen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_open);

        Intent i = getIntent();
        Challenge challenge = (Challenge) i.getParcelableExtra("challenge");

        if (challenge != null) {
            TextView titleText = (TextView) this.findViewById(R.id.challenge_title);
            TextView pointsText = (TextView) this.findViewById(R.id.challenge_points);
            TextView descText = (TextView) this.findViewById(R.id.challenge_description);

            titleText.setText(challenge.getTitle());
            descText.setText(challenge.getDescription());
            pointsText.setText((Integer.toString(challenge.getRewardPoints()))+"pts");

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challenge_open, menu);
        return true;
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
}
