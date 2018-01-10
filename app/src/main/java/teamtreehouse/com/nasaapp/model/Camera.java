package teamtreehouse.com.nasaapp.model;

import java.util.ArrayList;

public class Camera {
    public String camAbbreviation;
    public String camDescription;
    static ArrayList<Camera> cameras;
    public static int selectedRover;
    public int[] accessibleCrafts;

    public Camera(String camAbbreviation, String camDescription, int[] accessibleCrafts) {
        this.camAbbreviation = camAbbreviation;
        this.camDescription = camDescription;
        this.accessibleCrafts = accessibleCrafts;
    }

    public Camera(){}

    public static ArrayList<Camera> getCameraList() {
        cameras = new ArrayList<>();
        cameras.add(new Camera("fhaz","Front Hazard Avoidance Camera",new int[]{0,1,2}));
        cameras.add(new Camera("rhaz","Rear Hazard Avoidance Camera",new int[]{0,1,2}));
        cameras.add(new Camera("mast","Mast Camera",new int[]{0}));
        cameras.add(new Camera("chemcam","Chemistry and Camera Complex",new int[]{0}));
        cameras.add(new Camera("mahli","Mars Hand Lens Imager",new int[]{0}));
        cameras.add(new Camera("mardi","Mars Descent Imager",new int[]{0}));
        cameras.add(new Camera("navcam","Navigation Camera",new int[]{0,1,2}));
        cameras.add(new Camera("pancam","Panoramic Camera",new int[]{1,2}));
        cameras.add(new Camera("minitest","Front Hazard Avoidance Camera",new int[]{1,2}));
        return cameras;
    }

    public String getCamAbbreviation() {
        return camAbbreviation;
    }

    public void setCamAbbreviation(String camAbbreviation) {
        this.camAbbreviation = camAbbreviation;
    }

    public String getCamDescription() {
        return camDescription;
    }

    public void setCamDescription(String camDescription) {
        this.camDescription = camDescription;
    }

    public static ArrayList<Camera> getCameras() {
        return cameras;
    }

    public static void setCameras(ArrayList<Camera> cameras) {
        Camera.cameras = cameras;
    }

    public static int getSelectedRover() {
        return selectedRover;
    }

    public static void setSelectedRover(int selectedRover) {
        Camera.selectedRover = selectedRover;
    }

    public int[] getAccessibleCrafts() {
        return accessibleCrafts;
    }

    public void setAccessibleCrafts(int[] accessibleCrafts) {
        this.accessibleCrafts = accessibleCrafts;
    }
}
