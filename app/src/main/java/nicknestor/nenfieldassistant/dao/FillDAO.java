package nicknestor.nenfieldassistant.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nicknestor.nenfieldassistant.model.Fill;

public class FillDAO {

    public static final String TAG = "FillDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDbHelper;
    private String[] mAllColumns = {
            DatabaseHandler.CRANEFILL.CraneFill_id,
            DatabaseHandler.CRANEFILL.CraneFill_name
    };

    public FillDAO(Context context) {
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

    public Fill createFill(String fill) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.CRANEFILL.CraneFill_name, fill);
        long insertId = mDatabase
                .insert(DatabaseHandler.CRANEFILL.Table_CraneFill, null, values);
        Cursor cursor = mDatabase.query(DatabaseHandler.CRANEFILL.Table_CraneFill, mAllColumns,
                DatabaseHandler.CRANEFILL.CraneFill_name + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Fill newFill = cursorToFill(cursor);
        cursor.close();
        return newFill;
    }

    public void deleteFill(Fill fill) {
        long id = fill.getFillId();
        System.out.println("the deleted Fill has the id: " + id);
        mDatabase.delete(DatabaseHandler.CRANEFILL.Table_CraneFill, DatabaseHandler.CRANEFILL.CraneFill_id
                + " = " + id, null);
    }

    public List<Fill> getAllFills() {
        List<Fill> listFills = new ArrayList<Fill>();

        Cursor cursor = mDatabase.query(DatabaseHandler.CRANEFILL.Table_CraneFill, mAllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Fill fill = cursorToFill(cursor);
            listFills.add(fill);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listFills;
    }

    public List<Fill> getFillByCategory(long Category_id) {
        List<Fill> listFills = new ArrayList<Fill>();

        Cursor cursor = mDatabase.query(DatabaseHandler.CRANEFILL.Table_CraneFill, mAllColumns,
                DatabaseHandler.CRANEFILL.CraneFill_id + " = ?",
                new String[] { String.valueOf(Category_id) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Fill fill = cursorToFill(cursor);
            listFills.add(fill);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listFills;
    }

    private Fill cursorToFill(Cursor cursor) {
        Fill fill = new Fill(
                cursor.getLong(0),
                cursor.getString(1));

        return fill;
    }

}
