package com.example.newsreaderbbc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class TwitterFragment extends Fragment {

    /** This is the fragment in which the data of BBC on the Twitter platform is diplayed and is invoked by
     * Twitter button press on the ApplicationGuide activity
     */

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_twitter, container, false);

        Toast.makeText(getActivity(), "Second Fragment - BBC on Twitter", Toast.LENGTH_LONG).show();

        return view;
    }
}
