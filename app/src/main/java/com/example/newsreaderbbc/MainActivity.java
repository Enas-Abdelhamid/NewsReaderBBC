package com.example.newsreaderbbc;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    /** Declaration of Variables.
     * User sharing his or her Name, Age and Interests are going to be stored using
     * shared preferences
     */

    TextView userName, age, interests;
    SharedPreferences sharedInterestsPreferences;
    public static final String sharedName = "nameREF";
    public static final String sharedAge = "ageREF";
    public static final String sharedInterests = "interstsREF";
    public static final String prefSet = "prefREF";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (TextView) findViewById(R.id.text_name);
        age = (TextView) findViewById(R.id.text_age);
        interests = (TextView) findViewById(R.id.text_interest);

        /** Conditional Arrangement to check if the set of shared preferences already contains
         * the specific variables shared before or not, if those variables are detected, the values
         * will be displayed in the same editTexts the next time the program runs
         */

        sharedInterestsPreferences = getSharedPreferences(prefSet,Context.MODE_PRIVATE);

        if (sharedInterestsPreferences.contains(sharedName))
        {
            userName.setText(sharedInterestsPreferences.getString(sharedName, ""));
        }
        if (sharedInterestsPreferences.contains(sharedAge))
        {
            age.setText(sharedInterestsPreferences.getString(sharedAge, ""));
        }
        if (sharedInterestsPreferences.contains(sharedInterests))
        {
            interests.setText(sharedInterestsPreferences.getString(sharedInterests, ""));
        }
    }


    /** Function to move to next Introductory Page
     */
    public void moveToIntroductory(View v) {
        Intent intent = new Intent(MainActivity.this, IntroductoryActivity.class);
        startActivity(intent);
    }


    /** Function to store the sharedpreferences data
     */
    public void storeMyPreferences(View view) {
        String un = userName.getText().toString();
        String ua = age.getText().toString();
        String ui = interests.getText().toString();
        SharedPreferences.Editor editor = sharedInterestsPreferences.edit();
        editor.putString(sharedName, un);
        editor.putString(sharedAge, ua);
        editor.putString(sharedInterests, ui);
        editor.commit();
    }



}