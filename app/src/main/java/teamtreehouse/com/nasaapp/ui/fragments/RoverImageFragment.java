package teamtreehouse.com.nasaapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.adapters.MyPagerAdapter;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;

public class RoverImageFragment extends android.app.Fragment implements DatePickerDialog.OnDateSetListener{

    public static Context context;
    private static final String TAG = "RoverImageFragment";
    public static android.app.FragmentManager fm;
    ArrayList<Integer> imageArray = new ArrayList<>();
    ViewPager viewPager;
    private static final Integer[] IMAGES = {R.drawable.curiosity,R.drawable.opportunity , R.drawable.spirit};

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

    private void init() {
        for(int i=0; i<IMAGES.length; i++)
            imageArray.add(IMAGES[i]);

        viewPager = getView().findViewById(R.id.roverViewPager);
        viewPager.setAdapter(new MyPagerAdapter(imageArray, getActivity().getApplicationContext(), getActivity()));
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        MainActivity.selectedDate = year + "-" + monthOfYear+1 + "-" + dayOfMonth;
        Log.d(TAG, MainActivity.selectedDate);
        SelectCameraFragment scf = new SelectCameraFragment();
        android.app.FragmentManager fragmentManager = view.getFragmentManager();
        android.app.FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.detach(fm.findFragmentByTag("addRover"));
        ft.replace(R.id.frame1, scf).commit();
    }
}
