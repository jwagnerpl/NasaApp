package teamtreehouse.com.nasaapp.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import teamtreehouse.com.nasaapp.R;

public class RoverImageFragment extends Fragment {

    private static final java.lang.String ARG_PAGE = "ARG_Page";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rover_image,container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int page = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static RoverImageFragment newInstance(int page, String title) {
        RoverImageFragment ri = new RoverImageFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle",title);
        ri.setArguments(args);
        return ri;
    }
}