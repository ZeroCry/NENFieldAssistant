package nicknestor.nenfieldassistant.model;

import java.io.Serializable;

/**
 * Created by Nick on 12/19/2015.
 */
public class FillType implements Serializable {

    public static final String TAG = "FillType";
    private static final long serialVersionUID = -7406082437623008161L;


    private long mFillTypeId;
    private String mFillTypeName;
    private String mFillTypeTeam;
    private String mFillTypeWhole;
    private String mFillTypePart;
    private String mFillTypeUserId;
    private String mFillTypeTimestamp;


/*    public FillType(Long _id, String filltype, String abbr, String filltypeNumber, String address, String city, String state, String zip, String phone) {
        this.mId = _id;
        this.mFillType = filltype;
        this.mAbbr = abbr;
        this.mFillTypeNumber = filltypeNumber;
        this.mAddress = address;
        this.mCity = city;
        this.mState = state;
        this.mZip = zip;
        this.mPhone = phone;
    }*/

    public FillType(){};


    public long getFillTypeId() {return mFillTypeId;}
    public void setFillTypeId(long mFillTypeId) {this.mFillTypeId = mFillTypeId;}

    public String getFillTypeName() { return mFillTypeName;}
    public void setFillTypeName(String mFillTypeName) {this.mFillTypeName = mFillTypeName;}

    public String getFillTypeTeam() { return mFillTypeTeam;}
    public void setFillTypeTeam(String mFillTypeTeam) {this.mFillTypeTeam = mFillTypeTeam;}

    public String getFillTypeWhole() { return mFillTypeWhole;}
    public void setFillTypeWhole(String mFillTypeWhole) {this.mFillTypeWhole = mFillTypeWhole;}

    public String getFillTypePart() { return mFillTypePart;}
    public void setFillTypePart(String mFillTypePart) {this.mFillTypePart = mFillTypePart;}

    public String getFillTypeUserId() { return mFillTypeUserId;}
    public void setFillTypeUserId(String mFillTypeUserId) {this.mFillTypeUserId = mFillTypeUserId;}

    public String getFillTypeTimestamp() { return mFillTypeTimestamp;}
    public void setFillTypeTimestamp(String mFillTypeTimestamp) {this.mFillTypeTimestamp = mFillTypeTimestamp;}

}
