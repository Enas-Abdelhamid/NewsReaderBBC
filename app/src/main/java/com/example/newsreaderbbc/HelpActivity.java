package com.example.newsreaderbbc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

/** Activity that displays help tips for how to use different features of the app
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "You are now reading HELP tips", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}