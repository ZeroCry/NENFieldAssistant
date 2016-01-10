package nicknestor.nenfieldassistant.model;

        import java.io.Serializable;

public class Asset implements Serializable {

    public static final String TAG = "Asset";
    private static final long serialVersionUID = -7406082437623008161L;

    private long mId;
    private String mAssetNumber;
    private String mCategory;
    private String mMachineType;
    private String mLocation;

/*    public Asset() {

    }
*/
    public Asset(String assetnumber, String category, String machinetype, String location) {
        this.mAssetNumber = assetnumber;
        this.mCategory = category;
        this.mMachineType = machinetype;
        this.mLocation = location;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

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

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

}
