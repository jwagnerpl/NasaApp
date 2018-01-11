package teamtreehouse.com.nasaapp.photo_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
