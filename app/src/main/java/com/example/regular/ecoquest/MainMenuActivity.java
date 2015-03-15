package com.example.regular.ecoquest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


public class MainMenuActivity extends ActionBarActivity {
    private Activity myActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.myActivity = this;
        setContentView(R.layout.activity_main_menu);
        LinearLayout challengeOpenRegion = (LinearLayout) this.findViewById(R.id.main_menu_challenge_open);
        LinearLayout challengeInProgressRegion = (LinearLayout) this.findViewById(R.id.main_menu_challenge_in_progress);
        LinearLayout challengeFinishedRegion = (LinearLayout) this.findViewById(R.id.main_menu_challenge_finished);
        LinearLayout leaderboards = (LinearLayout) this.findViewById(R.id.main_menu_leaderboards_page);

        // Opens In Progress Challenges
        challengeInProgressRegion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.i("In Progress Challenges", "Opening In Progress Challenges Page");
                Intent i = new Intent(myActivity.getApplicationContext(), ChallengeListCurrent.class);
                startActivity(i);
            }
        });

        // Opens In Progress Challenges
        challengeOpenRegion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.i("In Progress Challenges", "Opening In Progress Challenges Page");
                Intent i = new Intent(myActivity.getApplicationContext(), ChallengesActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
