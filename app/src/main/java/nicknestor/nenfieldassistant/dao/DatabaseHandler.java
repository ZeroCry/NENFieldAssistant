package nicknestor.nenfieldassistant.dao;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String TAG = "DatabaseHandler";

    protected static final String DATABASE_NAME = "NEN_Database.db";
    protected static final int DATABASE_VERSION = 1;
    protected static final String Table_Areas = "Areas.db";

    protected static class CLASS_LOCATIONS {
        protected static final String Table_Locations = "Locations.db";
        protected static final String Locations_id = "id";
        protected static final String Locations_store = "store";
        protected static final String Locations_abbr = "abbr";
        protected static final String Locations_storeNumber = "storeNumber";
        protected static final String Locations_address = "address";
        protected static final String Locations_city = "city";
        protected static final String Locations_state = "state";
        protected static final String Locations_zip = "zip";
        protected static final String Locations_phone = "phone";
    }
    protected static class CLASS_ASSET_LOCATION {
        protected static final String Table_Asset_Location = "Assets.db";
        protected static final String Asset_Location_id = "id";
        protected static final String Asset_Location_id_asset = "idasset";
        protected static final String Asset_Location_id_area = "idarea";
        protected static final String Asset_Location_id_location = "idlocation";
        }
    protected static class CLASS_ASSETS {
        protected static final String Table_Assets = "Assets.db";
        protected static final String Assets_id = "id";
        protected static final String Assets_assetnumber = "assetnumber";
        protected static final String Assets_category = "category";
        protected static final String Assets_machinetype = "machinetype";
        protected static final String Assets_assetlocation = "assetlocation";
        }

    private static final String SQL_CREATE_TABLE_LOCATIONS = "CREATE_TABLE " + CLASS_LOCATIONS.Table_Locations + "("
            + CLASS_LOCATIONS.Locations_id + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CLASS_LOCATIONS.Locations_store + "TEXT NOT NULL, "
            + CLASS_LOCATIONS.Locations_abbr + "TEXT NOT NULL, "
            + CLASS_LOCATIONS.Locations_storeNumber + "TEXT NOT NULL, "
            + CLASS_LOCATIONS.Locations_address + "TEXT, "
            + CLASS_LOCATIONS.Locations_city + "TEXT NOT NULL, "
            + CLASS_LOCATIONS.Locations_state + "TEXT, "
            + CLASS_LOCATIONS.Locations_zip + "REAL, "
            + CLASS_LOCATIONS.Locations_phone + "TEXT "
            +");";

    private static final String SQL_CREATE_TABLE_ASSET_LOCATION = "CREATE_TABLE " + CLASS_ASSET_LOCATION.Table_Asset_Location + "("
            + CLASS_ASSET_LOCATION.Asset_Location_id + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CLASS_ASSET_LOCATION.Asset_Location_id_asset + "TEXT NOT NULL, "
            + CLASS_ASSET_LOCATION.Asset_Location_id_area + "TEXT NOT NULL, "
            + CLASS_ASSET_LOCATION.Asset_Location_id_location + "TEXT NOT NULL, "
            +");";

    private static final String SQL_CREATE_TABLE_ASSETS = "CREATE_TABLE " + CLASS_ASSETS.Table_Assets + "("
            + CLASS_ASSETS.Assets_id + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CLASS_ASSETS.Assets_assetnumber + "INTEGER NOT NULL, "
            + CLASS_ASSETS.Assets_category + "TEXT NOT NULL, "
            + CLASS_ASSETS.Assets_machinetype + "TEXT NOT NULL, "
            + CLASS_ASSETS.Assets_assetlocation + "TEXT NOT NULL, "
            +");";

    public DatabaseHandler(Context Context) {
        super (Context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    @Override
    public void onCreate (SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_LOCATIONS);
        database.execSQL(SQL_CREATE_TABLE_ASSET_LOCATION);
        database.execSQL(SQL_CREATE_TABLE_ASSETS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + CLASS_LOCATIONS.Table_Locations);
        db.execSQL("DROP TABLE IF EXISTS "+ CLASS_ASSET_LOCATION.Table_Asset_Location);
        db.execSQL("DROP TABLE IF EXISTS "+ CLASS_ASSETS.Table_Assets);
        onCreate(db);
    }
}
