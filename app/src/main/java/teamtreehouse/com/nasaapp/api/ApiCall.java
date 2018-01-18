package teamtreehouse.com.nasaapp.api;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teamtreehouse.com.nasaapp.date_model.CraftDates;
import teamtreehouse.com.nasaapp.date_model.DateRangeData;
import teamtreehouse.com.nasaapp.date_model.PhotoManifest;
import teamtreehouse.com.nasaapp.photo_model.Photo;
import teamtreehouse.com.nasaapp.photo_model.Photos;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;
import teamtreehouse.com.nasaapp.ui.fragments.RoverImageRecyclerFragment;
import teamtreehouse.com.nasaapp.ui.fragments.SelectCameraFragment;

import static teamtreehouse.com.nasaapp.ui.fragments.RoverImageFragment.context;


public class ApiCall extends Fragment {

    String curiosityLandDate;
    String curiosityMaxDate;
    String opportunityLandDate;
    String opportunityMaxDate;
    String spiritLandDate;
    String spiritMaxDate;
    SharedPreferences sharedPreferences;
    private static final String TAG = "ApiCall";
    private ArrayList<Photo> photoList;

    final ArrayList<CraftDates> craftDates = new ArrayList<>();

    public void getPhotos(String craft, String date, String abbrev, FragmentManager fm){


        Observer observer = new Observer(){

            @Override
            public void onSubscribe(Disposable d) {
                photoList = new ArrayList<>();
            }

            @Override
            public void onNext(Object value) {
                Log.d(TAG, value.toString());
            Photos photos = (Photos) value;
            Log.d(TAG, photos.toString() + "here are photos");
            Log.d(TAG, photos.getPhotos().toString() + "here is get photos");
            for(Photo photo: photos.getPhotos()){
                photo.getImgSrc();
                Log.d(TAG, photo.toString());
                photoList.add(photo);
            }

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Call went wrong" + e.toString());
            }

            @Override
            public void onComplete() {
                if(photoList.size() != 0) {
                    Log.d(TAG, photoList.toString() + "this is the photo list");
                    MainActivity.photoList = photoList;
                    SelectCameraFragment scf = new SelectCameraFragment();
                    SelectCameraFragment.startImageRecyclerFragment(fm);
                }

                else{
                    Toast.makeText(context,"sorry, choose another camera.", Toast.LENGTH_LONG );
                }

            }
        };

        NasaClient nasaClient = getNasaClient();
        Observable<Photos> photoCall = nasaClient.getRequestedPhotos(craft, date, abbrev);
        photoCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }


    public ArrayList<CraftDates> getDates() {

        MainActivity mainActivity = new MainActivity();
        Observer observer = new Observer() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribed");
            }

            @Override
            public void onNext(Object value) {
                DateRangeData dateRangeData = (DateRangeData) value;
                PhotoManifest photoManifest = dateRangeData.getPhotoManifest();
                String landDate = photoManifest.getLandingDate();
                String name = photoManifest.getName();
                String maxDate = photoManifest.getMaxDate();
                craftDates.add(new CraftDates(maxDate, landDate, name));
            }


            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error processing request for dates;");
                Log.d(TAG, e.toString());
            }

            @Override
            public void onComplete() {
                if(craftDates.size() == 3){
                    Log.d(TAG, "we have 3");
                    MainActivity.craftDates = craftDates;
                }
                else{Log.d(TAG, craftDates.size() + "");}
            }
        };

        NasaClient nasaClient = getNasaClient();
        Observable<DateRangeData> curiosityCall = nasaClient.getCuriosityDateRange();
        Observable<DateRangeData> opportunityCall = nasaClient.getOpportunityDateRange();
        Observable<DateRangeData> spiritCall = nasaClient.getSpiritDateRange();

        curiosityCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        opportunityCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        spiritCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

        return craftDates;
    }

    private NasaClient getNasaClient() {
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(NasaClient.NASA_PHOTOS_BASE_URI).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();

        return retrofit.create(NasaClient.class);
    }
}
