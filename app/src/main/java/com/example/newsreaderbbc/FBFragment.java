package com.example.newsreaderbbc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FBFragment extends Fragment {


    View view;
    Button firstButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_f_b, container, false);
        // get the reference of Button

                Toast.makeText(getActivity(), "First Fragment - BBC on Facebook", Toast.LENGTH_LONG).show();

        return view;
    }
}
