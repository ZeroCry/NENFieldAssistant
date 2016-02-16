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
import nicknestor.nenfieldassistant.adapter.SpinnerFillAdapter;
import nicknestor.nenfieldassistant.adapter.SpinnerLocationsAdapter;
import nicknestor.nenfieldassistant.dao.AreasDAO;
import nicknestor.nenfieldassistant.dao.AssetLocationsDAO;
import nicknestor.nenfieldassistant.dao.FillDAO;
import nicknestor.nenfieldassistant.dao.LocationDAO;
import nicknestor.nenfieldassistant.dao.AssetDAO;
import nicknestor.nenfieldassistant.model.Area;
import nicknestor.nenfieldassistant.model.AssetLocation;
import nicknestor.nenfieldassistant.model.Fill;
import nicknestor.nenfieldassistant.model.Location;
import nicknestor.nenfieldassistant.model.Asset;

public class AddAssetActivity extends Activity implements OnClickListener, OnItemSelectedListener {

    public static final String TAG = "AddAssetActivity";

    private EditText mTxtAssetNumber;
    private Spinner mSpinnerLocation;
    private Spinner mSpinnerArea;
    private Spinner mSpinnerCategory;

//Crane
    private Spinner mSpinnerCranetype;
    private Spinner mSpinnerFill;
    private Spinner mSpinnerClawSize;
    private EditText mTxtHeader;
    private EditText mTxtClings;

//Bulk
    private Spinner mSpinnerBulkType;
    private EditText mTxtBulkLayout;

//Ride Video
    private Spinner mSpinnerVideotype;
    private Spinner mSpinnerRidetype;
    private EditText mTxtRideVideoName;
    private EditText mTxtRideVideoRowPrice;

//Generic
    private EditText mTxtNotes;
    private Button mBtnAdd;



    private LocationDAO mLocationDao;
    private AssetDAO mAssetDao;
    private AreasDAO mAreasDao;
    private AssetLocationsDAO mAssetLocationDao;
    private FillDAO mFillDao;


    private Location mSelectedLocation;
    private SpinnerLocationsAdapter mLocationsAdapter;
    private Area mSelectedArea;
    private SpinnerAreasAdapter mAreasAdapter;
    private Fill mSelectedFill;
    private SpinnerFillAdapter mFillAdapter;
/*    private Category mSelectedCategory;
    private SpinnerCategoryAdapter mCategoryAdapter;
    private CraneType mCraneType;
    private SpinnerCraneType mCraneTypeAdapter;
    private ClawSize mSelectedClawSize;
    private SpinnerClawSize mClawSizeAdapter;
*/



    private String[] array_areas;
    private String[] array_category;
    private String[] array_crane_types;
    private String[] array_bulk_types;
    private String[] array_ride_types;
    private String[] array_video_types;
    private String[] array_type;
    private String[] array_claw_size;



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
        array_claw_size = getResources().getStringArray(R.array.array_claw_size);

        initViews();

        this.mLocationDao = new LocationDAO(this);
        this.mAssetDao = new AssetDAO(this);
        this.mAreasDao = new AreasDAO(this);
        this.mAssetLocationDao = new AssetLocationsDAO(this);
        this.mFillDao = new FillDAO(this);


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

        List<Fill> listFills = mFillDao.getAllFills();
        if (listFills != null) {
            mFillAdapter = new SpinnerFillAdapter(this, listFills);
            mSpinnerFill.setAdapter(mFillAdapter);
            mSpinnerFill.setOnItemSelectedListener(this);
        }


        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_category);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCategory.setAdapter(categoryAdapter);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_type);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCranetype.setAdapter(typeAdapter);

        ArrayAdapter<String> cranetypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_crane_types);
        cranetypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCranetype.setAdapter(cranetypeAdapter);

        ArrayAdapter<String> bulktypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_bulk_types);
        bulktypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerBulkType.setAdapter(bulktypeAdapter);

        ArrayAdapter<String> ridetypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_ride_types);
        ridetypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerRidetype.setAdapter(ridetypeAdapter);

        ArrayAdapter<String> videotypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_video_types);
        videotypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerVideotype.setAdapter(videotypeAdapter);

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_areas);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerArea.setAdapter(areasAdapter);

        ArrayAdapter<String> clawsizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_claw_size);
        clawsizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerClawSize.setAdapter(clawsizeAdapter);
    }



    private void initViews() {
        this.mTxtAssetNumber = (EditText) findViewById(R.id.txt_assetnumber);
        this.mSpinnerLocation = (Spinner) findViewById(R.id.spinner_location);
        this.mSpinnerArea = (Spinner) findViewById(R.id.spinner_area);
        this.mSpinnerCategory = (Spinner) findViewById(R.id.spinner_category);

//Crane
        this.mSpinnerCranetype = (Spinner) findViewById(R.id.spinner_cranetype);
        this.mSpinnerFill = (Spinner) findViewById(R.id.spinner_claw_size);
        this.mSpinnerClawSize = (Spinner) findViewById(R.id.spinner_claw_size);
        this.mTxtHeader = (EditText) findViewById(R.id.txt_header);
        this.mTxtClings = (EditText) findViewById(R.id.txt_clings);

//Bulk
        this.mSpinnerBulkType = (Spinner) findViewById(R.id.spinner_bulktype);
        this.mTxtBulkLayout = (EditText) findViewById(R.id.txt_bulk_layout);

//Ride Video
        this.mTxtRideVideoName = (EditText) findViewById(R.id.txt_ride_video_name);
        this.mTxtRideVideoRowPrice = (EditText) findViewById(R.id.txt_ride_video_rowprice);

        this.mTxtNotes = (EditText) findViewById(R.id.txt_notes);
        this.mBtnAdd = (Button) findViewById(R.id.btn_add);
        this.mBtnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Editable assetnumber = this.mTxtAssetNumber.getText();
                Editable notes = this.mTxtNotes.getText();
                mSelectedLocation = (Location) mSpinnerLocation.getSelectedItem();
                //mSelectedArea = (Area) mSpinnerArea.getSelectedItem();

                if (
                        !TextUtils.isEmpty(assetnumber)
                                //&& mSpinnerCategory.getSelectedItemPosition() != 0
                                //&& mSpinnerMachinetype.getSelectedItemPosition() != 0
                                //&& mSelectedLocation != null
                                //&& mSelectedArea != null
                                 ) {


                    Asset createdAsset = mAssetDao.createAsset(
                            assetnumber.toString(),
                            mSpinnerCategory.getSelectedItemPosition(),
                            mSpinnerCranetype.getSelectedItemPosition()
                    );

                    AssetLocation createdAssetLocation = mAssetLocationDao.createAssetLocation(
                            mAssetDao.getIDofAsset(assetnumber.toString()),
                            mSelectedLocation.getLocationId(),
                            mSpinnerArea.getSelectedItemId(),
                            UsefulClasses.getCurrentTimeStamp(),
                            notes.toString(),
                            notes.toString()
                            );

                    Log.d(TAG, "Asset Added : " +
                            "Asset=" + assetnumber.toString() + " " +
                            "Category=" + mSpinnerCategory.getSelectedItemPosition() + " " +
                            "Type=" + mSpinnerCranetype.getSelectedItemPosition() + " " +
                            "Location=" + mSelectedLocation.getLocationId() + " " +
                            "Area=" + mSpinnerArea.getSelectedItemPosition());

                    Log.d(TAG, "AssetLocation Added : " +
                            "AssetID=" + mAssetDao.getIDofAsset(assetnumber.toString()) + " " +
                            "LocationID=" + mSpinnerLocation.getSelectedItemPosition() + " " +
                            "AreaID=" + mSpinnerArea.getSelectedItemId() +" " +
                            "Timestamp=" + UsefulClasses.getCurrentTimeStamp() +" " +
                            "Notes=" + mTxtNotes.toString());


                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(this, R.string.empty_fields_message, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Failed adding asset : " +
                            "Asset=" + assetnumber.toString() + " " +
                            "Category=" + mSpinnerCategory.getSelectedItemPosition() + " " +
                            "Type=" + mSpinnerCranetype.getSelectedItemPosition() + " " +
                            "Location=" + mSpinnerLocation.getSelectedItemPosition() + " " +
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
//        mSelectedFill = mFillAdapter.getItem(position);

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
//Crane
                    mSpinnerFill.setVisibility(View.VISIBLE);
                    mSpinnerClawSize.setVisibility(View.VISIBLE);
                    mTxtHeader.setVisibility(View.VISIBLE);
                    mTxtClings.setVisibility(View.VISIBLE);

//Bulk
                    mTxtBulkLayout.setVisibility(View.GONE);

//Ride/Video
                    mTxtRideVideoName.setVisibility(View.GONE);
                    mTxtRideVideoRowPrice.setVisibility(View.GONE);


                } else if (mSpinnerCategory.getSelectedItemId() == 2) {
//Crane
                    mSpinnerFill.setVisibility(View.GONE);
                    mSpinnerClawSize.setVisibility(View.GONE);
                    mTxtHeader.setVisibility(View.GONE);
                    mTxtClings.setVisibility(View.GONE);

//Bulk
                    mTxtBulkLayout.setVisibility(View.VISIBLE);

//Ride/Video
                    mTxtRideVideoName.setVisibility(View.GONE);
                    mTxtRideVideoRowPrice.setVisibility(View.GONE);



                } else if (mSpinnerCategory.getSelectedItemId() == 3) {
//Crane
                    mSpinnerFill.setVisibility(View.GONE);
                    mSpinnerClawSize.setVisibility(View.GONE);
                    mTxtHeader.setVisibility(View.GONE);
                    mTxtClings.setVisibility(View.GONE);

//Bulk
                    mTxtBulkLayout.setVisibility(View.GONE);

//Ride/Video
                    mTxtRideVideoName.setVisibility(View.VISIBLE);
                    mTxtRideVideoRowPrice.setVisibility(View.VISIBLE);

                } else if (mSpinnerCategory.getSelectedItemId() == 4){
//Crane
                    mSpinnerFill.setVisibility(View.GONE);
                    mSpinnerClawSize.setVisibility(View.GONE);
                    mTxtHeader.setVisibility(View.GONE);
                    mTxtClings.setVisibility(View.GONE);

//Bulk
                    mTxtBulkLayout.setVisibility(View.GONE);

//Ride/Video
                    mTxtRideVideoName.setVisibility(View.VISIBLE);
                    mTxtRideVideoRowPrice.setVisibility(View.VISIBLE);


                } else if (mSpinnerCategory.getSelectedItemId() == 5){

//TODO Array Other Types
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
