package com.example.newsreaderbbc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/** this activity displays the favourites listview , and guides the user on how to delete
 * any favourite item, by clicking the article title and then moving to the next delete activity.
 */
public class FavouritesDisplayList extends AppCompatActivity {

    MyDBhelper database;
    private static final String TAG = "myTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_display_list);


        //This is for me to Check if importing link from previous activity is successful
       // Bundle extras = getIntent().getExtras();
      //  if (extras != null) {
         //   String valueFromHeadline = extras.getString("key");
           // EditText link_edit = (EditText) findViewById(R.id.text_link_fav);
          //  link_edit.setText(valueFromHeadline);
      //  }



        //----DB - Loading data from sqlite database to update the listview
       ListView listView = (ListView) findViewById(R.id.news_fav_list);
        database = new MyDBhelper(this);


      ArrayList<String> theList = new ArrayList<>();
     Cursor data = database.getListContents();
     if(data.getCount() == 0){
     Toast.makeText(this, "Emptized",Toast.LENGTH_LONG).show();
     }else{
         while(data.moveToNext()){
        theList.add(data.getString(1));
        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
        listView.setAdapter(listAdapter);
         }
       }
        //----DB




        //-----remove item
/**upon the click of any article, this activity starts another activity that gives the user
 * the option to delete the article from favourites list
 */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String articleTodelete = theList.get(position);

                Intent intent = new Intent(FavouritesDisplayList.this, deleteItemActivity.class);

                intent.putExtra("ChosenTitle", articleTodelete);

                startActivity(intent);

            }
        });
        //-----remove item



    }



}