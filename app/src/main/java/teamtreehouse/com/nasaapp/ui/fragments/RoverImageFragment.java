package teamtreehouse.com.nasaapp.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.adapters.MyPagerAdapter;

public class RoverImageFragment extends android.app.Fragment implements DatePickerDialog.OnDateSetListener{

    public static Context context;
    private static final String TAG = "RoverImageFragment";
    public static android.app.FragmentManager fm;
    ArrayList<Integer> imageArray = new ArrayList<>();
    ViewPager viewPager;
    private static final Integer[] IMAGES = {R.drawable.opportunity, R.drawable.curiosity, R.drawable.spirit};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rover_image, container, false);
        context = getActivity().getApplicationContext();
        fm = getFragmentManager();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager vp = view.findViewById(R.id.roverViewPager);
        init();
    }

    public static RoverImageFragment newInstance(int page, String title) {
        RoverImageFragment ri = new RoverImageFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle",title);
        ri.setArguments(args);
        return ri;
    }

    private void init() {
        for(int i=0; i<IMAGES.length; i++)
            imageArray.add(IMAGES[i]);

        viewPager = getView().findViewById(R.id.roverViewPager);
        viewPager.setAdapter(new MyPagerAdapter(imageArray, getActivity().getApplicationContext(), getActivity()));
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }
}
