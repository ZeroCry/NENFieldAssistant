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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.adapter.SpinnerLocationsAdapter;
import nicknestor.nenfieldassistant.dao.AreasDAO;
import nicknestor.nenfieldassistant.dao.LocationDAO;
import nicknestor.nenfieldassistant.dao.AssetDAO;
import nicknestor.nenfieldassistant.model.Location;
import nicknestor.nenfieldassistant.model.Asset;

public class AddAssetActivity extends Activity implements OnClickListener, OnItemSelectedListener{

    public static final String TAG = "AddAssetActivity";

    private EditText mTxtAssetNumber;
    private Button mBtnAdd;
    private Spinner mSpinnerLocation;
    private Spinner mSpinnerArea;
    private Spinner mSpinnerCategory;
    private Spinner mSpinnerMachinetype;


    private LocationDAO mLocationDao;
    private AssetDAO mAssetDao;
    private AreasDAO mAreasDao;


    private Location mSelectedLocation;
    private SpinnerLocationsAdapter mLocationsAdapter;


    private String[] array_areas;
    private String[] array_category;
    private String[] array_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);
        array_areas = getResources().getStringArray(R.array.areas_array);
        array_category = getResources().getStringArray(R.array.array_category);
        array_type = getResources().getStringArray(R.array.array_crane_types);


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



        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_areas);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerArea.setAdapter(areasAdapter);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_category);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCategory.setAdapter(categoryAdapter);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_type);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMachinetype.setAdapter(typeAdapter);

    }

    private void initViews() {
        this.mTxtAssetNumber = (EditText) findViewById(R.id.txt_assetnumber);
        this.mSpinnerCategory = (Spinner) findViewById(R.id.spinner_category);
        this.mSpinnerMachinetype = (Spinner) findViewById(R.id.spinner_machinetype);
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
                mSelectedLocation = (Location) mSpinnerLocation.getSelectedItem();
                if (
                        !TextUtils.isEmpty(assetnumber)
                        && mSpinnerCategory.getSelectedItemPosition() != 0
                        && mSpinnerMachinetype.getSelectedItemPosition() != 0
                        && mSelectedLocation != null
                        && mSpinnerArea.getSelectedItemPosition()!= 0) {

                    // add the asset to database
                    Asset createdAsset = mAssetDao.createAsset(
                            assetnumber.toString(),
                            mSpinnerCategory.getSelectedItemPosition(),
                            mSpinnerMachinetype.getSelectedItemPosition(),
                            //mSelectedLocation.getId(),
                            mSpinnerArea.getSelectedItemPosition());
                    Log.d(TAG, "added asset : "+ assetnumber.toString());
                    setResult(RESULT_OK);
                    finish();
                }
                else {
                    Toast.makeText(this, R.string.empty_fields_message, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Failed adding asset : " +
                            "Asset=" + assetnumber.toString() + " " +
                            "Category=" + mSpinnerCategory.getSelectedItemPosition() + " " +
                            "Type=" + mSpinnerMachinetype.getSelectedItemPosition() + " " +
                            "Location=" + mSelectedLocation.getId() + " " +
                            "Area=" + mSpinnerArea.getSelectedItemPosition());

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
