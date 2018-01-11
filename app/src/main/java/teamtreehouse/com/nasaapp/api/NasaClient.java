package teamtreehouse.com.nasaapp.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import teamtreehouse.com.nasaapp.date_model.DateRangeData;

public interface NasaClient {
    String NASA_PHOTOS_BASE_URI = "https://api.nasa.gov/mars-photos/api/v1/";
    String API_KEY = "N1ZUNUU8Arq28WsOwrHGApmFe2L7CeXy4d06YNFk";

    @GET("manifests/curiosity?api_key=N1ZUNUU8Arq28WsOwrHGApmFe2L7CeXy4d06YNFk")
    Observable<DateRangeData> getCuriosityDateRange();

    @GET("manifests/opportunity?api_key=N1ZUNUU8Arq28WsOwrHGApmFe2L7CeXy4d06YNFk")
    Observable<DateRangeData> getOpportunityDateRange();

    @GET("manifests/spirit?api_key=N1ZUNUU8Arq28WsOwrHGApmFe2L7CeXy4d06YNFk")
    Observable<DateRangeData> getSpiritDateRange();
}
