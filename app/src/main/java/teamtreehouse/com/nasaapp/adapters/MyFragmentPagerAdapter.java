package teamtreehouse.com.nasaapp.adapters;

import teamtreehouse.com.nasaapp.ui.fragments.EarthViewFragment;
import teamtreehouse.com.nasaapp.ui.fragments.RoverImageFragment;

public class MyFragmentPagerAdapter extends android.support.v13.app.FragmentPagerAdapter {
    int NUMBER_ITEMS = 2;
    private String tabTitles[] = new String[] {"Mars Rover", "Eye in the Sky"};

    public MyFragmentPagerAdapter(android.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.app.Fragment getItem(int position) {
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
