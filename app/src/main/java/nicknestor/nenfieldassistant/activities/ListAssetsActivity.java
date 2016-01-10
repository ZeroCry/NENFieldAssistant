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
import nicknestor.nenfieldassistant.model.Asset;

public class ListAssetsActivity extends Activity implements OnItemLongClickListener, OnItemClickListener, OnClickListener {

    public static final String TAG = "ListAssetsActivity";

    public static final int REQUEST_CODE_ADD_ASSET = 40;
    public static final String EXTRA_ADDED_ASSET = "extra_key_added_asset";
    public static final String EXTRA_SELECTED_LOCATION_ID = "extra_key_selected_location_id";

    private ListView mListviewAssets;
    private TextView mTxtEmptyListAssets;
    private ImageButton mBtnAddAsset;

    private ListAssetsAdapter mAdapter;
    private List<Asset> mListAssets;
    private AssetDAO mAssetDao;

    private long mLocationId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_assets);

        // initialize views
        initViews();

        // get the location id from extras
        mAssetDao = new AssetDAO(this);
        Intent intent  = getIntent();
        if(intent != null) {
            this.mLocationId = intent.getLongExtra(EXTRA_SELECTED_LOCATION_ID, -1);
        }

        if(mLocationId != -1) {
            mListAssets = mAssetDao.getAssetsOfLocation(mLocationId);
            // fill the listView
            if(mListAssets != null && !mListAssets.isEmpty()) {
                mAdapter = new ListAssetsAdapter(this, mListAssets);
                mListviewAssets.setAdapter(mAdapter);
            }
            else {
                mTxtEmptyListAssets.setVisibility(View.VISIBLE);
                mListviewAssets.setVisibility(View.GONE);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_ADD_ASSET) {
            if(resultCode == RESULT_OK) {
                //refresh the listView
                if(mListAssets == null || !mListAssets.isEmpty()) {
                    mListAssets = new ArrayList<Asset>();
                }

                if(mAssetDao == null)
                    mAssetDao = new AssetDAO(this);
                mListAssets = mAssetDao.getAssetsOfLocation(mLocationId);
                if(mAdapter == null) {
                    mAdapter = new ListAssetsAdapter(this, mListAssets);
                    mListviewAssets.setAdapter(mAdapter);
                    if(mListviewAssets.getVisibility() != View.VISIBLE) {
                        mTxtEmptyListAssets.setVisibility(View.GONE);
                        mListviewAssets.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    mAdapter.setItems(mListAssets);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAssetDao.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Asset clickedAsset = mAdapter.getItem(position);
        Log.d(TAG, "clickedItem : "+clickedAsset.getAssetNumber()+" "+clickedAsset.getCategory());
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
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