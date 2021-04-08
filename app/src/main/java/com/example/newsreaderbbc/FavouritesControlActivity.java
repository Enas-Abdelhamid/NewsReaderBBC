package com.example.newsreaderbbc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class FavouritesControlActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_control);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            EditText link_edit = (EditText) findViewById(R.id.text_link);
            link_edit.setText(value);
        }
    }

    public void readArticle(View view) {
        URL url;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(value));
            startActivity(i);
        }
}

    public void addToFavourites(View view) {


    }

}