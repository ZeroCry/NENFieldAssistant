package nicknestor.nenfieldassistant;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import nicknestor.nenfieldassistant.dao.DatabaseHandler;

/**
 * Created by Nick on 1/17/2016.
 */
public class UsefulClasses {
    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDatabaseHandler;

    public void open() throws SQLException {
        mDatabase = mDatabaseHandler.getWritableDatabase();
    }

    public static String getCurrentTimeStamp(){
    try {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

        return currentTimeStamp;
    } catch (Exception e) {
        e.printStackTrace();

        return null;
    }
}

    public String QueryHelper(String Table, String Columns, String Selection, String SelectionArgs, String GroupBy, String Having, String OrderBy, String Limit){
        String parameters = Table + ", " + Columns + ", " + Selection + ", " + SelectionArgs + ", " + GroupBy + ", " + Having + ", " + OrderBy + ", " + Limit;
        return parameters;
    }

}
