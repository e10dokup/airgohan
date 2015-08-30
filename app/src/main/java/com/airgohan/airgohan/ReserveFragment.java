package com.airgohan.airgohan;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReserveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReserveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReserveFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHARE_ID = "age_share_id";

    // TODO: Rename and change types of parameters
    private int mParam;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param Parameter 1.
     * @return A new instance of fragment RegistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReserveFragment newInstance(int param) {
        ReserveFragment fragment = new ReserveFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SHARE_ID, param);
        fragment.setArguments(args);
        return fragment;
    }

    public ReserveFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reserve, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        if(view.getId() == R.id.reserveButton) {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.fragment, new MailSendFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
