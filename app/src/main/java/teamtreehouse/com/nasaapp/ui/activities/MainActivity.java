package teamtreehouse.com.nasaapp.ui.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.date_model.CraftDates;
import teamtreehouse.com.nasaapp.photo_model.Photo;
import teamtreehouse.com.nasaapp.ui.fragments.StartScreenFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<CraftDates> craftDates;
    public static String selectedDate;
    public static String selectedRover;
    public static ArrayList<Photo> photoList;
    public static String earthUri;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        //Remove actionbar for app start screen
        if(getSupportActionBar() != null) getSupportActionBar().hide();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        createStartScreenFrag(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

    }

    private void createStartScreenFrag(Bundle savedInstanceState) {
        Fragment startScreenFragment = new StartScreenFragment();
        android.app.FragmentManager fm = getFragmentManager();

        //Inflate start screen fragment into main fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, startScreenFragment).commit();
    }

    @Override
    public void onBackPressed() {
        android.app.FragmentManager fm = getFragmentManager();
        fm.popBackStack();
    }
}
