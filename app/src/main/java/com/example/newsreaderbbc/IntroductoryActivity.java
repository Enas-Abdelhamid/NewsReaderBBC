package com.example.newsreaderbbc;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;


public class IntroductoryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Menu myMenu = toolbar.getMenu();
        getMenuInflater().inflate(R.menu.choices_menu, myMenu);
        for (int i = 0; i < myMenu.size(); i++) {
            myMenu.getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return onOptionsItemSelected(item);
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.choiceone) {
            Toast.makeText(IntroductoryActivity.this, "Check Latest News From BBC!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(IntroductoryActivity.this, HeadLinesActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.choicetwo) {
            Toast.makeText(IntroductoryActivity.this, "Favourites List!", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.choicethree) {
            Toast.makeText(IntroductoryActivity.this, "Help Option!", Toast.LENGTH_LONG).show();
        }
        

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.info_page) {
           Intent resultIntent = new Intent(IntroductoryActivity.this, UserInformationActivity.class);
           startActivity(resultIntent);


        } else if (id == R.id.favourites_control) {
            Intent resultIntent = new Intent(IntroductoryActivity.this, FavouritesControlActivity.class);
            startActivity(resultIntent);

        } else if (id == R.id.back_to_login) {
            Intent intent=new Intent();
            setResult(500,intent);
            finish();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}