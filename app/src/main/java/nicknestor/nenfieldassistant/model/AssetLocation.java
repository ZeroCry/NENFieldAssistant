package nicknestor.nenfieldassistant.model;

        import java.io.Serializable;

public class AssetLocation implements Serializable {

    public static final String TAG = "Asset";
    private static final long serialVersionUID = -7406082437623008161L;

    private Integer mId;
    private Integer mAsset_id;
    private Integer mLocation_id;
    private Integer mAreas_id;
    private String mTimestamp;
    private String mNotes;
    private String mUser;

    public AssetLocation() {

    }

    public AssetLocation(Integer _id, Integer Asset_id, Integer Location_id, Integer Areas_id, String Timestamp,String Notes,String User) {
        this.mId = _id;
        this.mAsset_id = Asset_id;
        this.mLocation_id = Location_id;
        this.mAreas_id = Areas_id;
        this.mTimestamp = Timestamp;
        this.mNotes = Notes;
        this.mUser = User;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    public Integer getAsset_id() {
        return mAsset_id;
    }

    public void setAsset_id(Integer mAsset_id) {
        this.mAsset_id = mAsset_id;
    }

    public Integer getLocation_id() {
        return mLocation_id;
    }

    public void setLocation_id(Integer mLocation_id) {
        this.mLocation_id = mLocation_id;
    }

    public Integer getAreas_id() {
        return mAreas_id;
    }

    public void setAreas_id(Integer mAreas_id) {
        this.mAreas_id = mAreas_id;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String mTimestamp) {
        this.mTimestamp = mTimestamp;
    }
    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String mNotes) {
        this.mNotes = mNotes;
    }
    public String getUser() {
        return mUser;
    }

    public void setUser(String mUser) {
        this.mUser = mUser;
    }



}
