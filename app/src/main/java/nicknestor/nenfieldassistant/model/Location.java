package nicknestor.nenfieldassistant.model;

import java.io.Serializable;

/**
 * Created by Nick on 12/19/2015.
 */
public class Location implements Serializable {

    public static final String TAG = "Location";


    private long mId;
    private String mStore;
    private String mAbbr;
    private String mStoreNumber;
    private String mAddress;
    private String mCity;
    private String mState;
    private String mZip;
    private String mPhone;

    public Location(){};

    public Location(String store, String abbr, String storeNumber, String address, String city, String state, String zip, String phone) {
        this.mStore = store;
        this.mAbbr = abbr;
        this.mStoreNumber = storeNumber;
        this.mAddress = address;
        this.mCity = city;
        this.mState = state;
        this.mZip = zip;
        this.mPhone = phone;
    }


    public long getId() {return mId;}
    public void setId(long mId) {this.mId = mId;}
    public String getStore() { return mStore;}
    public void setStore(String mStore) {this.mStore = mStore;}
    public String getAbbr() {return mAbbr;}
    public void setAbbr(String mAbbr) {this.mAbbr = mAbbr;}
    public String getStoreNumber() {return mStoreNumber;}
    public void setStoreNumber(String mStoreNumber) {this.mStoreNumber = mStoreNumber;}
    public String getAddress() {return mAddress;}
    public void setAddress(String mAddress) {this.mAddress = mAddress;}
    public String getCity() {return mCity;}
    public void setCity(String mCity) {this.mCity = mCity;}
    public String getState() {return mState;}
    public void setState(String mState) {this.mState = mState;}
    public String getZip() {return mZip;}
    public void setZip(String mZip) {this.mZip = mZip;}
    public String getPhone() {return mPhone;}
    public void setPhone(String mPhone) {this.mPhone = mPhone;}




}
