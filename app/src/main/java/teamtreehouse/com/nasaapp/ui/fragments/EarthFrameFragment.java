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

public class EarthFrameFragment extends Fragment {

    String frame;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.earth_fragment_container, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            EarthViewFragment rif = new EarthViewFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.earthFrame1, rif, "addEarthView").commit();
    }

    public static EarthFrameFragment newInstance(int page, String title) {
        EarthFrameFragment rf = new EarthFrameFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle",title);
        rf.setArguments(args);
        return rf;
    }

}
