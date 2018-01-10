package teamtreehouse.com.nasaapp.api;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teamtreehouse.com.nasaapp.R;
import teamtreehouse.com.nasaapp.model.CraftDates;
import teamtreehouse.com.nasaapp.model.DateRangeData;
import teamtreehouse.com.nasaapp.model.PhotoManifest;
import teamtreehouse.com.nasaapp.ui.activities.MainActivity;
import teamtreehouse.com.nasaapp.ui.fragments.StartScreenFragment;


public class DateRangeApiCall extends Fragment {

    String curiosityLandDate;
    String curiosityMaxDate;
    String opportunityLandDate;
    String opportunityMaxDate;
    String spiritLandDate;
    String spiritMaxDate;
    SharedPreferences sharedPreferences;
    private static final String TAG = "DateRangeApiCall";

    final ArrayList<CraftDates> craftDates = new ArrayList<>();

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

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(NasaClient.NASA_PHOTOS_BASE_URI).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Retrofit retrofit = builder.build();

        NasaClient nasaClient = retrofit.create(NasaClient.class);
        Observable<DateRangeData> curiosityCall = nasaClient.getCuriosityDateRange();
        Observable<DateRangeData> opportunityCall = nasaClient.getOpportunityDateRange();
        Observable<DateRangeData> spiritCall = nasaClient.getSpiritDateRange();

        curiosityCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        opportunityCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        spiritCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

        return craftDates;
    }
}
