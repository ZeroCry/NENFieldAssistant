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


public class LocationDAO {
    public static String TAG = "LocationDAO";

    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDatabaseHandler;
    private Context mContext;
    private String[] mAllColumns = {
            DatabaseHandler.LOCATIONS.Locations_id,
            DatabaseHandler.LOCATIONS.Locations_store,
            DatabaseHandler.LOCATIONS.Locations_abbr,
            DatabaseHandler.LOCATIONS.Locations_storeNumber,
            DatabaseHandler.LOCATIONS.Locations_address,
            DatabaseHandler.LOCATIONS.Locations_city,
            DatabaseHandler.LOCATIONS.Locations_state,
            DatabaseHandler.LOCATIONS.Locations_zip,
            DatabaseHandler.LOCATIONS.Locations_phone};

    public LocationDAO(Context context) {
        this.mContext = context;
        mDatabaseHandler = new DatabaseHandler(context);
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

    public Location createLocation(String store, String abbr, String storenumber, String address, String city, String state, String zip, String phone) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.LOCATIONS.Locations_store, store);
        values.put(DatabaseHandler.LOCATIONS.Locations_abbr, abbr);
        values.put(DatabaseHandler.LOCATIONS.Locations_storeNumber, storenumber);
        values.put(DatabaseHandler.LOCATIONS.Locations_address, address);
        values.put(DatabaseHandler.LOCATIONS.Locations_city, city);
        values.put(DatabaseHandler.LOCATIONS.Locations_state, state);
        values.put(DatabaseHandler.LOCATIONS.Locations_zip, zip);
        values.put(DatabaseHandler.LOCATIONS.Locations_phone, phone);
        Long insertLocationId = mDatabase
                .insert(DatabaseHandler.LOCATIONS.Table_Locations, null, values);
        Cursor cursor = mDatabase.query(DatabaseHandler.LOCATIONS.Table_Locations, mAllColumns,
                DatabaseHandler.LOCATIONS.Locations_id + " = " + insertLocationId, null, null, null, null);
        cursor.moveToFirst();
        Location newLocation = cursorToLocation(cursor);
        cursor.close();
        return newLocation;
    }

 /*   public void deleteLocation(Location location) {
        Long location_id = location.getId();
        // delete all assets of this location
        AssetDAO assetDao = new AssetDAO(mContext);
        List<Asset> listAssets = assetDao.getAssetsOfLocation(location_id);
        if (listAssets != null && !listAssets.isEmpty()) {
            for (Asset e : listAssets) {
                assetDao.deleteAsset(e);
            }
        }

        System.out.println("the deleted location has the id: " + location_id);
        mDatabase.delete(DatabaseHandler.LOCATIONS.Table_Locations, DatabaseHandler.LOCATIONS.Locations_id
                + " = " + location_id, null);
    }
*/
//TODO Set up editLocation function

/*    public void editLocation(Location Location) {
        Long location_id = Location.getId();
        Cursor cursor = mDatabase.query(DatabaseHandler.LOCATIONS.Table_Locations, mAllColumns,
        DatabaseHandler.LOCATIONS.Locations_id + " = " + insertId, null, null,
            null, null);
        cursor.moveToFirst();
        Location newLocation = cursorToLocation(cursor);
        cursor.close();

        }
*/



    public List<Location> getAllLocations() {
        List<Location> listLocations = new ArrayList<Location>();

        Cursor cursor = mDatabase.query(DatabaseHandler.LOCATIONS.Table_Locations, mAllColumns,
                null, null, null, null, null);
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

/*
    public List<Location> getAllLocations() {
        List<Location> listLocations = new ArrayList<Location>();

        Cursor cursor = ianap.SimpleQuery(DatabaseHandler.LOCATIONS.Table_Locations);
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
*/

    public Location getLocationById(long id) {
        Cursor cursor = mDatabase.query(DatabaseHandler.LOCATIONS.Table_Locations, mAllColumns,
                DatabaseHandler.LOCATIONS.Locations_id + " = ?",
                new String[] { String.valueOf(id) }, null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Location location = cursorToLocation(cursor);

        return location;
    }

    protected Location cursorToLocation(Cursor cursor) {
        Location location = new Location();
        location.setLocationId(cursor.getLong(0));
        location.setStore(cursor.getString(1));
        location.setAbbr(cursor.getString(2));
        location.setStoreNumber(cursor.getString(3));
        location.setAddress(cursor.getString(4));
        location.setCity(cursor.getString(5));
        location.setState(cursor.getString(6));
        location.setZip(cursor.getString(7));
        location.setPhone(cursor.getString(8));
        return location;
    }

}