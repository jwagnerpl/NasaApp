package teamtreehouse.com.nasaapp.ui.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.adapters.ItemClickListener;
import teamtreehouse.com.nasaapp.adapters.RoverCameraRecylerAdapter;
import teamtreehouse.com.nasaapp.api.ApiCall;
import teamtreehouse.com.nasaapp.date_model.Camera;
import teamtreehouse.com.nasaapp.photo_model.Photo;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;
import teamtreehouse.com.nasaapp.utilities.Utilities;

public class SelectCameraFragment extends android.app.Fragment implements ItemClickListener {
    private static final String TAG = "SelectCameraFragment";
    RecyclerView recyclerView;
    RoverCameraRecylerAdapter roverCameraRecylerAdapter;
    ArrayList<Camera> cameras;
    public static ArrayList<Photo> photos;
    int columns;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_camera, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.cameraSelectRecyclerView);
        columns = Utilities.calculateNoOfColumns(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), columns));
        cameras = new ArrayList<>();

        for(Camera camera: Camera.getCameraList()){
                    for(int craft: camera.getAccessibleCrafts()){
                        Log.d(TAG, craft + "");
                        Log.d(TAG, Camera.selectedRover + " here is the selected rover");
                        if(craft == Camera.selectedRover){
                            Log.d(TAG, Camera.selectedRover + " selected Rover");
                            Log.d(TAG, craft + " is the craft selected");
                            cameras.add(camera);
                        }
                    }
        }

        Log.d(TAG, cameras.toString());
        roverCameraRecylerAdapter = new RoverCameraRecylerAdapter(cameras);
        roverCameraRecylerAdapter.setClickListener(this);
        recyclerView.setAdapter(roverCameraRecylerAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "click registered");
        Log.d(TAG, cameras.get(position).getCamAbbreviation());
        Log.d(TAG, MainActivity.selectedDate);
        Log.d(TAG, MainActivity.selectedRover);
        ApiCall apiCall = new ApiCall();
        apiCall.getPhotos(MainActivity.selectedRover,MainActivity.selectedDate,cameras.get(position).getCamAbbreviation(),getFragmentManager());
    }

    public static void startImageRecyclerFragment(FragmentManager fragmentManager){
        RoverImageRecyclerFragment rirf = new RoverImageRecyclerFragment();
        FragmentManager fm = fragmentManager;
        FragmentTransaction ft = fm.beginTransaction();
        Log.d(TAG, "fragmentO");
        //ft.  // addToBackStack("roverImageRecyclerFragment");
        //ft.detach(fm.findFragmentByTag("selectCameraFragment"));
        ft.replace(R.id.frame1,rirf);
        //ft.addToBackStack("roverImageRecyclerFragment");
        ft.commit();
    }
}
