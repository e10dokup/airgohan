package com.airgohan.airgohan;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.airgohan.airgohan.model.Event;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.apache.http.impl.cookie.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CalendarFragment extends Fragment {

    @Bind(R.id.view_calender)
    CalendarView mCalendarView;

    Event mEvent = new Event();

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

        mEvent.getHostEvents(getActivity(), 1, mSuccessListener, mErrorListener);
        mCalendarView.setOnDateChangeListener(mOnDateChangeListener);
        
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
            for(Event event : Utils.events){
//                Toast.makeText(getActivity(), String.valueOf(e.getStartDate().getTime()), Toast.LENGTH_LONG).show();
                Date eventTrntDate = clearTime(event.getStartDate());
                SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
                Date selectedDate = new Date();
                try{
                    selectedDate = sdf.parse(String.valueOf(year) + "-" + String.valueOf(month+1) + "-" + String.valueOf(dayOfMonth));
                }catch(ParseException e){
                    e.printStackTrace();
                }
                /*
                if(selectedDate.equals(eventTrntDate)){
                    Utils.selectedEvent = event;
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction  = manager.beginTransaction();
                    transaction.replace(R.id.content_frame, new HostEventFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                }
                */
            }
        }
    };

    private Response.Listener<JSONObject> mSuccessListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try{
                JSONArray values = response.getJSONArray("records");
                for(int i = 0; i < values.length(); i++){
                    JSONObject value = values.getJSONObject(i);
                    Utils.events.add(new Event(value));
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

    static Date clearTime(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.clear(Calendar.AM_PM);
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.HOUR_OF_DAY);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        return cal.getTime();
    }

}
