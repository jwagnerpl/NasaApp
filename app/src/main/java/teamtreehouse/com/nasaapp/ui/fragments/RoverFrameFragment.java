package teamtreehouse.com.nasaapp.ui.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import teamtreehouse.com.nasaapp.R;

public class RoverFrameFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RoverImageFragment rif = new RoverImageFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame2, rif, "addRover").commit();
    }

    public static RoverFrameFragment newInstance(int page, String title) {
        RoverFrameFragment rf = new RoverFrameFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle",title);
        rf.setArguments(args);
        return rf;
    }

}
