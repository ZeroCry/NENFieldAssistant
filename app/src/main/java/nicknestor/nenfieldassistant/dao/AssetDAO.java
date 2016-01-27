package nicknestor.nenfieldassistant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import nicknestor.nenfieldassistant.dao.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;
import android.database.sqlite.SQLiteQueryBuilder;

import nicknestor.nenfieldassistant.model.Asset;
import nicknestor.nenfieldassistant.model.AssetLocation;
import nicknestor.nenfieldassistant.model.Location;


public class AssetDAO {
    public static final String ASSET_ID_WITH_PREFIX = "asset.id";
    public static final String TAG = "AssetDAO";
    public String locationassets;
    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDatabaseHandler;
    private String[] mAllColumns = {
            DatabaseHandler.CLASS_ASSETS.Assets_id,
            DatabaseHandler.CLASS_ASSETS.Assets_assetnumber,
            DatabaseHandler.CLASS_ASSETS.Assets_category,
            DatabaseHandler.CLASS_ASSETS.Assets_machinetype,
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
        values.put(DatabaseHandler.CLASS_ASSETS.Assets_assetnumber, assetnumber);
        values.put(DatabaseHandler.CLASS_ASSETS.Assets_category, category);
        values.put(DatabaseHandler.CLASS_ASSETS.Assets_machinetype, machinetype);
        Long insertAssetId = mDatabase
                .insert(DatabaseHandler.CLASS_ASSETS.Table_Assets, null, values);
        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_ASSETS.Table_Assets, mAllColumns,
                DatabaseHandler.CLASS_ASSETS.Assets_id + " = " + insertAssetId, null,null,null,null);
        cursor.moveToFirst();
        Asset newAsset = cursorToAsset(cursor);
        cursor.close();
       return newAsset;
    }

    public void deleteAsset(Asset asset) {
        Long asset_id = asset.getAssetId();
        System.out.println("the deleted asset has the id: " + asset_id);
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


    public List<Asset> getAssetsOfLocation(long locationId) {
        List<Asset> listassets = new ArrayList<Asset>();

/*        String tables = DatabaseHandler.CLASS_ASSETS.Table_Assets
                        + " INNER JOIN "
                        + DatabaseHandler.CLASS_ASSETLOCATION.Table_AssetLocation
                        + " ON "
                        + DatabaseHandler.CLASS_ASSETS.Assets_id
                        + " = "
                        + DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_asset;
*/

        Cursor cursor = mDatabase.rawQuery("SELECT DatabaseHandler.CLASS_ASSETS.Table_Assets INNER JOIN DatabaseHandler.CLASS_ASSETLOCATION.Table_AssetLocation ON DatabaseHandler.CLASS_ASSETS.Assets_id = DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_asset WHERE DatabaseHandler.CLASS_ASSETLOCATION = ?",
            "locationId"


//                DatabaseHandler.CLASS_ASSETS.Table_Assets,mAllColumns,null,null,null,null,null
//                tables,mAllColumns,null,null,null,null,null
/*                tables,
                new String[]{
                        DatabaseHandler.CLASS_ASSETS.Assets_id,
                        DatabaseHandler.CLASS_ASSETS.Assets_assetnumber,
                        DatabaseHandler.CLASS_ASSETS.Assets_category,
                        DatabaseHandler.CLASS_ASSETS.Assets_machinetype,
                        DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_location},
                DatabaseHandler.CLASS_ASSETS.Assets_id + " = ?",
                new String[] {String.valueOf(locationId)},null,null,null,null,null,null*/
                );


        Log.d(TAG, "mDatabase : " + mDatabase);



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

    private Asset cursorToAsset(Cursor cursor) {
        Asset asset = new Asset();
        asset.setAssetId(cursor.getLong(0));
        asset.setAssetNumber(cursor.getString(1));
        asset.setCategory(cursor.getString(2));
        asset.setMachineType(cursor.getString(3));
        return asset;
    }

    public int getIDofAsset(String assetnumber) {
            //Long assetid = null;
            Cursor cursor = mDatabase.rawQuery("SELECT assets_id FROM assets WHERE assetnumber = " + assetnumber, null);
                cursor.moveToFirst();
                    int assetid = cursor.getColumnIndex("assets_id");
                cursor.close();
            return assetid;

    }
}