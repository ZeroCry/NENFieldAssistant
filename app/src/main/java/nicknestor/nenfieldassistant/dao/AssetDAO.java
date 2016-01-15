package nicknestor.nenfieldassistant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nicknestor.nenfieldassistant.model.Location;
import nicknestor.nenfieldassistant.model.Asset;

public class AssetDAO {

    public static final String TAG = "AssetDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDbHelper;
    private String[] mAllColumns = {
            DatabaseHandler.CLASS_ASSETS.Assets_id,
            DatabaseHandler.CLASS_ASSETS.Assets_assetnumber,
            DatabaseHandler.CLASS_ASSETS.Assets_category,
            DatabaseHandler.CLASS_ASSETS.Assets_machinetype,
            DatabaseHandler.CLASS_ASSETS.Assets_location_id,

    };

    public AssetDAO(Context context) {
        mDbHelper = new DatabaseHandler(context);
        this.mContext = context;
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opening database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Asset createAsset(String assetnumber, Integer category,
                             Integer machinetype, Integer assetarea) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.CLASS_ASSETS.Assets_assetnumber, assetnumber);
        values.put(DatabaseHandler.CLASS_ASSETS.Assets_category, category);
        values.put(DatabaseHandler.CLASS_ASSETS.Assets_machinetype, machinetype);
        values.put(DatabaseHandler.CLASS_ASSETS.Assets_Area_id, assetarea);
        long insertId = mDatabase.insert(DatabaseHandler.CLASS_ASSETS.Table_Assets, null, values);
        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_ASSETS.Table_Assets, mAllColumns, DatabaseHandler.CLASS_ASSETS.Assets_id + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Asset newAsset = cursorToAsset(cursor);
        cursor.close();

        return newAsset;
    }

    public void deleteAsset(Asset asset) {
        long asset_id = asset.getId();
        System.out.println("the deleted employee has the id: " + asset_id);
        mDatabase.delete(DatabaseHandler.CLASS_ASSETS.Table_Assets, DatabaseHandler.CLASS_ASSETS.Assets_id
                + " = " + asset_id, null);
    }

    public List<Asset> getAllAssets() {
        List<Asset> listAssets = new ArrayList<Asset>();

        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_ASSETS.Table_Assets, mAllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Asset asset = cursorToAsset(cursor);
            listAssets.add(asset);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listAssets;
    }

    public List<Asset> getAssetsOfLocation(long location_Id) {
        List<Asset> listAssets = new ArrayList<Asset>();

        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_ASSETS.Table_Assets, mAllColumns,
                DatabaseHandler.CLASS_ASSETS.Assets_location_id + " = ?",
                new String[] { String.valueOf(location_Id) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Asset asset = cursorToAsset(cursor);
            listAssets.add(asset);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listAssets;
    }

    private Asset cursorToAsset(Cursor cursor) {
        Asset asset = new Asset();
        asset.setId(cursor.getLong(0));
        asset.setAssetNumber(cursor.getString(1));
        asset.setCategory(cursor.getString(2));
        asset.setMachineType(cursor.getString(3));
        return asset;
    }

}
