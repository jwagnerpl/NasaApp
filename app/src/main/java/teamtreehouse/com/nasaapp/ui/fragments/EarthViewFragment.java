package teamtreehouse.com.nasaapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import teamtreehouse.com.nasaapp.R;

public class EarthViewFragment extends android.app.Fragment {

    private static final java.lang.String ARG_PAGE = "ARGPage";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_earth_view,container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int page = getArguments().getInt(ARG_PAGE);
    }

    public static EarthViewFragment newInstance(int page, String title) {

            EarthViewFragment ev = new EarthViewFragment();
            Bundle args = new Bundle();
            args.putInt("someInt", page);
            args.putString("someTitle",title);
            ev.setArguments(args);
            return ev;
    }
}