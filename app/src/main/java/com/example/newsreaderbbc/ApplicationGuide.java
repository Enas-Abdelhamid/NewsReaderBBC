package com.example.newsreaderbbc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/** In this activity, fragment concept is implemented,
 *  based on either button that is pressed, the activity loads a different fragment
 *  either for FB data or Twitter data.
 */

public class ApplicationGuide extends AppCompatActivity {

    Button fbFragment, twitterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_guide);

        fbFragment = (Button) findViewById(R.id.fragment1);
        twitterFragment = (Button) findViewById(R.id.fragment2);

        /** click listener for facebook button click.
         */
        fbFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragmentfb(new FBFragment());
            }
        });

        /** click listener for twitter button click.
         */
        twitterFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragmenttw(new TwitterFragment());
            }
        });

    }

    /** Method for loading Facebook Fragment using Fragment Manager.
     */
    private void loadFragmentfb(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    /** Method for loading Twitter Fragment using Fragment Manager.
          */
    private void loadFragmenttw(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}