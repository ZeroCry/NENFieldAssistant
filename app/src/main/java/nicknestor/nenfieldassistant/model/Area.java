package nicknestor.nenfieldassistant.model;

        import java.io.Serializable;

public class Area implements Serializable {

    public static final String TAG = "Area";
    private static final long serialVersionUID = -7406082437623008161L;

    private Long mAreaId;
    private Integer mArea;

    //public Area() {};

    public Area(Long Area_id, Integer area) {
        this.mAreaId = Area_id;
        this.mArea = area;
    }

    public Long getAreaId() {
        return mAreaId;
    }
    public void setAreaId(Long mAreaId) {
        this.mAreaId = mAreaId;
    }

    public Integer getArea() {
        return mArea;
    }
    public void setArea(Integer mArea) {
        this.mArea = mArea;
    }


}
