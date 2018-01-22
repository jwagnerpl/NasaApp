package teamtreehouse.com.nasaapp.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import teamtreehouse.com.nasaapp.date_model.DateRangeData;
import teamtreehouse.com.nasaapp.earth_model.EarthPhoto;
import teamtreehouse.com.nasaapp.photo_model.Photos;

public interface NasaClient {
    String NASA_PHOTOS_BASE_URI = "https://api.nasa.gov/mars-photos/api/v1/";
    String NASA_EARTH_BASE_URI = "https://api.nasa.gov/planetary/earth/";

    @GET("manifests/curiosity?api_key=N1ZUNUU8Arq28WsOwrHGApmFe2L7CeXy4d06YNFk")
    Observable<DateRangeData> getCuriosityDateRange();

    @GET("manifests/opportunity?api_key=N1ZUNUU8Arq28WsOwrHGApmFe2L7CeXy4d06YNFk")
    Observable<DateRangeData> getOpportunityDateRange();

    @GET("manifests/spirit?api_key=N1ZUNUU8Arq28WsOwrHGApmFe2L7CeXy4d06YNFk")
    Observable<DateRangeData> getSpiritDateRange();

    @GET("rovers/{rover}/photos?api_key=N1ZUNUU8Arq28WsOwrHGApmFe2L7CeXy4d06YNFk")
    Observable<Photos> getRequestedPhotos(@Path("rover") String rover, @Query("earth_date") String earthDate, @Query("camera") String camAbbrev);

    @GET("imagery?api_key=N1ZUNUU8Arq28WsOwrHGApmFe2L7CeXy4d06YNFk")
    Observable<EarthPhoto> getEarthPhoto(@Query("date") String date, @Query("lat") String latitude, @Query("lon") String longitude);
}
