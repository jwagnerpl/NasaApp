package teamtreehouse.com.nasaapp.earth_model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EarthPhoto {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("cloud_score")
        @Expose
        private Float cloudScore;
        @SerializedName("id")
        @Expose
        private String id;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Float getCloudScore() {
            return cloudScore;
        }

        public void setCloudScore(Float cloudScore) {
            this.cloudScore = cloudScore;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }