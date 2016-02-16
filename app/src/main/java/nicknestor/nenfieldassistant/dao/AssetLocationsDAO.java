package nicknestor.nenfieldassistant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import nicknestor.nenfieldassistant.model.AssetLocation;

public class AssetLocationsDAO {

    public static final String TAG = "AssetLocationsDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDatabaseHandler;
    private String[] mAllColumns = {
            DatabaseHandler.ASSETLOCATION.AssetLocation_id,
            DatabaseHandler.ASSETLOCATION.AssetLocation_id_asset,
            DatabaseHandler.ASSETLOCATION.AssetLocation_id_location,
            DatabaseHandler.ASSETLOCATION.AssetLocation_id_area,
            DatabaseHandler.ASSETLOCATION.AssetLocation_timestamp,
            DatabaseHandler.ASSETLOCATION.AssetLocation_notes,
            DatabaseHandler.ASSETLOCATION.AssetLocation_user,
    };

    public AssetLocationsDAO(Context context) {
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

    public AssetLocation createAssetLocation(Integer asset_id, Long location_id, Long areas_id, String timestamp, String notes, String user) {
        ContentValues valuesAssetLocation = new ContentValues();
        valuesAssetLocation.put(DatabaseHandler.ASSETLOCATION.AssetLocation_id_asset, asset_id);
        valuesAssetLocation.put(DatabaseHandler.ASSETLOCATION.AssetLocation_id_location, location_id);
        valuesAssetLocation.put(DatabaseHandler.ASSETLOCATION.AssetLocation_id_area, areas_id);
        valuesAssetLocation.put(DatabaseHandler.ASSETLOCATION.AssetLocation_timestamp, timestamp);
        valuesAssetLocation.put(DatabaseHandler.ASSETLOCATION.AssetLocation_notes, notes);
        valuesAssetLocation.put(DatabaseHandler.ASSETLOCATION.AssetLocation_user, user);
        Long insertAssetLocationId = mDatabase
                .insert(DatabaseHandler.ASSETLOCATION.Table_AssetLocation, null, valuesAssetLocation);
        Cursor cursor = mDatabase.query(DatabaseHandler.ASSETLOCATION.Table_AssetLocation, mAllColumns,
                DatabaseHandler.ASSETLOCATION.AssetLocation_id + " = " + insertAssetLocationId, null,null,null,null);
        cursor.moveToFirst();
        AssetLocation newAssetLocation = cursorToAssetLocation(cursor);
        cursor.close();
        return newAssetLocation;

    }

    private AssetLocation cursorToAssetLocation(Cursor cursor) {
        AssetLocation assetlocation = new AssetLocation();
        assetlocation.setAssetLocationId(cursor.getLong(0));
        assetlocation.setAsset_id(cursor.getLong(1));
        assetlocation.setLocation_id(cursor.getLong(2));
        assetlocation.setAreas_id(cursor.getLong(3));
        assetlocation.setTimestamp(cursor.getString(4));
        assetlocation.setNotes(cursor.getString(5));
        assetlocation.setUser(cursor.getString(6));
        return assetlocation;
    }

}
