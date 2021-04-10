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
    ArrayList<String> headlines, hyperLinks, articleDates, descriptionTexts;
    ListView news_feed_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_lines);
        news_feed_list = (ListView) findViewById(R.id.news_list);

        /** creating array for each attribute of articles
         */
        headlines = new ArrayList<String>();
        hyperLinks = new ArrayList<String>();
        articleDates = new ArrayList<String>();
        descriptionTexts = new ArrayList<String>();


/** Upon a click of any article on the headlies list, this activity
 * starts another activity that displays the details of the chosen article
 */
        news_feed_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {

                Uri newLink = Uri.parse(hyperLinks.get(index));
                String singleArticleTitle = (headlines.get(index));
                String singleArticleDate = (articleDates.get(index));
                String singleArticleDescription = (descriptionTexts.get(index));
                Intent i = new Intent(HeadLinesActivity.this, FavouritesControlActivity.class);

                /** Passing the chosen article details to the next activity
                 */
              i.putExtra("urlkey",newLink.toString());
              i.putExtra("titlekey",singleArticleTitle);
                i.putExtra("datekey",singleArticleDate);
                i.putExtra("descriptionkey",singleArticleDescription);
                startActivity(i);

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


        /** Parsing the details of articles from the rss newsfeed of BBC using the XmlPullParser
         */

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
                                /** Adding parsed titles to the headlines array
                                 */
                                headlines.add(xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("link"))
                        {
                            if (foundUnit)
                            {
                                /** Adding parsed links to the hyperlinks array
                                 */
                                hyperLinks.add(xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("pubDate"))
                        {
                            if (foundUnit)
                            {
                                /** Adding parsed publishing dates to the articleDates array
                                 */
                                articleDates.add(xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("description"))
                        {
                            if (foundUnit)
                            {
                                /** Adding parsed descriptions to the description array
                                 */
                                descriptionTexts.add(xpp.nextText());
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
            /** setting the listView adapter after execution of parsing
             * and transferring data to arrays
             */
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(HeadLinesActivity.this, android.R.layout.simple_list_item_1, headlines);
            news_feed_list.setAdapter(adapter);


        }
    }







}