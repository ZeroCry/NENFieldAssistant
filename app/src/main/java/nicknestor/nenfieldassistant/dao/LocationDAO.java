package nicknestor.nenfieldassistant.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import nicknestor.nenfieldassistant.model.Location;
import nicknestor.nenfieldassistant.model.Asset;


public class LocationDAO {
    public static String TAG = "LocationDAO";

    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDatabaseHandler;
    private Context mContext;
    private String[] mAllColumns = {
            DatabaseHandler.CLASS_LOCATIONS.Locations_id,
            DatabaseHandler.CLASS_LOCATIONS.Locations_store,
            DatabaseHandler.CLASS_LOCATIONS.Locations_abbr,
            DatabaseHandler.CLASS_LOCATIONS.Locations_storeNumber,
            DatabaseHandler.CLASS_LOCATIONS.Locations_address,
            DatabaseHandler.CLASS_LOCATIONS.Locations_city,
            DatabaseHandler.CLASS_LOCATIONS.Locations_state,
            DatabaseHandler.CLASS_LOCATIONS.Locations_zip,
            DatabaseHandler.CLASS_LOCATIONS.Locations_phone};

    public LocationDAO(Context context) {
        this.mContext = context;
        this.mDatabaseHandler = new DatabaseHandler(context);
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

    public Location createLocation(String store, String abbr, String storeID, String address, String city, String state, String zip, String phone) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.CLASS_LOCATIONS.Locations_store, store);
        values.put(DatabaseHandler.CLASS_LOCATIONS.Locations_abbr, abbr);
        values.put(DatabaseHandler.CLASS_LOCATIONS.Locations_storeNumber, storeID);
        values.put(DatabaseHandler.CLASS_LOCATIONS.Locations_address, address);
        values.put(DatabaseHandler.CLASS_LOCATIONS.Locations_city, city);
        values.put(DatabaseHandler.CLASS_LOCATIONS.Locations_state, state);
        values.put(DatabaseHandler.CLASS_LOCATIONS.Locations_zip, zip);
        values.put(DatabaseHandler.CLASS_LOCATIONS.Locations_phone, phone);
        long insertId = mDatabase
                .insert(DatabaseHandler.CLASS_LOCATIONS.Table_Locations, null, values);
        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_LOCATIONS.Table_Locations, mAllColumns,
                DatabaseHandler.CLASS_LOCATIONS.Locations_id + " = " + insertId, null, null, null, null, null);
        cursor.moveToFirst();
        Location newLocation = cursorToLocation(cursor);
        cursor.close();
        return newLocation;
    }

 /*   public void deleteLocation(Location location) {
        Integer location_id = location.getId();
        // delete all assets of this location
        AssetDAO assetDao = new AssetDAO(mContext);
        List<Asset> listAssets = assetDao.getAssetsOfLocation(location_id);
        if (listAssets != null && !listAssets.isEmpty()) {
            for (Asset e : listAssets) {
                assetDao.deleteAsset(e);
            }
        }

        System.out.println("the deleted location has the id: " + location_id);
        mDatabase.delete(DatabaseHandler.CLASS_LOCATIONS.Table_Locations, DatabaseHandler.CLASS_LOCATIONS.Locations_id
                + " = " + location_id, null);
    }
*/
//TODO Set up editLocation function

/*    public void editLocation(Location Location) {
        Long location_id = Location.getId();
        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_LOCATIONS.Table_Locations, mAllColumns,
        DatabaseHandler.CLASS_LOCATIONS.Locations_id + " = " + insertId, null, null,
            null, null);
        cursor.moveToFirst();
        Location newLocation = cursorToLocation(cursor);
        cursor.close();

        }
*/



    public List<Location> getAllLocations() {
        List<Location> listLocations = new ArrayList<Location>();
        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_LOCATIONS.Table_Locations, mAllColumns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Location location = cursorToLocation(cursor);
                listLocations.add(location);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listLocations;
    }

    public Location getLocationById(Integer location_id) {
        Cursor cursor = mDatabase.query(DatabaseHandler.CLASS_LOCATIONS.Table_Locations, mAllColumns,
                DatabaseHandler.CLASS_LOCATIONS.Locations_id + " = ?",
                new String[] { String.valueOf(location_id) }, null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Location location = cursorToLocation(cursor);
        return location;
    }

    protected Location cursorToLocation(Cursor cursor) {
        Location location = new Location(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8));
        return location;
    }

}