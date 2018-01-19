package teamtreehouse.com.nasaapp.ui.activities;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.List;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.date_model.CraftDates;
import teamtreehouse.com.nasaapp.photo_model.Photo;
import teamtreehouse.com.nasaapp.ui.fragments.StartScreenFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static Context context;
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
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createStartScreenFrag(savedInstanceState);
        context = this;
    }

    private void createStartScreenFrag(Bundle savedInstanceState) {
        Fragment startScreenFragment = new StartScreenFragment();
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, startScreenFragment).commit();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    @Override
    public void onBackPressed() {
        android.app.FragmentManager fm = getFragmentManager();
        fm.popBackStack();
    }
}
