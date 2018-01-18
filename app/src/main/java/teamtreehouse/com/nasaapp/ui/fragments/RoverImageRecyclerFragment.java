package teamtreehouse.com.nasaapp.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.adapters.RoverCameraRecylerAdapter;
import teamtreehouse.com.nasaapp.adapters.RoverImageRecyclerAdapter;
import teamtreehouse.com.nasaapp.photo_model.Photo;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;
import teamtreehouse.com.nasaapp.utilities.Utilities;

public class RoverImageRecyclerFragment extends android.app.Fragment {

    RecyclerView recyclerView;
    RoverImageRecyclerAdapter adapter;
    ArrayList<Photo> photos;
    int columns;
    private static final String TAG = "RoverImageRecyclerFragm";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rover_image_recycler, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photos = MainActivity.photoList;
        Log.d(TAG,photos.toString());
        adapter = new RoverImageRecyclerAdapter(photos);
        recyclerView = view.findViewById(R.id.imageRecyclerView);
        columns = Utilities.calculateNoOfColumns(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), columns));
        recyclerView.setAdapter(adapter);
    }
}
