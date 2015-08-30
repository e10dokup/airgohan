package com.airgohan.airgohan;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airgohan.airgohan.model.Event;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HostEventFragment extends Fragment {
    private static final String TAG = HostEventFragment.class.getSimpleName();
    private final HostEventFragment self = this;

    @Bind(R.id.btn_create)
    Button mCreateButton;
    @Bind(R.id.edit_name)
    EditText mNameEdit;
    @Bind(R.id.edit_address)
    EditText mAddressEdit;
    @Bind(R.id.edit_genre)
    EditText mGenreEdit;
    @Bind(R.id.edit_main_menu)
    EditText mMainMenuEdit;
    @Bind(R.id.edit_max_members)
    EditText mMaxMembersEdit;
    @Bind(R.id.edit_start_time)
    EditText mStartTimeEdit;
    @Bind(R.id.edit_finish_time)
    EditText mFinishTimeEdit;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
    Event event = Utils.selectedEvent;

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

        if(event != null){
            mNameEdit.setText(event.getName());
            mAddressEdit.setText(event.getAddress());
            mGenreEdit.setText(event.getGenre());
            mMainMenuEdit.setText(event.getMainMenu());
            mMaxMembersEdit.setText(String.valueOf(event.getMaxMember()));
            mStartTimeEdit.setText(sdf.format(event.getStartDate()));
            mFinishTimeEdit.setText(sdf.format(event.getFinishDate()));
        }
        return view;
    }

    @OnClick(R.id.btn_create)
    public void onCreatButtonClicked(){

        if(event != null) {
            try {
                Event newEvent = new Event(event.getId(), event.getHostId(),
                        mNameEdit.getText().toString(), mAddressEdit.getText().toString(),
                        mGenreEdit.getText().toString(), mMainMenuEdit.getText().toString(),
                        sdf.parse(mStartTimeEdit.getText().toString()), sdf.parse(mFinishTimeEdit.getText().toString()),
                        Integer.parseInt(mMaxMembersEdit.getText().toString()));
                newEvent.putEvents(getActivity(), mPutSuccessListener, mErrorListener);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    Response.Listener<JSONObject> mPutSuccessListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d(TAG, response.toString());
            FragmentManager manager = getFragmentManager();
            manager.popBackStack();
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG, error.toString());
            Toast.makeText(getActivity(), "何か問題があるよ", Toast.LENGTH_LONG).show();
        }
    };
}