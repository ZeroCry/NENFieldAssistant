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
            DatabaseHandler.CLASS_ASSETS.Assets_assetlocation,

    };

    public AssetDAO(Context context) {
        mDbHelper = new DatabaseHandler(context);
        this.mContext = context;
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Asset createAsset(String assetnumber, String category,
                                  String machinetype, Long locationId) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.CLASS_ASSETS.Assets_assetnumber, assetnumber);
        values.put(DatabaseHandler.CLASS_ASSETS.Assets_category, category);
        values.put(DatabaseHandler.CLASS_ASSETS.Assets_machinetype, machinetype);
        long insertId = mDatabase
                .insert(DatabaseHandler.CLASS_ASSETS.Table_Assets, null, values);
        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_ASSETS.Table_Assets, mAllColumns,
                DatabaseHandler.CLASS_ASSETS.Assets_assetnumber + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Asset newAsset = cursorToAsset(cursor);
        cursor.close();
        return newAsset;
    }

    public void deleteAsset(Asset asset) {
        long id = asset.getId();
        System.out.println("the deleted employee has the id: " + id);
        mDatabase.delete(DatabaseHandler.CLASS_ASSETS.Assets_assetnumber, DatabaseHandler.CLASS_ASSETS.Assets_assetnumber
                + " = " + id, null);
    }

    public List<Asset> getAllAssets() {
        List<Asset> listAssets = new ArrayList<Asset>();

        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_ASSETS.Assets_assetnumber, mAllColumns,
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

    public List<Asset> getAssetsOfLocation(long locationId) {
        List<Asset> listAssets = new ArrayList<Asset>();

        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_ASSETS.Table_Assets, mAllColumns,
                DatabaseHandler.CLASS_ASSETS.Assets_assetnumber + " = ?",
                new String[] { String.valueOf(locationId) }, null, null, null);

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


        // get The Location by id
        long locationId = cursor.getLong(4);
        LocationDAO dao = new LocationDAO(mContext);
        Location location = dao.getLocationById(locationId);
        if (location != null)
            asset.setLocation();

        return asset;
    }

}
