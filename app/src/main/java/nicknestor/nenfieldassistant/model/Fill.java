package nicknestor.nenfieldassistant.model;

        import java.io.Serializable;

public class Fill implements Serializable {

    public static final String TAG = "Fill";
    private static final long serialVersionUID = -7406082437623008161L;

    private Long mFillId;
    private String mFill;

    //public Fill() {};

    public Fill(Long Fill_id, String fill) {
        this.mFillId = Fill_id;
        this.mFill = fill;
    }

    public Long getFillId() {
        return mFillId;
    }
    public void setFillId(Long mFillId) {
        this.mFillId = mFillId;
    }

    public String getFill() {
        return mFill;
    }
    public void setFill(String mFill) {
        this.mFill = mFill;
    }


}
