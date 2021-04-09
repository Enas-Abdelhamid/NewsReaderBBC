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
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class FavouritesControlActivity extends AppCompatActivity {

    MyDBhelper dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_control);

        dataBase = new MyDBhelper(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String valueFromHyperlink = extras.getString("urlkey");
            String valueFromHeadline = extras.getString("titlekey");
            String valueFromDates = extras.getString("datekey");
            String valueFromDescription = extras.getString("descriptionkey");
            EditText link_edit = (EditText) findViewById(R.id.text_link);
            link_edit.setText(valueFromHyperlink);
            EditText title_edit = (EditText) findViewById(R.id.text_title);
            title_edit.setText(valueFromHeadline);
            EditText date_edit = (EditText) findViewById(R.id.text_date);
            date_edit.setText(valueFromDates);
            EditText description_edit = (EditText) findViewById(R.id.text_description);
            description_edit.setText(valueFromDescription);
        }
    }

    public void readArticle(View view) {
        URL url;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("urlkey");
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(value));
            startActivity(i);
        }
}

    public void addToFavourites(View v) {

        URL url;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("titlekey");
            boolean enterMSG = dataBase.addData(value);
            if(enterMSG==true){
                Toast.makeText(this, "Message stored in Database", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Unsuccessful Entry", Toast.LENGTH_LONG).show();
            }
        }


        //--



    }


    public void displayFav(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String valueFromFavChoice = extras.getString("titlekey");
            Intent i = new Intent(FavouritesControlActivity.this, FavouritesDisplayList.class);
            i.putExtra("titlekey", valueFromFavChoice);
            startActivity(i);
        }

    }

}