package nicknestor.nenfieldassistant.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class ianap {
    private static SQLiteDatabase mDatabase;

    // get ID of Asset
    public static Cursor SimpleQuery(String columns, String table, String lookup, String specific) {
        Cursor cursor = mDatabase.rawQuery("SELECT ? FROM ? WHERE ? = ?", new String[]{columns, table, lookup, specific});
        return cursor;
    }


    // get Assets of Location
    public static Cursor SimpleQuery(String table1, String table2, String id1, String id2, String lookup, long locationId) {
        Cursor cursor = mDatabase.rawQuery(
                "SELECT * FROM ? INNER JOIN ? ON ? = ? WHERE ? = ?",
                new String[] {table1, table2, id1, id2, lookup, Long.toString(locationId)} );
        return cursor;
    }

    // get all locations
    public static Cursor SimpleQuery(String table){
        Cursor cursor = mDatabase.rawQuery(
                "SELECT * FROM ? ",
                new String[]{table});
        return cursor;
    }


}
