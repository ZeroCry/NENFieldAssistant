package nicknestor.nenfieldassistant.dao;

import android.content.ContentValues;
import android.content.Context;
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
            DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_asset,
            DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_location,
            DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_area,
            DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_timestamp,
            DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_notes,
            DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_user,
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

    public AssetLocation createAssetLocation(Integer asset_id, Integer location_id, Long areas_id, String timestamp, String notes, String user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_asset, asset_id);
        values.put(DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_location, location_id);
        values.put(DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_id_area, areas_id);
        values.put(DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_timestamp, timestamp);
        values.put(DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_notes, notes);
        values.put(DatabaseHandler.CLASS_ASSETLOCATION.AssetLocation_user, user);


        return null;
    }

}
