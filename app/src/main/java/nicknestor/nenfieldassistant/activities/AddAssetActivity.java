package nicknestor.nenfieldassistant.activities;


import java.util.List;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.UsefulClasses;
import nicknestor.nenfieldassistant.adapter.SpinnerAreasAdapter;
import nicknestor.nenfieldassistant.adapter.SpinnerLocationsAdapter;
import nicknestor.nenfieldassistant.dao.AreasDAO;
import nicknestor.nenfieldassistant.dao.AssetLocationsDAO;
import nicknestor.nenfieldassistant.dao.LocationDAO;
import nicknestor.nenfieldassistant.dao.AssetDAO;
import nicknestor.nenfieldassistant.model.Area;
import nicknestor.nenfieldassistant.model.AssetLocation;
import nicknestor.nenfieldassistant.model.Location;
import nicknestor.nenfieldassistant.model.Asset;

public class AddAssetActivity extends Activity implements OnClickListener, OnItemSelectedListener {

    public static final String TAG = "AddAssetActivity";

    private EditText mTxtAssetNumber;
    private Button mBtnAdd;
    private Spinner mSpinnerLocation;
    private Spinner mSpinnerArea;
    private Spinner mSpinnerCategory;
    private Spinner mSpinnerMachinetype;
    private EditText mTxtNotes;

    private LocationDAO mLocationDao;
    private AssetDAO mAssetDao;
    private AreasDAO mAreasDao;
    private AssetLocationsDAO mAssetLocationDao;

    private Area mSelectedArea;
    private Location mSelectedLocation;
    private SpinnerLocationsAdapter mLocationsAdapter;
    private SpinnerAreasAdapter mAreasAdapter;


    private String[] array_areas;
    private String[] array_category;
    private String[] array_crane_types;
    private String[] array_bulk_types;
    private String[] array_ride_types;
    private String[] array_video_types;
    private String[] array_type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);
        array_areas = getResources().getStringArray(R.array.areas_array);
        array_category = getResources().getStringArray(R.array.array_category);
        array_crane_types = getResources().getStringArray(R.array.array_crane_types);
        array_bulk_types = getResources().getStringArray(R.array.array_bulk_types);
        array_ride_types = getResources().getStringArray(R.array.array_ride_types);
        array_video_types = getResources().getStringArray(R.array.array_video_types);
        array_type = getResources().getStringArray(R.array.array_crane_types);


        initViews();

        this.mLocationDao = new LocationDAO(this);
        this.mAssetDao = new AssetDAO(this);
        this.mAreasDao = new AreasDAO(this);
        this.mAssetLocationDao = new AssetLocationsDAO(this);


        List<Location> listLocations = mLocationDao.getAllLocations();
        if (listLocations != null) {
            mLocationsAdapter = new SpinnerLocationsAdapter(this, listLocations);
            mSpinnerLocation.setAdapter(mLocationsAdapter);
            mSpinnerLocation.setOnItemSelectedListener(this);

        }


/*        List<Area> listAreas = mAreasDao.getAllAreas();
        if (listAreas != null) {
            mAreasAdapter = new SpinnerAreasAdapter(this, listAreas);
            mSpinnerArea.setAdapter(mAreasAdapter);
            mSpinnerArea.setOnItemSelectedListener(this);
        }*/

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_category);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCategory.setAdapter(categoryAdapter);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_type);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMachinetype.setAdapter(typeAdapter);

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_areas);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerArea.setAdapter(areasAdapter);
    }



    private void initViews() {
        this.mTxtAssetNumber = (EditText) findViewById(R.id.txt_assetnumber);
        this.mSpinnerCategory = (Spinner) findViewById(R.id.spinner_category);
        this.mSpinnerMachinetype = (Spinner) findViewById(R.id.spinner_machinetype);
        this.mSpinnerLocation = (Spinner) findViewById(R.id.spinner_location);
        this.mSpinnerArea = (Spinner) findViewById(R.id.spinner_area);
        this.mTxtNotes = (EditText) findViewById(R.id.txt_notes);
        this.mBtnAdd = (Button) findViewById(R.id.btn_add);

        this.mBtnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Editable assetnumber = mTxtAssetNumber.getText();
                mSelectedLocation = (Location) mSpinnerLocation.getSelectedItem();
                //mSelectedArea = (Area) mSpinnerArea.getSelectedItem();

                if (
                        !TextUtils.isEmpty(assetnumber)
                                //&& mSpinnerCategory.getSelectedItemPosition() != 0
                                //&& mSpinnerMachinetype.getSelectedItemPosition() != 0
                                //&& mSelectedLocation != null
                                //&& mSelectedArea != null
                                 ) {

//TODO add the asset to database
                    Asset createdAsset = mAssetDao.createAsset(
                            assetnumber.toString(),
                            mSpinnerCategory.getSelectedItemPosition(),
                            mSpinnerMachinetype.getSelectedItemPosition()
                    );

                    AssetDAO getIDforAsset = mAssetDao.getIDforAsset(mTxtAssetNumber);

                    AssetLocation createdAssetLocation = mAssetLocationDao.createAssetLocation(
                            getIDforAsset.asset_id,
                            mSelectedLocation.getLocationId(),
                            mSpinnerArea.getSelectedItemId(),
                            UsefulClasses.getCurrentTimeStamp(),
                            mTxtNotes.toString(),
                            mTxtNotes.toString()
                            );

                    Log.d(TAG, "Asset Added : " +
                            "Asset=" + assetnumber.toString() + " " +
                            "Category=" + mSpinnerCategory.getSelectedItemPosition() + " " +
                            "Type=" + mSpinnerMachinetype.getSelectedItemPosition() + " " +
                            "Location=" + mSelectedLocation + " " +
                            "Area=" + mSelectedArea);
                    //Log.d(TAG, "added asset : " + assetnumber.toString());
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(this, R.string.empty_fields_message, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Failed adding asset : " +
                            "Asset=" + assetnumber.toString() + " " +
                            "Category=" + mSpinnerCategory.getSelectedItemPosition() + " " +
                            "Type=" + mSpinnerMachinetype.getSelectedItemPosition() + " " +
                            "Location=" + mSelectedLocation + " " +
                            "Area=" + mSelectedArea);

                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationDao.close();
        mAreasDao.close();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSelectedLocation = mLocationsAdapter.getItem(position);
//        mSelectedArea = mAreasAdapter.getItem(position);
        Log.d(TAG, "selectedCompany : " + mSelectedLocation.getStore());

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    @Override
    public void onStart() {
        super.onStart();


        mSpinnerCategory.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d(TAG,"Selected category : " + mSpinnerCategory.getSelectedItemId());
//TODO This OnItemSelectedListener is supposed to set the machinetype spinner options based on what is selected from the category spinner
                if (mSpinnerCategory.getSelectedItemId() == 1) {
                    array_type = getResources().getStringArray(R.array.array_crane_types);
                } else if (mSpinnerCategory.getSelectedItemId() == 2) {
                    array_type = getResources().getStringArray(R.array.array_bulk_types);
                } else if (mSpinnerCategory.getSelectedItemId() == 3) {
                    array_type = getResources().getStringArray(R.array.array_ride_types);
                } else if (mSpinnerCategory.getSelectedItemId() == 4){
                    array_type = getResources().getStringArray(R.array.array_bulk_types);
                } else if (mSpinnerCategory.getSelectedItemId() == 5){
//TODO Array Other Types
                    array_type = getResources().getStringArray(R.array.array_crane_types);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();


    }
}
