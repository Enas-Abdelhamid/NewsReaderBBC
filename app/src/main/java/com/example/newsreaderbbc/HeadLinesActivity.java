package com.example.newsreaderbbc;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class HeadLinesActivity extends AppCompatActivity {

    /** Declaration of Variables.
     * @param headlines An  array of strings, each representing a single headline of the newsfeed.
     */
    ArrayList<String> headlines, hyperLinks;
    ListView news_feed_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_lines);
        news_feed_list = (ListView) findViewById(R.id.news_list);

        headlines = new ArrayList<String>();
        hyperLinks = new ArrayList<String>();

        news_feed_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {

                Uri newLink = Uri.parse(hyperLinks.get(index));
                Intent intent = new Intent(Intent.ACTION_VIEW, newLink);
                startActivity(intent);
            }
        });

        new HeadLinesActivity.ProcessInBackground().execute();


    }



    public InputStream getInputStream(URL url)
    {
        try{
            return  url.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            return null;
        }
    }




    public class ProcessInBackground extends AsyncTask<Integer, Void, String>
    {
        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();



        }

        @Override
        protected String doInBackground(Integer... integers) {
            try{
                URL rssLink = new URL("https://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(rssLink), "UTF_8");
                boolean foundUnit = false;
                int docIndices = xpp.getEventType();

                while (docIndices != XmlPullParser.END_DOCUMENT)
                {
                    if (docIndices == XmlPullParser.START_TAG)
                    {
                        if (xpp.getName().equalsIgnoreCase("item"))
                        {
                            foundUnit = true;
                        }
                        else if (xpp.getName().equalsIgnoreCase("title"))
                        {
                            if (foundUnit)
                            {
                                headlines.add(xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("link"))
                        {
                            if (foundUnit)
                            {
                                hyperLinks.add(xpp.nextText());
                            }
                        }
                    }
                    else if (docIndices == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item"))
                    {
                        foundUnit = false;
                    }
                    docIndices = xpp.next();
                }

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(HeadLinesActivity.this, android.R.layout.simple_list_item_1, headlines);
            news_feed_list.setAdapter(adapter);


        }
    }







}