package com.airgohan.airgohan;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.airgohan.airgohan.model.Event;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CalendarFragment extends Fragment {

    @Bind(R.id.view_calender)
    CalendarView mCalendarView;

    Event mEvent = new Event();
    List<Event> mEvents = new ArrayList<Event>();

    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        return fragment;
    }

    public CalendarFragment() {
        // Required empty public constructor
    }

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
        mCalendarView.setOnDateChangeListener(mOnDateChangeListener);

        mEvent.getHostEvents(getActivity(), 1, mSuccessListener, mErrorListener);
        
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    // month is 0-origin
    CalendarView.OnDateChangeListener mOnDateChangeListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            Log.d("CalendaerFragment",String.valueOf(year) +String.valueOf(month+1) + String.valueOf(dayOfMonth));
        }
    };

    private Response.Listener<JSONObject> mSuccessListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d("CalenderFragment", response.toString());
            try{
                JSONArray values = response.getJSONArray("records");
                for(int i = 0; i < values.length(); i++){
                    JSONObject value = values.getJSONObject(i);
                    mEvents.add(new Event(value));
                }
            }catch(JSONException e){
                Log.d("CalenderFragment", e.toString());
                Toast.makeText(getActivity(), "何か問題があるよ", Toast.LENGTH_LONG).show();
            }
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getActivity(), "何か問題があるよ", Toast.LENGTH_LONG).show();
        }
    };

}
