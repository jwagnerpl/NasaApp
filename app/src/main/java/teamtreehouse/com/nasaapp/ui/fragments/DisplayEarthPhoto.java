package teamtreehouse.com.nasaapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;

public class DisplayEarthPhoto extends android.app.Fragment {
    TextView textView;

    private static final String TAG = "DisplayEarthPhoto";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_earth_photo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = getActivity().findViewById(R.id.imageInfo);
        ImageView iv = getActivity().findViewById(R.id.earthImage);
        Picasso.with(getActivity()).load(MainActivity.earthUri).into(iv);
        String string = MainActivity.earthUri;
        String[] parts = string.split("\\&");
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556
        textView.setText(part1);
        Log.d(TAG,textView.getText().toString());
    }
}
