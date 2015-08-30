package com.airgohan.airgohan;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public class HostEventFragment extends Fragment {
    private static final String TAG = HostEventFragment.class.getSimpleName();
    private final HostEventFragment self = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
}