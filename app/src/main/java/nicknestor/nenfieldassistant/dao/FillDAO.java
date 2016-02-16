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
            DatabaseHandler.FILLS.Fills_id,
            DatabaseHandler.FILLS.Fills_fill
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
        values.put(DatabaseHandler.FILLS.Fills_fill, fill);
        long insertId = mDatabase
                .insert(DatabaseHandler.FILLS.Table_Fills, null, values);
        Cursor cursor = mDatabase.query(DatabaseHandler.FILLS.Table_Fills, mAllColumns,
                DatabaseHandler.FILLS.Fills_fill + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Fill newFill = cursorToFill(cursor);
        cursor.close();
        return newFill;
    }

    public void deleteFill(Fill fill) {
        long id = fill.getFillId();
        System.out.println("the deleted Fill has the id: " + id);
        mDatabase.delete(DatabaseHandler.FILLS.Table_Fills, DatabaseHandler.FILLS.Fills_id
                + " = " + id, null);
    }

    public List<Fill> getAllFills() {
        List<Fill> listFills = new ArrayList<Fill>();

        Cursor cursor = mDatabase.query(DatabaseHandler.FILLS.Table_Fills, mAllColumns,
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

        Cursor cursor = mDatabase.query(DatabaseHandler.FILLS.Table_Fills, mAllColumns,
                DatabaseHandler.FILLS.Fills_id + " = ?",
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
