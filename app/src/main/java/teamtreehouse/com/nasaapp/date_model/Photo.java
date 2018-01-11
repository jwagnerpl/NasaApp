package teamtreehouse.com.nasaapp.date_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {

        @SerializedName("sol")
        @Expose
        private Integer sol;
        @SerializedName("total_photos")
        @Expose
        private Integer totalPhotos;
        @SerializedName("cameras")
        @Expose
        private List<String> cameras = null;

        public Integer getSol() {
            return sol;
        }

        public void setSol(Integer sol) {
            this.sol = sol;
        }

        public Integer getTotalPhotos() {
            return totalPhotos;
        }

        public void setTotalPhotos(Integer totalPhotos) {
            this.totalPhotos = totalPhotos;
        }

        public List<String> getCameras() {
            return cameras;
        }

        public void setCameras(List<String> cameras) {
            this.cameras = cameras;
        }

}
