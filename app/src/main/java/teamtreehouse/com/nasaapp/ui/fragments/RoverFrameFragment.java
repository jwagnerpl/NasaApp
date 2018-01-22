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
        return inflater.inflate(R.layout.fragment_container, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RoverImageFragment rif = new RoverImageFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame1, rif, "addRover").commit();
    }

    public static RoverFrameFragment newInstance() {
        RoverFrameFragment rf = new RoverFrameFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", 0);
        args.putString("someTitle", "Rover Image");
        rf.setArguments(args);
        return rf;
    }

}
