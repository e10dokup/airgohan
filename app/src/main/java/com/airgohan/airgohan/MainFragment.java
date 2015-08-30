package com.airgohan.airgohan;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MainFragment extends Fragment implements View.OnClickListener {


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.getView().findViewById(R.id.freImage).setOnClickListener(this);
        this.getView().findViewById(R.id.chuImage).setOnClickListener(this);
        this.getView().findViewById(R.id.waImage).setOnClickListener(this);
        this.getView().findViewById(R.id.yoImage).setOnClickListener(this);
        this.getView().findViewById(R.id.searchButton).setOnClickListener(this);

    }
    /*
     @Override
     public void onAttach(Activity activity) {

     }

     @Override
     public void onDetach() {
         super.onDetach();

     }
 */
    @Override
    public void onClick(View view) {

        int id=3;
        switch(view.getId()){
            case R.id.freImage:
                searchGenre(3);
                break;
            case R.id.chuImage:
                searchGenre(2);
                break;
            case R.id.waImage:
                searchGenre(1);
                break;
            case R.id.yoImage:
                searchGenre(4);
                break;
            case R.id.searchButton:
                search();
                break;
            default:
                return;
        }



    }

    private void searchKeyword(){

    }

    private void search(){
        /*
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, new SearchFragment());
        transaction.addToBackStack(null);
        transaction.commit();
        */
        EditText text = (EditText)getView().findViewById(R.id.edit_name);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, new SearchFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }



    private void searchGenre(int id){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //transaction.replace(R.id., NextFragment.newInstance());
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

}
