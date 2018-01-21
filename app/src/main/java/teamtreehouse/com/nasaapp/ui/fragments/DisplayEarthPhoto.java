package teamtreehouse.com.nasaapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;

public class DisplayEarthPhoto extends android.app.Fragment {
    TextView imageInfoTextview;
    String earthUri;
    ImageView iv;

    private static final String TAG = "DisplayEarthPhoto";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_earth_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        earthUri = MainActivity.earthUri;
        iv = getActivity().findViewById(R.id.earthImage);
        Picasso.with(getActivity()).load(earthUri).into(iv);


        // Splits URI returned for earthimage at token so can be compared against expected value in unit test.
        imageInfoTextview = getActivity().findViewById(R.id.imageInfo);
        String[] parts = earthUri.split("\\&");
        imageInfoTextview.setText(parts[0]);

    }
}
