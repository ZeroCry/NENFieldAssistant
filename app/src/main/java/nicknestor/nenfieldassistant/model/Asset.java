package nicknestor.nenfieldassistant.model;

        import java.io.Serializable;

public class Asset implements Serializable {

    public static final String TAG = "Asset";
    private static final long serialVersionUID = -7406082437623008161L;

    private Long mAssetId;
    private String mAssetNumber;
    private String mCategory;
    private String mMachineType;
    private Location mLocation;

    public Asset() {

    }

    public Asset(Long asset_id, String assetnumber, String category, String machinetype) {
        this.mAssetId = asset_id;
        this.mAssetNumber = assetnumber;
        this.mCategory = category;
        this.mMachineType = machinetype;
    }

    public Long getAssetId() {return mAssetId;}
    public void setAssetId(Long mAssetId) {this.mAssetId = mAssetId;}

    public String getAssetNumber() {
        return mAssetNumber;
    }
    public void setAssetNumber(String mAssetNumber) {
        this.mAssetNumber = mAssetNumber;
    }

    public String getCategory() {
        return mCategory;
    }
    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getMachineType() {
        return mMachineType;
    }
    public void setMachineType(String mMachineType) {
        this.mMachineType = mMachineType;
    }

    public Location getLocation() {
        return mLocation;
    }
    public void setLocation(Location mLocation) {
        this.mLocation = mLocation;
    }
}
