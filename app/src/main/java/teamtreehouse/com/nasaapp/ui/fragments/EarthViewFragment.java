package teamtreehouse.com.nasaapp.ui.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.api.ApiCall;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;

public class EarthViewFragment extends android.app.Fragment {

    private static final java.lang.String ARG_PAGE = "ARGPage";
    Button submitButton;
    EditText addressFieldEditText;
    private static final String TAG = "EarthViewFragment";
    private Handler handler;
    String coordinates;
    private EditText dateFieldEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_earth_view,container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitButton = view.findViewById(R.id.coordinateSubmitButton);
        addressFieldEditText = view.findViewById(R.id.addressInput);
        dateFieldEditText = view.findViewById(R.id.dateInput);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String address = addressFieldEditText.getText().toString();
                String date = dateFieldEditText.getText().toString();

                class GetCoordinatesTask extends AsyncTask<Void,Void,Void>{
                    String address;

                    public GetCoordinatesTask(String address) {
                        this.address = address;
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        coordinates = getCoordinates(address);
                        Log.d(TAG, coordinates);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        if(!coordinates.equals("")){
                            String[] latlong = coordinates.split(";");
                            ApiCall apiCall = new ApiCall();
                            apiCall.getEarthSnapshot(date,latlong[0],latlong[1],getActivity(), getFragmentManager());
                        }
                        else{Toast.makeText(getActivity().getApplicationContext(), "Try a different address.",Toast.LENGTH_LONG).show();
                        }
                    }
                }

                new GetCoordinatesTask(address).execute();

            }

        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int page = getArguments().getInt(ARG_PAGE);
    }

    public static EarthViewFragment newInstance(int page, String title) {

            EarthViewFragment ev = new EarthViewFragment();
            Bundle args = new Bundle();
            args.putInt("someInt", page);
            args.putString("someTitle",title);
            ev.setArguments(args);
            return ev;
    }
    String getCoordinates(String address) {
        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocationName(address, 1);
        } catch (IOException e) {
            e.printStackTrace();
            addresses = null;
        }
        if (addresses.size() > 0) {
            double latitude = addresses.get(0).getLatitude();
            double longitude = addresses.get(0).getLongitude();
            return latitude + ";" + longitude;
        }
        else{
            return "";
        }
    }

}