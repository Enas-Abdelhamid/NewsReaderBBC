package com.example.newsreaderbbc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FBFragment extends Fragment {

    /** This is the fragment in which the data of BBC on the FB platform is diplayed and is invoked by
     * FB button press on the ApplicationGuide activity
     */
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_f_b, container, false);

                Toast.makeText(getActivity(), "First Fragment - BBC on Facebook", Toast.LENGTH_LONG).show();

        return view;
    }
}
