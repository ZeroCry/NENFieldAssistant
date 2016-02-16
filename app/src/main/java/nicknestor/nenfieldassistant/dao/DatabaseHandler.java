package nicknestor.nenfieldassistant.dao;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.jar.Manifest;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String TAG = "DatabaseHandler";

    protected static final String DATABASE_NAME = "NEN_Database.db";
    protected static final int DATABASE_VERSION = 1;

    protected static class LOCATIONS {
        protected static final String Table_Locations = "Locations";
        protected static final String Locations_id = "locations_id";
        protected static final String Locations_store = "store";
        protected static final String Locations_abbr = "abbr";
        protected static final String Locations_storeNumber = "storeID";
        protected static final String Locations_address = "address";
        protected static final String Locations_city = "city";
        protected static final String Locations_state = "state";
        protected static final String Locations_zip = "zip";
        protected static final String Locations_phone = "phone";
    }
    protected static class ASSETLOCATION {
        protected static final String Table_AssetLocation = "AssetLocation";
        protected static final String AssetLocation_id = "assetlocation_id";
        protected static final String AssetLocation_id_asset = "asset_id";
        protected static final String AssetLocation_id_location = "location_id";
        protected static final String AssetLocation_id_area = "areas_id";
        protected static final String AssetLocation_timestamp = "timestamp";
        protected static final String AssetLocation_notes = "notes";
        protected static final String AssetLocation_user = "user";
    }

    protected static class ASSETS {
        protected static final String Table_Assets = "Assets";
        protected static final String Assets_id = "assets_id";
        protected static final String Assets_assetnumber = "assetnumber";
        protected static final String Assets_category = "category";
        protected static final String Assets_machinetype = "machinetype";
        protected static final String Assets_user = "user";
    }

    protected static class AREAS {
        protected static final String Table_Areas = "Areas";
        protected static final String Areas_id = "area_id";
        protected static final String Areas_area = "areas_area";
    }

    protected static class FILLS {
        protected static final String Table_Fills = "Fills";
        protected static final String Fills_id = "fill_id";
        protected static final String Fills_fill = "fill_area";
    }

    protected static class FILL {
        protected static final String Table_Fill = "Fill";
        protected static final String Fill_id = "fill_id";
        protected static final String Fill_category = "fill_category";
        protected static final String Fill_typeid = "fill_typeid";
        protected static final String Fill_orderby = "fill_orderby";
    }

    protected static class ASSETFILLS {
        protected static final String Table_AssetFills = "AssetFills";
        protected static final String AssetFills_id = "assetfills_id";
        protected static final String AssetFills_asset = "assetfills_asset";
        protected static final String AssetFills_fill = "assetfills_fill";
        protected static final String AssetFills_timestamp = "assetfills_timestamp";
        protected static final String AssetFills_user = "assetfills_user";
        protected static final String AssetFills_notes = "assetfills_notes";
    }

    protected static class MANIFEST {
        protected static final String Table_Manifest = "Manifest";
        protected static final String Manifest_id = "manifest_id";
        protected static final String Manifest_meter = "manifest_meter";
        protected static final String Manifest_toys = "manifest_toys";
        protected static final String Manifest_coin = "manifest_coin";
        protected static final String Manifest_bills = "manifest_bills";
        protected static final String Manifest_testplays = "manifest_testplays";
        protected static final String Manifest_refunds = "manifest_refunds";
        protected static final String Manifest_asset = "manifest_asset";
        protected static final String Manifest_user = "manifest_user";
        protected static final String Manifest_timestamp = "manifest_timestamp";
        protected static final String Manifest_notes = "manifest_notes";
    }

    protected static class PRODUCTREQUESTS {
        protected static final String Table_ProductRequests = "ProductRequests";
        protected static final String ProductRequests_id = "productpequests_id";
        protected static final String ProductRequests_quantity = "ProductRequests_quantity";
        protected static final String ProductRequests_fill = "ProductRequests_fill";
        protected static final String ProductRequests_timestamp = "ProductRequests_timestamp";
        protected static final String ProductRequests_user = "ProductRequests_user";
        protected static final String ProductRequests_notes = "ProductRequests_notes";
    }

    protected static class TIMECARD {
        protected static final String Table_TimeCard = "Timecard";
        protected static final String Timecard_id = "timecard_id";
        protected static final String Timecard_date = "timecard_date";
        protected static final String Timecard_time = "timecard_time";
        protected static final String Timecard_description = "timecard_description";
        protected static final String Timecard_user = "timecard_user";
        protected static final String Timecard_notes = "timecard_notes";
    }

    protected static class RECEIVING {
        protected static final String Table_Receiving = "Receiving";
        protected static final String Receiving_id = "receiving_id";
        protected static final String Receiving_date = "Receiving_date";
        protected static final String Receiving_carrier = "Receiving_carrier";
        protected static final String Receiving_expected = "Receiving_expected";
        protected static final String Receiving_received = "Receiving_received";
        protected static final String Receiving_po = "Receiving_po";
        protected static final String Receiving_route = "Receiving_route";
        protected static final String Receiving_user = "Receiving_user";

    }

    protected static class INVENTORY {
        protected static final String Table_Inventory = "Inventory";
        protected static final String Inventory_id = "inventory_id";
        protected static final String Inventory_fillid = "inventory_fillid";
        protected static final String Inventory_qtywhole = "inventory_qtywhole";
        protected static final String Inventory_qtypart = "inventory_qtypart";
        protected static final String Inventory_user = "inventory_user";
    }

    protected static class PARTREQUESTS {
        protected static final String Table_PartRequests = "PartRequests";
        protected static final String PartRequests_id = "partrequests_id";
        protected static final String PartRequests_asset = "partrequests_asset";
        protected static final String PartRequests_part = "partrequests_part";
        protected static final String PartRequests_timestamp = "partrequests_timestamp";
        protected static final String PartRequests_user = "partrequests_user";
        protected static final String PartRequests_notes = "partrequests_notes";
        protected static final String PartRequests_status = "partrequests_status";
    }

    protected static class REMINDERMESSAGE {
        protected static final String Table_ReminderMessage = "ReminderMessage";
        protected static final String ReminderMessage_id = "ReminderMessage_id";
        protected static final String ReminderMessage_note = "ReminderMessage_note";
        protected static final String ReminderMessage_whenid = "ReminderMessage_whenid";
        protected static final String ReminderMessage_ArriveDepart = "ReminderMessage_ArriveDepart";
        protected static final String ReminderMessage_place = "ReminderMessage_place";
        protected static final String ReminderMessage_user = "ReminderMessage_user";
    }

    protected static class VACATION {
        protected static final String Table_Vacation = "Vacation";
        protected static final String Vacation_id = "Vacation_id";
        protected static final String Vacation_start = "Vacation_start";
        protected static final String Vacation_end = "Vacation_end";
        protected static final String Vacation_status = "Vacation_status";
        protected static final String Vacation_user = "Vacation_user";
        protected static final String Vacation_vacationer = "Vacation_vacationer";
    }

    protected static class USER {
        protected static final String Table_User = "User";
        protected static final String User_id = "user_id";
        protected static final String User_firstname = "user_firstname";
        protected static final String User_lastname = "user_lastname";
        protected static final String User_email = "user_email";
        protected static final String User_phone = "user_phone";
        protected static final String User_route = "user_route";
        protected static final String User_bossid = "user_bossid";
        protected static final String User_officeid = "user_officeid";
    }

    private static final String SQL_CREATE_TABLE_LOCATIONS = "CREATE TABLE " + LOCATIONS.Table_Locations + "("
            + LOCATIONS.Locations_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LOCATIONS.Locations_store + " TEXT NOT NULL, "
            + LOCATIONS.Locations_abbr + " TEXT NOT NULL, "
            + LOCATIONS.Locations_storeNumber + " INTEGER NOT NULL, "
            + LOCATIONS.Locations_address + " TEXT, "
            + LOCATIONS.Locations_city + " TEXT NOT NULL, "
            + LOCATIONS.Locations_state + " TEXT, "
            + LOCATIONS.Locations_zip + " INTEGER, "
            + LOCATIONS.Locations_phone + " INTEGER "
            +");";

    private static final String SQL_CREATE_TABLE_ASSETLOCATION = "CREATE TABLE " + ASSETLOCATION.Table_AssetLocation + "("
            + ASSETLOCATION.AssetLocation_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ASSETLOCATION.AssetLocation_id_asset + " INTEGER NOT NULL, "
            + ASSETLOCATION.AssetLocation_id_location + " INTEGER NOT NULL, "
            + ASSETLOCATION.AssetLocation_id_area + " INTEGER NOT NULL, "
            + ASSETLOCATION.AssetLocation_timestamp + " INTEGER NOT NULL, "
            + ASSETLOCATION.AssetLocation_notes + " TEXT, "
            + ASSETLOCATION.AssetLocation_user + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_ASSETS = "CREATE TABLE " + ASSETS.Table_Assets + "("
            + ASSETS.Assets_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ASSETS.Assets_assetnumber + " INTEGER NOT NULL, "
            + ASSETS.Assets_category + " INTEGER NOT NULL, "
            + ASSETS.Assets_machinetype + " INTEGER NOT NULL "
            + ASSETS.Assets_user + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_AREAS = "CREATE TABLE " + AREAS.Table_Areas + "("
            + AREAS.Areas_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AREAS.Areas_area + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_FILLS = "CREATE TABLE " + FILLS.Table_Fills + "("
            + FILLS.Fills_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FILLS.Fills_fill + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_FILL = "CREATE TABLE " + FILL.Table_Fill + "("
            + FILL.Fill_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FILL.Fill_category + " TEXT NOT NULL "
            + FILL.Fill_typeid + " INTEGER NOT NULL "
            + FILL.Fill_orderby + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_ASSETFILLS = "CREATE TABLE " + ASSETFILLS.Table_AssetFills + "("
            + ASSETFILLS.AssetFills_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ASSETFILLS.AssetFills_asset + " TEXT NOT NULL "
            + ASSETFILLS.AssetFills_fill + " TEXT NOT NULL "
            + ASSETFILLS.AssetFills_timestamp + " TEXT NOT NULL "
            + ASSETFILLS.AssetFills_user + " TEXT NOT NULL "
            + ASSETFILLS.AssetFills_notes + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_MANIFEST = "CREATE TABLE " + MANIFEST.Table_Manifest + "("
            + MANIFEST.Manifest_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MANIFEST.Manifest_meter + " INTEGER NOT NULL "
            + MANIFEST.Manifest_toys + " INTEGER NOT NULL "
            + MANIFEST.Manifest_coin + " INTEGER NOT NULL "
            + MANIFEST.Manifest_bills + " INTEGER NOT NULL "
            + MANIFEST.Manifest_testplays + " INTEGER NOT NULL "
            + MANIFEST.Manifest_refunds + " INTEGER NOT NULL "
            + MANIFEST.Manifest_asset + " INTEGER NOT NULL "
            + MANIFEST.Manifest_user + " TEXT NOT NULL "
            + MANIFEST.Manifest_timestamp + " TEXT NOT NULL "
            + MANIFEST.Manifest_notes + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_PRODUCT_REQUESTS = "CREATE TABLE " + PRODUCTREQUESTS.Table_ProductRequests + "("
            + PRODUCTREQUESTS.ProductRequests_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRODUCTREQUESTS.ProductRequests_quantity + " INTEGER NOT NULL "
            + PRODUCTREQUESTS.ProductRequests_fill + " TEXT NOT NULL "
            + PRODUCTREQUESTS.ProductRequests_timestamp + " TEXT NOT NULL "
            + PRODUCTREQUESTS.ProductRequests_user + " TEXT NOT NULL "
            + PRODUCTREQUESTS.ProductRequests_notes + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_TIMECARD = "CREATE TABLE " + TIMECARD.Table_TimeCard + "("
            + TIMECARD.Timecard_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TIMECARD.Timecard_date + " INTEGER NOT NULL "
            + TIMECARD.Timecard_time + " INTEGER NOT NULL "
            + TIMECARD.Timecard_description + " TEXT NOT NULL "
            + TIMECARD.Timecard_user + " TEXT NOT NULL "
            + TIMECARD.Timecard_notes + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_RECEIVING = "CREATE TABLE " + RECEIVING.Table_Receiving + "("
            + RECEIVING.Receiving_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RECEIVING.Receiving_date + " TEXT NOT NULL "
            + RECEIVING.Receiving_carrier + " TEXT NOT NULL "
            + RECEIVING.Receiving_expected + " INTEGER NOT NULL "
            + RECEIVING.Receiving_received + " INTEGER NOT NULL "
            + RECEIVING.Receiving_po + " INTEGER NOT NULL "
            + RECEIVING.Receiving_route + " INTEGER NOT NULL "
            + RECEIVING.Receiving_user + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_INVENTORY = "CREATE TABLE " + INVENTORY.Table_Inventory + "("
            + INVENTORY.Inventory_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + INVENTORY.Inventory_fillid + " INTEGER NOT NULL "
            + INVENTORY.Inventory_qtywhole + " INTEGER NOT NULL "
            + INVENTORY.Inventory_qtypart + " INTEGER NOT NULL "
            + INVENTORY.Inventory_user + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_PART_REQUESTS = "CREATE TABLE " + PARTREQUESTS.Table_PartRequests + "("
            + PARTREQUESTS.PartRequests_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PARTREQUESTS.PartRequests_asset + " INTEGER NOT NULL "
            + PARTREQUESTS.PartRequests_part + " INTEGER NOT NULL "
            + PARTREQUESTS.PartRequests_timestamp + " INTEGER NOT NULL "
            + PARTREQUESTS.PartRequests_user + " INTEGER NOT NULL "
            + PARTREQUESTS.PartRequests_notes + " INTEGER NOT NULL "
            + PARTREQUESTS.PartRequests_status + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_VACATION = "CREATE TABLE " + VACATION.Table_Vacation + "("
            + VACATION.Vacation_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + VACATION.Vacation_start + " TEXT NOT NULL "
            + VACATION.Vacation_end + " TEXT NOT NULL "
            + VACATION.Vacation_status + " TXET NOT NULL "
            + VACATION.Vacation_user + " INTEGER NOT NULL "
            + VACATION.Vacation_vacationer + " INTEGER NOT NULL "
            +");";


    private static final String SQL_CREATE_TABLE_REMINDERMESSAGE = "CREATE TABLE " + REMINDERMESSAGE.Table_ReminderMessage + "("
            + REMINDERMESSAGE.ReminderMessage_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + REMINDERMESSAGE.ReminderMessage_note + " TEXT NOT NULL "
            + REMINDERMESSAGE.ReminderMessage_whenid + " TEXT NOT NULL "
            + REMINDERMESSAGE.ReminderMessage_ArriveDepart + " TEXT NOT NULL "
            + REMINDERMESSAGE.ReminderMessage_place + " TEXT NOT NULL "
            + REMINDERMESSAGE.ReminderMessage_user + " TXET NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_USER = "CREATE TABLE " + USER.Table_User + "("
            + USER.User_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER.User_firstname + " TEXT NOT NULL "
            + USER.User_lastname + " TEXT NOT NULL "
            + USER.User_email + " TXET NOT NULL "
            + USER.User_phone + " INTEGER NOT NULL "
            + USER.User_route + " INTEGER NOT NULL "
            + USER.User_bossid + " INTEGER NOT NULL "
            + USER.User_officeid + " INTEGER NOT NULL "
            +");";

    public DatabaseHandler(Context Context) {
        super (Context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    @Override
    public void onCreate (SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_LOCATIONS);
        database.execSQL(SQL_CREATE_TABLE_ASSETLOCATION);
        database.execSQL(SQL_CREATE_TABLE_ASSETS);
        database.execSQL(SQL_CREATE_TABLE_AREAS);
        database.execSQL(SQL_CREATE_TABLE_FILLS);
        database.execSQL(SQL_CREATE_TABLE_FILL);
        database.execSQL(SQL_CREATE_TABLE_ASSETFILLS);
        database.execSQL(SQL_CREATE_TABLE_MANIFEST);
        database.execSQL(SQL_CREATE_TABLE_PRODUCT_REQUESTS);
        database.execSQL(SQL_CREATE_TABLE_TIMECARD);
        database.execSQL(SQL_CREATE_TABLE_RECEIVING);
        database.execSQL(SQL_CREATE_TABLE_INVENTORY);
        database.execSQL(SQL_CREATE_TABLE_PART_REQUESTS);
        database.execSQL(SQL_CREATE_TABLE_REMINDERMESSAGE);
        database.execSQL(SQL_CREATE_TABLE_VACATION);
        database.execSQL(SQL_CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + LOCATIONS.Table_Locations);
        db.execSQL("DROP TABLE IF EXISTS "+ ASSETLOCATION.Table_AssetLocation);
        db.execSQL("DROP TABLE IF EXISTS "+ ASSETS.Table_Assets);
        db.execSQL("DROP TABLE IF EXISTS "+ AREAS.Table_Areas);
        db.execSQL("DROP TABLE IF EXISTS "+ FILLS.Table_Fills);
        db.execSQL("DROP TABLE IF EXISTS "+ FILL.Table_Fill);
        db.execSQL("DROP TABLE IF EXISTS "+ ASSETFILLS.Table_AssetFills);
        db.execSQL("DROP TABLE IF EXISTS "+ MANIFEST.Table_Manifest);
        db.execSQL("DROP TABLE IF EXISTS "+ PRODUCTREQUESTS.Table_ProductRequests);
        db.execSQL("DROP TABLE IF EXISTS "+ TIMECARD.Table_TimeCard);
        db.execSQL("DROP TABLE IF EXISTS "+ RECEIVING.Table_Receiving);
        db.execSQL("DROP TABLE IF EXISTS "+ INVENTORY.Table_Inventory);
        db.execSQL("DROP TABLE IF EXISTS "+ PARTREQUESTS.Table_PartRequests);
        db.execSQL("DROP TABLE IF EXISTS "+ REMINDERMESSAGE.Table_ReminderMessage);
        db.execSQL("DROP TABLE IF EXISTS "+ VACATION.Table_Vacation);
        db.execSQL("DROP TABLE IF EXISTS "+ USER.Table_User);
        onCreate(db);
    }
}
