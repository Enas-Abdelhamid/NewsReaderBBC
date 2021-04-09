package com.example.newsreaderbbc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouritesDisplayList extends AppCompatActivity {

    MyDBhelper database;
    private static final String TAG = "myTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_display_list);


        //Check if importing link from previous activity is successful
       // Bundle extras = getIntent().getExtras();
      //  if (extras != null) {
         //   String valueFromHeadline = extras.getString("key");
           // EditText link_edit = (EditText) findViewById(R.id.text_link_fav);
          //  link_edit.setText(valueFromHeadline);
      //  }



        //----DB
       ListView listView = (ListView) findViewById(R.id.news_fav_list);
        database = new MyDBhelper(this);


      ArrayList<String> theList = new ArrayList<>();
     Cursor data = database.getListContents();
     if(data.getCount() == 0){
     Toast.makeText(this, "List is Empty Now",Toast.LENGTH_LONG).show();
     }else{
         while(data.moveToNext()){
        theList.add(data.getString(1));
        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
        listView.setAdapter(listAdapter);
         }
       }
        //----DB




        //-----remove item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {

                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    String valueFromFavChoice = extras.getString("titlekey");
                    Intent i = new Intent(FavouritesDisplayList.this, deleteItemActivity.class);
                    i.putExtra("titlekey", valueFromFavChoice);
                    startActivity(i);
                }

            }
        });
        //-----remove item



    }



}