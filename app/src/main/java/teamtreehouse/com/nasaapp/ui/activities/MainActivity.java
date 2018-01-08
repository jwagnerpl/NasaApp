package teamtreehouse.com.nasaapp.ui.activities;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.ui.fragments.StartScreenFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

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
    }

    private void createStartScreenFrag(Bundle savedInstanceState) {
        android.support.v4.app.Fragment startScreenFragment = new StartScreenFragment();
        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, startScreenFragment).commit();
    }
}
