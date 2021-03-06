package teamtreehouse.com.nasaapp.adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.date_model.Camera;
import teamtreehouse.com.nasaapp.date_model.CraftDates;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;
import teamtreehouse.com.nasaapp.ui.fragments.RoverImageFragment;

public class MyPagerAdapter extends PagerAdapter implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    private ArrayList<Integer> images;
    private Fragment fragment;
    private Calendar minCalendar;
    private Calendar maxCalendar;
    private LayoutInflater inflater;
    private Context context;
    private Long curiosityLandDate;
    private Long curiosityMaxDate;
    private Long opportunityLandDate;
    private Long opportunityMaxDate;
    private Long spiritLandDate;
    private Long spiritMaxDate;
    private FragmentManager fm;


    private static final String TAG = "MyPagerAdapter";

    public MyPagerAdapter(ArrayList<Integer> images, Context context) {
        this.images = images;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) throws android.net.ParseException {
        View imageLayout = inflater.inflate(R.layout.viewpager_rover, container, false);
        assert imageLayout != null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        minCalendar = Calendar.getInstance();
        maxCalendar = Calendar.getInstance();

        for (CraftDates date : MainActivity.craftDates) {
            switch (date.getName()) {
                case "Opportunity":
                    try {
                        opportunityLandDate = sdf.parse(date.getLandDate()).getTime();
                        opportunityMaxDate = sdf.parse(date.getMaxDate()).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Curiosity":
                    try {
                        curiosityLandDate = sdf.parse(date.getLandDate()).getTime();
                        curiosityMaxDate = sdf.parse(date.getMaxDate()).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Spirit":
                    try {
                        spiritLandDate = sdf.parse(date.getLandDate()).getTime();
                        spiritMaxDate = sdf.parse(date.getMaxDate()).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        Log.d(TAG, "here ww are");
        final ImageView imageView = imageLayout.findViewById(R.id.roverImageView);
        final Button roverButton = imageLayout.findViewById(R.id.roverButton);
        switch (position) {
            case 0:
                roverButton.setText("Curiosity \n Select this rover.");
                break;
            case 1:
                roverButton.setText("Opportunity \n Select this rover.");
                break;
            case 2:
                roverButton.setText("Spirit \n Select this rover.");
                break;
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(images.get(position));
        final CoordinatorLayout cl = imageLayout.findViewById(R.id.coordinatorLayout);
        container.addView(imageLayout, 0);
        roverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(position){
                    case 0: MainActivity.selectedRover = "curiosity";
                        break;
                    case 1: MainActivity.selectedRover = "opportunity";
                        break;
                    case 2: MainActivity.selectedRover = "spirit";
                        break;
                }

                Camera.selectedRover = position;
                Log.d(TAG, "roverButton set");
                Calendar now = Calendar.getInstance();
                // Refactor - Find better way to instantiate datepicker than creating additional instance of roverimagefragment
                RoverImageFragment roverImageFragment = new RoverImageFragment();
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.
                        DatePickerDialog.newInstance(/*refactor here*/roverImageFragment,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                Toast.makeText(context, "Choose a date to view rover images.",Toast.LENGTH_LONG);

                switch (position) {
                    case 0:
                        maxCalendar.setTimeInMillis(curiosityMaxDate);
                        minCalendar.setTimeInMillis(curiosityLandDate);
                        break;
                    case 1:
                        maxCalendar.setTimeInMillis(opportunityMaxDate);
                        minCalendar.setTimeInMillis(opportunityLandDate);
                        break;
                    case 2:
                        maxCalendar.setTimeInMillis(spiritMaxDate);
                        minCalendar.setTimeInMillis(spiritLandDate);
                        break;
                }

                dpd.setMinDate(minCalendar);
                dpd.setMaxDate(maxCalendar);
                dpd.showYearPickerFirst(true);
                dpd.show(RoverImageFragment.fm, "hello");
            }
        });

        return imageLayout;
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }


}
