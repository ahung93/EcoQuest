package com.example.regular.ecoquest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nwhacks.ecoquest.classes.Challenge;
import com.nwhacks.ecoquest.classes.ChallengeLoader;
import com.nwhacks.ecoquest.classes.User;


public class ChallengesActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    final ChallengesActivity current = this;

    SectionsPagerAdapter mSectionsPagerAdapter;
    static ChallengeListAdapter transportationChallengeListAdapter;
    static ChallengeListAdapter wasteAndEnergyChallengeListAdapter;
    static ChallengeListAdapter nutritionChallengeListAdapter;

    static List<Challenge> allChallenges;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        ChallengeLoader cl = new ChallengeLoader();

        allChallenges = cl.getAvailableChallenges();

        transportationChallengeListAdapter = new ChallengeListAdapter(getTransportationChallenges(allChallenges));
        wasteAndEnergyChallengeListAdapter = new ChallengeListAdapter(getWasteAndEnergyChallenges(allChallenges));
        nutritionChallengeListAdapter = new ChallengeListAdapter(getNutritionChallenges(allChallenges));

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challenges, menu);
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "Transportation".toUpperCase(l);
                case 1:
                    return "Waste/Energy".toUpperCase(l);
                case 2:
                    return "Nutrition".toUpperCase(l);
            }
            return null;
        }
    }

    private List<Challenge> getTransportationChallenges(List<Challenge> challenges) {
        List<Challenge> ret = new ArrayList<>();
        for (Challenge c : challenges) {
            if (c.getType() == Challenge.Type.TRANSPORTATION) {
                ret.add(c);
            }
        }
        return ret;
    }

    private List<Challenge> getWasteAndEnergyChallenges(List<Challenge> challenges) {
        List<Challenge> ret = new ArrayList<>();
        for (Challenge c : challenges) {
            if (c.getType().equals(Challenge.Type.WASTE_AND_ENERGY_REDUCTION)) {
                ret.add(c);
            }
        }
        return ret;
    }

    private List<Challenge> getNutritionChallenges(List<Challenge> challenges) {
        List<Challenge> ret = new ArrayList<>();
        for (Challenge c : challenges) {
            if (c.getType() == Challenge.Type.NUTRITION) {
                ret.add(c);
            }
        }
        return ret;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_challenges, container, false);
            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            ListView lv = (ListView) rootView.findViewById(R.id.challengesList);
            switch (sectionNumber) {
                case 1:             // Transportation
                    lv.setAdapter(transportationChallengeListAdapter);
                    break;
                case 2:             // Waste and Energy
                    lv.setAdapter(wasteAndEnergyChallengeListAdapter);
                    break;
                case 3:             // Nutrition
                    lv.setAdapter(nutritionChallengeListAdapter);
                    break;
                default:
                    break;
            }

            return rootView;
        }
    }

    public class ChallengeListAdapter extends ArrayAdapter<Challenge> {

        private List<Challenge> challenges;

        public ChallengeListAdapter(List<Challenge> c) {
            super(ChallengesActivity.this, R.layout.challenge_item, c);
            this.challenges = c;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.challenge_item, parent, false);
            }

            final Challenge currentChallenge = challenges.get(position);

            TextView titleText = (TextView) itemView.findViewById(R.id.challenge_item_title);
            TextView descriptionText = (TextView) itemView.findViewById(R.id.challenge_item_description);
            TextView pointsText = (TextView) itemView.findViewById(R.id.challenge_item_points);
            String title = currentChallenge.getTitle();
            titleText.setText(title.substring(0, Math.min(23, title.length())) + "...");
            String description = currentChallenge.getDescription();
            descriptionText.setText(description.substring(0, Math.min(37, description.length())) + "...");
            pointsText.setText((Integer.toString(currentChallenge.getRewardPoints())));

            LinearLayout layout = (LinearLayout) itemView.findViewById(R.id.challenge_item_layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(current, ChallengeOpen.class);
                    intent.putExtra("challenge", currentChallenge);
                    startActivity(intent);
                }
            });

            // Add to the items
            /**
             TextView rankText = (TextView) itemView.findViewById(R.id.user_item_rank);
             TextView fullNameText = (TextView) itemView.findViewById(R.id.user_item_fullname);
             TextView descriptionText = (TextView) itemView.findViewById(R.id.user_item_description);
             TextView pointsText = (TextView) itemView.findViewById(R.id.user_item_points);

             rankText.setText(Integer.toString(position + 1));
             fullNameText.setText(currentChallenge.getName());
             descriptionText.setText("Some description");
             pointsText.setText((Integer.toString(currentChallenge.getTotalPoints())));
             **/
            return itemView;
        }
    }
}
