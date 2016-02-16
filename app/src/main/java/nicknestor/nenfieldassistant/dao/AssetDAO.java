package nicknestor.nenfieldassistant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nicknestor.nenfieldassistant.model.Asset;


public class AssetDAO {
    public static final String ASSET_ID_WITH_PREFIX = "asset.id";
    public static final String TAG = "AssetDAO";
    public String locationassets;
    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDatabaseHandler;
    private String[] mAllColumns = {
            DatabaseHandler.ASSETS.Assets_id,
            DatabaseHandler.ASSETS.Assets_assetnumber,
            DatabaseHandler.ASSETS.Assets_category,
            DatabaseHandler.ASSETS.Assets_machinetype,
    };

    public AssetDAO(Context context) {
        mDatabaseHandler = new DatabaseHandler(context);
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
        mDatabase = mDatabaseHandler.getWritableDatabase();
    }

    public void close() {
        mDatabaseHandler.close();
    }

    public Asset createAsset(String assetnumber, Integer category, Integer machinetype) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.ASSETS.Assets_assetnumber, assetnumber);
        values.put(DatabaseHandler.ASSETS.Assets_category, category);
        values.put(DatabaseHandler.ASSETS.Assets_machinetype, machinetype);
        Long insertAssetId = mDatabase
                .insert(DatabaseHandler.ASSETS.Table_Assets, null, values);
        Cursor cursor = mDatabase.query(DatabaseHandler.ASSETS.Table_Assets, mAllColumns,
                DatabaseHandler.ASSETS.Assets_id + " = " + insertAssetId, null,null,null,null);
        cursor.moveToFirst();
        Asset newAsset = cursorToAsset(cursor);
        cursor.close();
       return newAsset;
    }

    public void deleteAsset(Asset asset) {
        Long asset_id = asset.getAssetId();
        System.out.println("the deleted asset has the id: " + asset_id);
        mDatabase.delete(DatabaseHandler.ASSETS.Table_Assets, DatabaseHandler.ASSETS.Assets_id
                + " = " + asset_id, null);
    }

    public List<Asset> getAllAssets() {
        List<Asset> listAssets = new ArrayList<Asset>();

        Cursor cursor = mDatabase.query(DatabaseHandler.ASSETS.Table_Assets, mAllColumns,
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
        List<Asset> listassets = new ArrayList<Asset>();

        String querydata = "SELECT * FROM " + DatabaseHandler.ASSETS.Table_Assets +
                " INNER JOIN " +
                DatabaseHandler.ASSETLOCATION.Table_AssetLocation +
                " ON " +
                DatabaseHandler.ASSETS.Assets_id +
                " = " +
                DatabaseHandler.ASSETLOCATION.AssetLocation_id_asset +
                " WHERE " +
                DatabaseHandler.ASSETLOCATION.AssetLocation_id_location +
                " = ?";

        Cursor cursor = mDatabase.rawQuery(querydata, new String[] {Long.toString(locationId)} );


        Log.d(TAG, "Querydata : " + querydata);



        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Asset asset = cursorToAsset(cursor);
            listassets.add(asset);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d(TAG, "listassets : " + listassets);
        return listassets;

    }

    protected Asset cursorToAsset(Cursor cursor) {
        Asset asset = new Asset();
        asset.setAssetId(cursor.getLong(0));
        asset.setAssetNumber(cursor.getString(1));
        asset.setCategory(cursor.getString(2));
        asset.setMachineType(cursor.getString(3));
        return asset;
    }

    public Integer getIDofAsset(String assetnumber) {
            Cursor cursor = mDatabase.rawQuery(
                    "SELECT " +
                            DatabaseHandler.ASSETS.Assets_id +
                            " FROM " +
                            DatabaseHandler.ASSETS.Table_Assets +
                            " WHERE " +
                            DatabaseHandler.ASSETS.Assets_assetnumber +
                            " = ? "
                    , new String[]{assetnumber});
                cursor.moveToFirst();
                    Integer assetid = cursor.getInt(0);
                cursor.close();
            return assetid;

    }


}