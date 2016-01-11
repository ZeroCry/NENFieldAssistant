package nicknestor.nenfieldassistant.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.adapter.ListLocationsAdapter;
import nicknestor.nenfieldassistant.dao.LocationDAO;
import nicknestor.nenfieldassistant.model.Location;



public class ListLocationsActivity extends Activity implements OnItemLongClickListener, OnItemClickListener, OnClickListener {

    private static final String TAG = "ListLocationsActivity";

    private static final int REQUEST_CODE_ADD_LOCATION = 40;
    public static final String EXTRA_ADDED_LOCATION = "extra_key_added_Location";

    private ListView mListviewLocations;
    private TextView mTxtEmptyListLocations;
    private Button mBtnAddLocation;

    private ListLocationsAdapter mAdapter;
    private List<Location> mListLocations;
    private LocationDAO mLocationDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        // initialize views
        initViews();

        // fill the listView
        this.mLocationDao = new LocationDAO(this);
        this.mListLocations = mLocationDao.getAllLocations();
        if(mListLocations != null && !mListLocations.isEmpty()) {
            this.mAdapter = new ListLocationsAdapter(this, mListLocations);
            this.mListviewLocations.setAdapter(mAdapter);
        }
        else {
            this.mTxtEmptyListLocations.setVisibility(View.VISIBLE);
            this.mListviewLocations.setVisibility(View.GONE);
        }
    }

    private void initViews() {
        this.mListviewLocations = (ListView) findViewById(R.id.list_locations);
        this.mTxtEmptyListLocations = (TextView) findViewById(R.id.txt_empty_list_Locations);
        this.mBtnAddLocation = (Button) findViewById(R.id.btn_add_location);
        this.mListviewLocations.setOnItemClickListener(this);
        this.mListviewLocations.setOnItemLongClickListener(this);
        this.mBtnAddLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_location:
                Intent intent = new Intent(this, AddLocationActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_LOCATION);
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_ADD_LOCATION) {
            if(resultCode == RESULT_OK) {
                // add the added Location to the listLocations and refresh the listView
                if(data != null) {
                    Location createdLocation = (Location) data.getSerializableExtra(EXTRA_ADDED_LOCATION);
                    if(createdLocation != null) {
                        if(this.mListLocations == null)
                            this.mListLocations = new ArrayList<Location>();
                        this.mListLocations.add(createdLocation);

                        if(mAdapter == null) {
                            if(mListviewLocations.getVisibility() != View.VISIBLE) {
                                this.mListviewLocations.setVisibility(View.VISIBLE);
                                this.mTxtEmptyListLocations.setVisibility(View.GONE);
                            }

                            this.mAdapter = new ListLocationsAdapter(this, mListLocations);
                            this.mListviewLocations.setAdapter(mAdapter);
                        }
                        else {
                            this.mAdapter.setItems(mListLocations);
                            this.mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mLocationDao.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Location clickedLocation = mAdapter.getItem(position);
        Log.d(TAG, "clickedItem : " + clickedLocation.getStore());
        Intent intent = new Intent(this, ListAssetsActivity.class);
        intent.putExtra(ListAssetsActivity.EXTRA_SELECTED_LOCATION_ID, clickedLocation.getId());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Location clickedLocation = mAdapter.getItem(position);
        Log.d(TAG, "longClickedItem : "+clickedLocation.getStore());
        showDeleteDialogConfirmation(clickedLocation);
        return true;
    }

    private void showDeleteDialogConfirmation(final Location clickedLocation) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setMessage("Are you sure you want to delete the \""+clickedLocation.getStore()+"\" location ?");

        // set positive button YES message
        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete the Location and refresh the list
                if (mLocationDao != null) {
                    mLocationDao.deleteLocation(clickedLocation);
                    mListLocations.remove(clickedLocation);

                    //refresh the listView
                    if (mListLocations.isEmpty()) {
                        mListviewLocations.setVisibility(View.GONE);
                        mTxtEmptyListLocations.setVisibility(View.VISIBLE);
                    }
                    mAdapter.setItems(mListLocations);
                    mAdapter.notifyDataSetChanged();
                }

                dialog.dismiss();
                Toast.makeText(ListLocationsActivity.this, R.string.Location_deleted_successfully, Toast.LENGTH_SHORT).show();
            }
        });

        // set neutral button OK
        alertDialogBuilder.setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }

}
