package teamtreehouse.com.nasaapp.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import teamtreehouse.com.nasaapp.model.DateRangeData;

public interface NasaClient {
    String NASA_PHOTOS_BASE_URI = "https://api.nasa.gov/mars-photos/api/v1/";
    String API_KEY = "N1ZUNUU8Arq28WsOwrHGApmFe2L7CeXy4d06YNFk";

    @GET("manifests/curiosity?api_key=DEMO_KEY")
    Observable<DateRangeData> getCuriosityDateRange();

    @GET("manifests/opportunity?api_key=DEMO_KEY")
    Observable<DateRangeData> getOpportunityDateRange();

    @GET("manifests/spirit?api_key=DEMO_KEY")
    Observable<DateRangeData> getSpiritDateRange();
}
