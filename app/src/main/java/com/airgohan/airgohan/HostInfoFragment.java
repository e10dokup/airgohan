package com.airgohan.airgohan;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airgohan.airgohan.model.Event;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HostInfoFragment extends Fragment {

    TextView tvHostName;
    ImageView ivHost;
    Event mEvent = new Event();
    List<Event> mEvents = new ArrayList<Event>();
    Integer mHostId;
    ListView lvHostInfo;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");

    public HostInfoFragment() {
        //TODO:
        mHostId = 1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_host_info, container, false);

        mEvent.getHostEvents(getActivity(), mHostId, mSuccessListener, mErrorListener);

        tvHostName = (TextView)v.findViewById(R.id.textView_host_name);
        ivHost = (ImageView)v.findViewById(R.id.imageView_host);
        lvHostInfo = (ListView)v.findViewById(R.id.listView_hostinfo);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void search(){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, new ShareListFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE:
                if (enter) {
                    return AnimatorInflater.loadAnimator(getActivity(), android.R.animator.fade_in);
                } else {
                    return AnimatorInflater.loadAnimator(getActivity(), android.R.animator.fade_out);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_CLOSE:
                if (enter) {
                    return AnimatorInflater.loadAnimator(getActivity(), android.R.animator.fade_in);
                } else {
                    return AnimatorInflater.loadAnimator(getActivity(), android.R.animator.fade_out);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
            default:
                if (enter) {
                    return AnimatorInflater.loadAnimator(getActivity(), android.R.animator.fade_in);
                } else {
                    return AnimatorInflater.loadAnimator(getActivity(), android.R.animator.fade_out);
                }
        }
    }

    private Response.Listener<JSONObject> mSuccessListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d("HostInfoFragment", response.toString());
            try{
                JSONArray values = response.getJSONArray("records");
                for(int i = 0; i < values.length(); i++){
                    JSONObject value = values.getJSONObject(i);
                    mEvents.add(new Event(value));
                    ArrayList<String> hostInfos = new ArrayList<String>();
                    for(Event e: mEvents){
                        if (mHostId == e.getHostId()) {
                            String name = e.getName();
                            Date startDate = e.getStartDate();
                            String genre = e.getGenre();
                            Log.d("HostInfoFragment", "name:" + name);
                            Log.d("HostInfoFragment", "startDate:" + sdf.format(startDate));
                            Log.d("HostInfoFragment", "genre:" + genre);
                            tvHostName.setText(name + " さん");
                            String hostInfo = sdf.format(startDate) + "    " + genre;
                            hostInfos.add(hostInfo);
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_expandable_list_item_1, hostInfos);
                    lvHostInfo.setAdapter(adapter);
                }
            }catch(JSONException e){
                Log.d("HostInfoFragment", e.toString());
                Toast.makeText(getActivity(), "何か問題があるよ2", Toast.LENGTH_LONG).show();
            }
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("HostInfoFragment", error.toString());
            Toast.makeText(getActivity(), "何か問題があるよ1", Toast.LENGTH_LONG).show();
        }
    };
}
