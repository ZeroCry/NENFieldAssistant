package nicknestor.nenfieldassistant.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nicknestor.nenfieldassistant.model.Area;

public class AreasDAO {

    public static final String TAG = "AreaDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDbHelper;
    private String[] mAllColumns = {
            DatabaseHandler.AREAS.Areas_id,
            DatabaseHandler.AREAS.Areas_area
    };

    public AreasDAO(Context context) {
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

    public Area createArea(String area) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.AREAS.Areas_area, area);
        long insertId = mDatabase
                .insert(DatabaseHandler.AREAS.Table_Areas, null, values);
        Cursor cursor = mDatabase.query(DatabaseHandler.AREAS.Table_Areas, mAllColumns,
                DatabaseHandler.AREAS.Areas_area + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Area newArea = cursorToArea(cursor);
        cursor.close();
        return newArea;
    }

    public void deleteArea(Area area) {
        long id = area.getAreaId();
        System.out.println("the deleted employee has the id: " + id);
        mDatabase.delete(DatabaseHandler.AREAS.Table_Areas, DatabaseHandler.AREAS.Areas_id
                + " = " + id, null);
    }

    public List<Area> getAllAreas() {
        List<Area> listAreas = new ArrayList<Area>();

        Cursor cursor = mDatabase.query(DatabaseHandler.AREAS.Table_Areas, mAllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Area area = cursorToArea(cursor);
            listAreas.add(area);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listAreas;
    }

    public List<Area> getAreasOfLocation(long location_id) {
        List<Area> listAreas = new ArrayList<Area>();

        Cursor cursor = mDatabase.query(DatabaseHandler.AREAS.Table_Areas, mAllColumns,
                DatabaseHandler.AREAS.Areas_id + " = ?",
                new String[] { String.valueOf(location_id) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Area area = cursorToArea(cursor);
            listAreas.add(area);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listAreas;
    }

    private Area cursorToArea(Cursor cursor) {
        Area area = new Area(
                cursor.getLong(0),
                cursor.getInt(1));

        return area;
    }

}
