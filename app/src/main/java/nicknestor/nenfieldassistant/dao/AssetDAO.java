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


public class AssetDAO {
    public static final String ASSET_ID_WITH_PREFIX = "asset.id";
    public static final String TAG = "AssetDAO";

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

       return null;
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

    //TODO This is where it fails?
    public ArrayList<Asset> getAssetsOfLocation() {
        ArrayList<Asset> assets = new ArrayList<Asset>();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder
                .setTables(DatabaseHandler.CLASS_ASSETS.Table_Assets
                        + " INNER JOIN "
                        + DatabaseHandler.CLASS_ASSETLOCATION.Table_AssetLocation
                        + " ON "
                        + DatabaseHandler.CLASS_ASSETS.Assets_id
                        + " = "
                        + DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_asset);


        // Get cursor
        Cursor cursor = queryBuilder.query(mDatabase, new String[]{
                        DatabaseHandler.CLASS_ASSETS.Assets_id,
                        DatabaseHandler.CLASS_ASSETS.Assets_assetnumber,
                        DatabaseHandler.CLASS_ASSETS.Assets_category,
                        DatabaseHandler.CLASS_ASSETS.Assets_machinetype,
                        DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_location}, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Asset asset = new Asset();
            asset.setAssetId(cursor.getLong(0));
            asset.setAssetNumber(cursor.getString(1));
            asset.setCategory(cursor.getString(2));
            asset.setMachineType(cursor.getString(3));


            AssetLocation assetlocation = new AssetLocation();
            assetlocation.setLocation_id(cursor.getLong(4));

            assets.add(asset);
        }
        return assets;

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