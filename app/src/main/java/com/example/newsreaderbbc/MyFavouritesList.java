package com.example.newsreaderbbc;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/** This activity only is for displaying the items in the favourites listview
 */
public class MyFavouritesList extends AppCompatActivity {
    MyDBhelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourites_list);
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
    }
}