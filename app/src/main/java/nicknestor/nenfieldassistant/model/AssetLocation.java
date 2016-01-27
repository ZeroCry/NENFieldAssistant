package nicknestor.nenfieldassistant.model;

        import java.io.Serializable;

public class AssetLocation implements Serializable {

    public static final String TAG = "Asset";
    private static final long serialVersionUID = -7406082437623008161L;

    private long mAssetLocationId;
    private long mAsset_id;
    private long mLocation_id;
    private long mAreas_id;
    private String mTimestamp;
    private String mNotes;
    private String mUser;

    public AssetLocation() {
    }

    public AssetLocation(Long AssetLocation_id, Long Asset_id, Long Location_id, Long Areas_id, String Timestamp, String Notes, String User) {
        this.mAssetLocationId = AssetLocation_id;
        this.mAsset_id = Asset_id;
        this.mLocation_id = Location_id;
        this.mAreas_id = Areas_id;
        this.mTimestamp = Timestamp;
        this.mNotes = Notes;
        this.mUser = User;
    }

    public Long getAssetLocationId() {
        return mAssetLocationId;
    }
    public void setAssetLocationId(Long mAssetLocationId) {this.mAssetLocationId = mAssetLocationId;}

    public Long getAsset_id() {
        return mAsset_id;
    }
    public void setAsset_id(Long mAsset_id) {
        this.mAsset_id = mAsset_id;
    }

    public Long getLocation_id() {return mLocation_id;}
    public void setLocation_id(Long mLocation_id) {
        this.mLocation_id = mLocation_id;
    }

    public Long getAreas_id() {
        return mAreas_id;
    }
    public void setAreas_id(Long mAreas_id) {
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

    public String getUser() {return mUser;}
    public void setUser(String mUser) {this.mUser = mUser;}



}
