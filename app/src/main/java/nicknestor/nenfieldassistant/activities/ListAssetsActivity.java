package nicknestor.nenfieldassistant.activities;

/**
 * Created by Nick on 12/19/2015.
 */

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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.adapter.ListAssetsAdapter;
import nicknestor.nenfieldassistant.dao.AssetDAO;
import nicknestor.nenfieldassistant.dao.DatabaseHandler;
import nicknestor.nenfieldassistant.model.Asset;

public class ListAssetsActivity extends Activity implements OnItemLongClickListener, OnItemClickListener, OnClickListener {

    private static final String TAG = "ListAssetsActivity";

    private static final int REQUEST_CODE_ADD_ASSET = 40;
    private static final String EXTRA_ADDED_ASSET = "extra_key_added_asset";
    public static final String EXTRA_SELECTED_LOCATION_ID = "extra_key_selected_location_id";

    private ListView mListviewAssets;
    private TextView mTxtEmptyListAssets;
    private ImageButton mBtnAddAsset;

    private ListAssetsAdapter mAdapter;
    private List<Asset> mListAssets;
    private AssetDAO mAssetDao;

    private long mLocationId = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_assets);

        // initialize views
        initViews();

        // get the location id from extras
        this.mAssetDao = new AssetDAO(this);
        Intent intent  = getIntent();
        if(intent != null) {
            this.mLocationId = intent.getLongExtra(EXTRA_SELECTED_LOCATION_ID, -1);
        }

        if(mLocationId != -1) {
            this.mListAssets = mAssetDao.getAssetsOfLocation();
            // fill the listView
            if(mListAssets != null && !mListAssets.isEmpty()) {
                this.mAdapter = new ListAssetsAdapter(this, mListAssets);
                this.mListviewAssets.setAdapter(mAdapter);
            }
            else {
                this.mTxtEmptyListAssets.setVisibility(View.VISIBLE);
                this.mListviewAssets.setVisibility(View.GONE);
            }
        }
    }

    private void initViews() {
        this.mListviewAssets = (ListView) findViewById(R.id.list_assets);
        this.mTxtEmptyListAssets = (TextView) findViewById(R.id.txt_empty_list_assets);
        this.mBtnAddAsset = (ImageButton) findViewById(R.id.btn_add_asset);
        this.mListviewAssets.setOnItemClickListener(this);
        this.mListviewAssets.setOnItemLongClickListener(this);
        this.mBtnAddAsset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_asset:
                Intent intent = new Intent(this, AddAssetActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_ASSET);
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_ADD_ASSET) {
            if(resultCode == RESULT_OK) {
                //refresh the listView
                if(mListAssets == null || !mListAssets.isEmpty()) {
                    this.mListAssets = new ArrayList<Asset>();
                }

                if(mAssetDao == null)
                    this.mAssetDao = new AssetDAO(this);
//TODO Join goes here?   Left join CLASS_ASSETS.Table_Assets to CLASS_ASSETLOCATION.Table_AssetLocation
                this.mListAssets = mAssetDao.getAssetsOfLocation();
                if(mAdapter == null) {
                    this.mAdapter = new ListAssetsAdapter(this, mListAssets);
                    this.mListviewAssets.setAdapter(mAdapter);
                    if(mListviewAssets.getVisibility() != View.VISIBLE) {
                        this.mTxtEmptyListAssets.setVisibility(View.GONE);
                        this.mListviewAssets.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    this.mAdapter.setItems(mListAssets);
                    this.mAdapter.notifyDataSetChanged();
                }
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mAssetDao.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Asset clickedAsset = mAdapter.getItem(position);
        Log.d(TAG, "clickedItem : "+clickedAsset.getAssetNumber()+" "+clickedAsset.getCategory());
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//TODO Replace this section with editAsset instead of deleteAsset
        Asset clickedAsset = mAdapter.getItem(position);
        Log.d(TAG, "longClickedItem : "+clickedAsset.getAssetNumber()+" "+clickedAsset.getCategory());

        showDeleteDialogConfirmation(clickedAsset);
        return true;
    }

    private void showDeleteDialogConfirmation(final Asset asset) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder
                .setMessage("Are you sure you want to delete the asset \""
                        + asset.getAssetNumber() + " "
                        + asset.getCategory() + "\"");

        // set positive button YES message
        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete the asset and refresh the list
                if(mAssetDao != null) {
                    mAssetDao.deleteAsset(asset);

                    //refresh the listView
                    mListAssets.remove(asset);
                    if(mListAssets.isEmpty()) {
                        mListviewAssets.setVisibility(View.GONE);
                        mTxtEmptyListAssets.setVisibility(View.VISIBLE);
                    }

                    mAdapter.setItems(mListAssets);
                    mAdapter.notifyDataSetChanged();
                }

                dialog.dismiss();
                Toast.makeText(ListAssetsActivity.this, R.string.asset_deleted_successfully, Toast.LENGTH_SHORT).show();

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
