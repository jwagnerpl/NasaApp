package teamtreehouse.com.nasaapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DateRangeData {

    @SerializedName("photo_manifest")
    @Expose
    private PhotoManifest photoManifest;

    public PhotoManifest getPhotoManifest() {
        return photoManifest;
    }

    public void setPhotoManifest(PhotoManifest photoManifest) {
        this.photoManifest = photoManifest;
    }
}
