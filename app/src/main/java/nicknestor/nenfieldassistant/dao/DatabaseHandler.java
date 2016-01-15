package nicknestor.nenfieldassistant.dao;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String TAG = "DatabaseHandler";

    protected static final String DATABASE_NAME = "NEN_Database.db";
    protected static final int DATABASE_VERSION = 1;

    protected static class CLASS_LOCATIONS {
        protected static final String Table_Locations = "Locations";
        protected static final String Locations_id = "location_id";
        protected static final String Locations_store = "store";
        protected static final String Locations_abbr = "abbr";
        protected static final String Locations_storeNumber = "storeID";
        protected static final String Locations_address = "address";
        protected static final String Locations_city = "city";
        protected static final String Locations_state = "state";
        protected static final String Locations_zip = "zip";
        protected static final String Locations_phone = "phone";
    }
    protected static class CLASS_ASSET_LOCATION {
        protected static final String Table_Asset_Location = "AssetLocation";
        protected static final String Asset_Location_id = "_id";
        protected static final String Asset_Location_id_asset = "asset_id";
        protected static final String Asset_Location_id_area = "area_id";
        protected static final String Asset_Location_id_location = "location_id";
        }
    protected static class CLASS_ASSETS {
        protected static final String Table_Assets = "Assets";
        protected static final String Assets_id = "asset_id";
        protected static final String Assets_assetnumber = "assetnumber";
        protected static final String Assets_category = "category";
        protected static final String Assets_machinetype = "machinetype";
        protected static final String Assets_location_id = "assetlocation";
        protected static final String Assets_Area_id = "assetarea";

    }

    protected static class CLASS_AREAS {
        protected static final String Table_Areas = "Areas";
        protected static final String Areas_id = "areas_id";
        protected static final String Areas_area = "areas_area";
    }


    private static final String SQL_CREATE_TABLE_LOCATIONS = "CREATE TABLE " + CLASS_LOCATIONS.Table_Locations + "("
            + CLASS_LOCATIONS.Locations_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CLASS_LOCATIONS.Locations_store + " TEXT NOT NULL, "
            + CLASS_LOCATIONS.Locations_abbr + " TEXT NOT NULL, "
            + CLASS_LOCATIONS.Locations_storeNumber + " INTEGER NOT NULL, "
            + CLASS_LOCATIONS.Locations_address + " TEXT, "
            + CLASS_LOCATIONS.Locations_city + " TEXT NOT NULL, "
            + CLASS_LOCATIONS.Locations_state + " TEXT, "
            + CLASS_LOCATIONS.Locations_zip + " INTEGER, "
            + CLASS_LOCATIONS.Locations_phone + " INTEGER "
            +");";

    private static final String SQL_CREATE_TABLE_ASSET_LOCATION = "CREATE TABLE " + CLASS_ASSET_LOCATION.Table_Asset_Location + "("
            + CLASS_ASSET_LOCATION.Asset_Location_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CLASS_ASSET_LOCATION.Asset_Location_id_asset + " INTEGER NOT NULL, "
            + CLASS_ASSET_LOCATION.Asset_Location_id_area + " INTEGER NOT NULL, "
            + CLASS_ASSET_LOCATION.Asset_Location_id_location + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_ASSETS = "CREATE TABLE " + CLASS_ASSETS.Table_Assets + "("
            + CLASS_ASSETS.Assets_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CLASS_ASSETS.Assets_assetnumber + " INTEGER NOT NULL, "
            + CLASS_ASSETS.Assets_category + " INTEGER NOT NULL, "
            + CLASS_ASSETS.Assets_machinetype + " INTEGER NOT NULL, "
            + CLASS_ASSETS.Assets_location_id + " LONG NOT NULL, "
            + CLASS_ASSETS.Assets_Area_id + " INTEGER NOT NULL "

            +");";

    private static final String SQL_CREATE_TABLE_AREAS = "CREATE TABLE " + CLASS_AREAS.Table_Areas + "("
            + CLASS_AREAS.Areas_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CLASS_AREAS.Areas_area + " TEXT NOT NULL "
            +");";



    public DatabaseHandler(Context Context) {
        super (Context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    @Override
    public void onCreate (SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_LOCATIONS);
        database.execSQL(SQL_CREATE_TABLE_ASSET_LOCATION);
        database.execSQL(SQL_CREATE_TABLE_ASSETS);
        database.execSQL(SQL_CREATE_TABLE_AREAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + CLASS_LOCATIONS.Table_Locations);
        db.execSQL("DROP TABLE IF EXISTS "+ CLASS_ASSET_LOCATION.Table_Asset_Location);
        db.execSQL("DROP TABLE IF EXISTS "+ CLASS_ASSETS.Table_Assets);
        db.execSQL("DROP TABLE IF EXISTS "+ CLASS_AREAS.Table_Areas);
        onCreate(db);
    }
}
