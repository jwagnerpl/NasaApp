package teamtreehouse.com.nasaapp.ui.fragments;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.api.DateRangeApiCall;
import teamtreehouse.com.nasaapp.model.CraftDates;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static android.R.anim.*;


public class StartScreenFragment extends android.app.Fragment {

    public static ArrayList<CraftDates> craftDates;
    Button launchButton;
    private static final String TAG = "StartScreenFragment";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_screen, container, false);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        launchButton = view.findViewById(R.id.launchButton);

        DateRangeApiCall dateRangeApiCall = new DateRangeApiCall();
        dateRangeApiCall.getDates();

        launchButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                view.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        Handler handler = new Handler();
        waitFiveSeconds(handler);
    }

        void waitFiveSeconds(Handler handler){
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                if(MainActivity.craftDates != null) {
                    startFragment();
                }

                else{waitFiveSeconds(handler);}

            }
        }, 6000);
    }


        void startFragment(){
        android.app.Fragment mainFragment = new MainFragment();
        android.app.FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        //fragmentTransaction.setCustomAnimations(fade_in, fade_out);
        fragmentTransaction.replace(R.id.placeHolder, mainFragment).commit();
    }


}
