package com.example.newsreaderbbc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/** This activity displays the data stored in shared preferences, these data was entered in the very
 * first page of the application
 */

public class UserInformationActivity extends AppCompatActivity {

    /**
     * Declaration of Variables.
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
        setContentView(R.layout.activity_user_information);


        userName = (EditText) findViewById(R.id.text_name);
        age = (EditText) findViewById(R.id.text_age);
        interests = (EditText) findViewById(R.id.text_interest);
        sharedInterestsPreferences = getSharedPreferences(prefSet,
                Context.MODE_PRIVATE);

        if (sharedInterestsPreferences.contains(sharedName)) {
            userName.setText(sharedInterestsPreferences.getString(sharedName, ""));
        }
        if (sharedInterestsPreferences.contains(sharedAge)) {
            age.setText(sharedInterestsPreferences.getString(sharedAge, ""));
        }
        if (sharedInterestsPreferences.contains(sharedInterests)) {
            interests.setText(sharedInterestsPreferences.getString(sharedInterests, ""));
        }
    }
}
