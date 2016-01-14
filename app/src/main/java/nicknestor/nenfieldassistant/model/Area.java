package nicknestor.nenfieldassistant.model;

        import java.io.Serializable;

public class Area implements Serializable {

    public static final String TAG = "Area";
    private static final long serialVersionUID = -7406082437623008161L;

    private long mId;
    private String mArea;

    public Area() {

    }

    public Area(Long area_id, String area) {
        this.mId = area_id;
        this.mArea = area;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getArea() {
        return mArea;
    }

    public void setArea(String mArea) {
        this.mArea = mArea;
    }


}
