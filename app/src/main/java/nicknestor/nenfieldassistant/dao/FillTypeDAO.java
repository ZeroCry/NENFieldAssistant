package nicknestor.nenfieldassistant.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nicknestor.nenfieldassistant.model.FillType;

public class FillTypeDAO {

    public static final String TAG = "FillTypeDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDbHelper;
    private String[] mAllColumns = {
            DatabaseHandler.FILLTYPE.FillType_id,
            DatabaseHandler.FILLTYPE.FillType_Name
    };

    public FillTypeDAO(Context context) {
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

    public FillType createFillType(String filltype) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.FILLTYPE.FillType_Name, filltype);
        long insertId = mDatabase
                .insert(DatabaseHandler.FILLTYPE.Table_FillType, null, values);
        Cursor cursor = mDatabase.query(DatabaseHandler.FILLTYPE.Table_FillType, mAllColumns,
                DatabaseHandler.FILLTYPE.FillType_Name + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        FillType newFillType = cursorToFillType(cursor);
        cursor.close();
        return newFillType;
    }

    public void deleteFillType(FillType filltype) {
        long id = filltype.getFillTypeId();
        System.out.println("the deleted FillType has the id: " + id);
        mDatabase.delete(DatabaseHandler.FILLTYPE.Table_FillType, DatabaseHandler.FILLTYPE.FillType_id
                + " = " + id, null);
    }

    public List<FillType> getAllFillTypes() {
        List<FillType> listFillTypes = new ArrayList<FillType>();

        Cursor cursor = mDatabase.query(DatabaseHandler.FILLTYPE.Table_FillType, mAllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FillType filltype = cursorToFillType(cursor);
            listFillTypes.add(filltype);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listFillTypes;
    }

    public List<FillType> getFillTypeByCategory(long Category_id) {
        List<FillType> listFillTypes = new ArrayList<FillType>();

        Cursor cursor = mDatabase.query(DatabaseHandler.FILLTYPE.Table_FillType, mAllColumns,
                DatabaseHandler.FILLTYPE.FillType_id + " = ?",
                new String[] { String.valueOf(Category_id) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FillType filltype = cursorToFillType(cursor);
            listFillTypes.add(filltype);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listFillTypes;
    }

    private FillType cursorToFillType(Cursor cursor) {
        FillType filltype = new FillType();
        filltype.setFillTypeId(cursor.getLong(0));
        filltype.setFillTypeName(cursor.getString(1));
        filltype.setFillTypeTeam(cursor.getString(2));
        filltype.setFillTypeWhole(cursor.getString(3));
        filltype.setFillTypePart(cursor.getString(4));
        filltype.setFillTypeUserId(cursor.getString(5));
        filltype.setFillTypeTimestamp(cursor.getString(6));
        return filltype;
    }

}
