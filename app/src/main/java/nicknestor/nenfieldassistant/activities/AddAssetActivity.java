package nicknestor.nenfieldassistant.activities;


import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.adapter.SpinnerAreasAdapter;
import nicknestor.nenfieldassistant.adapter.SpinnerLocationsAdapter;
import nicknestor.nenfieldassistant.dao.AreasDAO;
import nicknestor.nenfieldassistant.dao.LocationDAO;
import nicknestor.nenfieldassistant.dao.AssetDAO;
import nicknestor.nenfieldassistant.model.Area;
import nicknestor.nenfieldassistant.model.Location;
import nicknestor.nenfieldassistant.model.Asset;

public class AddAssetActivity extends Activity implements OnClickListener, OnItemSelectedListener{

    public static final String TAG = "AddAssetActivity";

    private EditText mTxtAssetNumber;
    private EditText mTxtCategory;
    private EditText mTxtMachineType;
    private Button mBtnAdd;
    private Spinner mSpinnerLocation;
    private Spinner mSpinnerArea;

    private LocationDAO mLocationDao;
    private AssetDAO mAssetDao;
    private AreasDAO mAreasDao;


    private Location mSelectedLocation;
    private Area mSelectedArea;
    private SpinnerLocationsAdapter mLocationsAdapter;
    private SpinnerAreasAdapter mAreasAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);

        initViews();

        this.mLocationDao = new LocationDAO(this);
        this.mAssetDao = new AssetDAO(this);
        this.mAreasDao = new AreasDAO(this);

        List<Location> listLocations = mLocationDao.getAllLocations();
        if(listLocations != null) {
            mLocationsAdapter = new SpinnerLocationsAdapter(this, listLocations);
            mSpinnerLocation.setAdapter(mLocationsAdapter);
            mSpinnerLocation.setOnItemSelectedListener(this);
        }
        List<Area> listAreas = mAreasDao.getAllAreas();
        if(listAreas != null) {
            mAreasAdapter = new SpinnerAreasAdapter(this, listAreas);
            mSpinnerArea.setAdapter(mAreasAdapter);
            mSpinnerArea.setOnItemSelectedListener(this);
        }



    }

    private void initViews() {
        this.mTxtAssetNumber = (EditText) findViewById(R.id.txt_assetnumber);
        this.mTxtCategory = (EditText) findViewById(R.id.txt_category);
        this.mTxtMachineType = (EditText) findViewById(R.id.txt_machinetype);
        this.mSpinnerLocation = (Spinner) findViewById(R.id.spinner_location);
        this.mSpinnerArea = (Spinner) findViewById(R.id.spinner_area);
        this.mBtnAdd = (Button) findViewById(R.id.btn_add);

        this.mBtnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Editable assetnumber = mTxtAssetNumber.getText();
                Editable category = mTxtCategory.getText();
                Editable machinetype = mTxtMachineType.getText();
                mSelectedLocation = (Location) mSpinnerLocation.getSelectedItem();
                if (!TextUtils.isEmpty(assetnumber) && !TextUtils.isEmpty(category)
                        && !TextUtils.isEmpty(machinetype) && mSelectedLocation != null
                        && mSelectedArea != null) {
                    // add the company to database
                    Asset createdAsset = mAssetDao.createAsset(assetnumber.toString(), category.toString(), machinetype.toString(), mSelectedLocation.getId() , mSelectedArea.getId());

                    Log.d(TAG, "added asset : "+ createdAsset.getAssetNumber());
                    setResult(RESULT_OK);
                    finish();
                }
                else {
                    Toast.makeText(this, R.string.empty_fields_message, Toast.LENGTH_LONG).show();
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
        Log.d(TAG, "selectedCompany : " + mSelectedLocation.getStore());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
