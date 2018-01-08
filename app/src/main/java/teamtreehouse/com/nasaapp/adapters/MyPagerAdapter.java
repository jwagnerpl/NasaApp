package teamtreehouse.com.nasaapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import teamtreehouse.com.nasaapp.ui.fragments.EarthViewFragment;
import teamtreehouse.com.nasaapp.ui.fragments.RoverImageFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {
    int NUMBER_ITEMS = 2;
    private String tabTitles[] = new String[] {"Mars Rover", "Eye in the Sky"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return RoverImageFragment.newInstance(0, "Rover Image");

            case 1:
                return EarthViewFragment.newInstance(0, "Earth View");
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return NUMBER_ITEMS;
    }
}
