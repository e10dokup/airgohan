package com.airgohan.airgohan;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.airgohan.airgohan.model.Event;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShareListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShareListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShareListFragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    Event mEvent = new Event();
    List<Event> mEvents = new ArrayList<Event>();

    private ListView mListView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShareListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShareListFragment newInstance(String param1, String param2) {
        ShareListFragment fragment = new ShareListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ShareListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = (ListView)getView().findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);

        Event event = new Event();
        mEvent.getEvents(getActivity().getApplicationContext(), mSuccessListener, mErrorListener);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
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
            Log.d("CalenderFragment", response.toString());
            try{
                JSONArray values = response.getJSONArray("records");
                for(int i = 0; i < values.length(); i++){
                    JSONObject value = values.getJSONObject(i);
                    mEvents.add(new Event(value));
                }


                ShareListAdapter adapter = new ShareListAdapter(getActivity().getApplicationContext(), R.layout.adapter_sharelist, mEvents);
                mListView.setAdapter(adapter);

            }catch(JSONException e){
                Log.d("CalenderFragment", e.toString());
                Toast.makeText(getActivity(), "何か問題があるよ", Toast.LENGTH_LONG).show();
            }
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Event event = (Event)adapterView.getAdapter().getItem(i);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, ShareFragment.newInstance(event.getId()));
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
