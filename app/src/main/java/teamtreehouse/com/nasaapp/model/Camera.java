package teamtreehouse.com.nasaapp.model;

import java.util.ArrayList;

public class Camera {
    String camAbbreviation;
    String camDescription;
    ArrayList<Camera> cameras;
    int[] accessibleCrafts;

    public Camera(String camAbbreviation, String camDescription, int[] accessibleCrafts) {
        this.camAbbreviation = camAbbreviation;
        this.camDescription = camDescription;
    }

    public Camera(){}

    public ArrayList<Camera> getCameraList() {
        cameras = new ArrayList<>();
        cameras.add(new Camera("fhaz","Front Hazard Avoidance Camera",new int[]{1,2,3}));
        cameras.add(new Camera("rhaz","Rear Hazard Avoidance Camera",new int[]{1,2,3}));
        cameras.add(new Camera("mast","Mast Camera",new int[]{1}));
        cameras.add(new Camera("chemcam","Chemistry and Camera Complex",new int[]{1}));
        cameras.add(new Camera("mahli","Mars Hand Lens Imager",new int[]{1}));
        cameras.add(new Camera("mardi","Mars Descent Imager",new int[]{1}));
        cameras.add(new Camera("navcam","Navigation Camera",new int[]{1,2,3}));
        cameras.add(new Camera("pancam","Panoramic Camera",new int[]{2,3}));
        cameras.add(new Camera("minitest","Front Hazard Avoidance Camera",new int[]{2,3}));
        return cameras;
    }
}
