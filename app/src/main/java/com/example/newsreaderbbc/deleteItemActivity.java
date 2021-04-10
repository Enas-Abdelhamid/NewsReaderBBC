package com.example.newsreaderbbc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/** This activity handles how to remove an item from the favourites list,
 * the item is being deleted from the database as from the listview.
 */
public class deleteItemActivity extends AppCompatActivity {

    MyDBhelper dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        dataBase = new MyDBhelper(this);

/** Using the extras get string method, this activity receives the specific title
 *  of the article that is desired to be deleted from the previous activity.
 */
        Bundle extras = getIntent().getExtras();
          if (extras != null) {
           String valueFromHeadline = extras.getString("ChosenTitle");
           EditText link_delete = (EditText) findViewById(R.id.chosen_title);
          link_delete.setText(valueFromHeadline);
         }
    }

    /** the deleteArticle method is being invoked by the press of the delete button.
     */
    public void deleteArticle(View view) {
        Bundle extras = getIntent().getExtras();
        String valueFromHeadline = extras.getString("ChosenTitle");
        /** this method consequently calls the other deleteName method that is embedded on the
         * MyDBhelper java class.
         */
        dataBase.deleteName(valueFromHeadline);
    /** toast to assure deletion.
     */
        Toast.makeText(deleteItemActivity.this, "Article removed from your Favourites!", Toast.LENGTH_LONG).show();

        }
}