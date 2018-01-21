package teamtreehouse.com.nasaapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.adapters.MyFragmentPagerAdapter;

public class MainFragment extends android.app.Fragment {
    private static final String TAG = "MainFragment";

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Builds copyright notice as dialog builder
        if (item.getItemId() == R.id.copyrightInfo) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Copyright Info");
            alertDialog.setNeutralButton("OK", null);
            alertDialog.setMessage("Rover Images and soundclips Courtesy NASA/JPL-Caltech\n\nSpace background photo by Jack Cain on Unsplash\n\nAll images are used for educational purposes only.");
            alertDialog.show();
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("iNeedSpace");

            actionBar.show();
        }
        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager vp = view.findViewById(R.id.viewPager);
        MyFragmentPagerAdapter adapterViewPager = new MyFragmentPagerAdapter(getFragmentManager());
        vp.setAdapter(adapterViewPager);
        TabLayout tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vp);
    }

}
