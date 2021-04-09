package com.example.newsreaderbbc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class deleteItemActivity extends AppCompatActivity {

    MyDBhelper dataBase;
    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        dataBase = new MyDBhelper(this);


        Bundle extras = getIntent().getExtras();
          if (extras != null) {
           String valueFromHeadline = extras.getString("titlekey");
           EditText link_delete = (EditText) findViewById(R.id.chosen_title);
          link_delete.setText(valueFromHeadline);
         }
    }

    public void deleteArticle(View view) {
        Bundle extras = getIntent().getExtras();
        String valueFromHeadline = extras.getString("titlekey");
        dataBase.deleteName(valueFromHeadline);

        Toast.makeText(deleteItemActivity.this, "Article removed from your Favourites!", Toast.LENGTH_LONG).show();

        }
}