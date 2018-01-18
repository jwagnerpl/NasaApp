package teamtreehouse.com.nasaapp.ui.fragments;

import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.api.ApiCall;
import teamtreehouse.com.nasaapp.date_model.CraftDates;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;


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


        ApiCall apiCall = new ApiCall();
        apiCall.getDates();

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
