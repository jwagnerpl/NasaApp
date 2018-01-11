package teamtreehouse.com.nasaapp.date_model;

public class CraftDates {
    String maxDate;
    String landDate;
    String name;

    public CraftDates(String maxDate, String landDate, String name) {
        this.maxDate = maxDate;
        this.landDate = landDate;
        this.name = name;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public String getLandDate() {
        return landDate;
    }

    public String getName() {
        return name;
    }
}
