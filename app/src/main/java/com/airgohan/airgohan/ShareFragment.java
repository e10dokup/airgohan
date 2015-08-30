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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShareFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShareFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShareFragment extends Fragment implements View.OnClickListener {

    Event mEvent = new Event();
    Event mTargetEvent;


    private static final String ARG_SHARE_ID = "arg_share_id";

    private int mParam;

    // TODO: Rename and change types and number of parameters
    public static ShareFragment newInstance(int param) {
        ShareFragment fragment = new ShareFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SHARE_ID, param);
        fragment.setArguments(args);
        return fragment;
    }

    public ShareFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getInt(ARG_SHARE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Event event = new Event();
        mEvent.getEvents(getActivity().getApplicationContext(), mSuccessListener, mErrorListener);
    }

    private Response.Listener<JSONObject> mSuccessListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d("CalenderFragment", response.toString());
            try{
                JSONArray values = response.getJSONArray("records");
                mTargetEvent = new Event(values.getJSONObject(0));
                initControl(mTargetEvent);

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

    private void initControl(Event event){

        ImageView foodView = (ImageView)getView().findViewById(R.id.foodImage);
        ImageView hosView = (ImageView)getView().findViewById(R.id.hostImage);

        TextView nameText =  (TextView)getView().findViewById(R.id.hostText);
        nameText.setText(event.getName());

        TextView addressText =  (TextView)getView().findViewById(R.id.addressText);
        addressText.setText(event.getAddress());

        TextView mainText =  (TextView)getView().findViewById(R.id.mainText);
        mainText.setText(event.getMainMenu());

        TextView genreText =  (TextView)getView().findViewById(R.id.genreText);
        genreText.setText(event.getGenre());

        TextView commentText =  (TextView)getView().findViewById(R.id.commentText);
        commentText.setText("");

        this.getView().findViewById(R.id.hostImage).setOnClickListener(this);

        TextView timeText =  (TextView)getView().findViewById(R.id.timeText);
        timeText.setText(String.format("%d年%d月%d日 %d時%d分 〜 %d年%d月%d日 %d時%d分",
                event.getStartDate().getYear() + 1900,
                event.getStartDate().getMonth() + 1,
                event.getStartDate().getDay(),
                event.getStartDate().getHours(),
                event.getStartDate().getMinutes(),
                event.getFinishDate().getYear() + 1900,
                event.getFinishDate().getMonth() + 1,
                event.getFinishDate().getDay(),
                event.getFinishDate().getHours(),
                event.getFinishDate().getMinutes()));


        this.getView().findViewById(R.id.reserveButton).setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.reserveButton:
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.fragment, ReserveFragment.newInstance(mTargetEvent.getId()));
            transaction.addToBackStack(null);
            transaction.commit();
            break;
            case R.id.hostImage:
            showHostInfo();
            break;
        }
    }

    private void showHostInfo(){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, new HostInfoFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
