package com.airgohan.airgohan;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HostEventFragment extends Fragment {
    private static final String TAG = HostEventFragment.class.getSimpleName();
    private final HostEventFragment self = this;

    @Bind(R.id.btn_create)
    Button mCreateButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_host_event, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_create)
    public void onCreatButtonClicked(){
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
}