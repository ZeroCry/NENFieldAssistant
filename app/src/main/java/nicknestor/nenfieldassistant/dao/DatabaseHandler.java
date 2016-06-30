package nicknestor.nenfieldassistant.dao;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


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

    protected static class CRANEFILL {
        protected static final String Table_CraneFill = "CraneFill";
        protected static final String CraneFill_id = "cranefill_id";
        protected static final String CraneFill_name = "name";
        protected static final String CraneFill_abbr = "abbr";
        protected static final String CraneFill_rowprice = "rowprice";
        protected static final String CraneFill_vendtarget = "vendtarget";
        protected static final String CraneFill_countby = "countby";
        protected static final String CraneFill_thenby = "thenby";
        protected static final String CraneFill_user = "user";
        protected static final String CraneFill_timestamp = "timestamp";
        protected static final String CraneFill_notes = "notes";
    }

    protected static class MANIFEST {
        protected static final String Table_Manifest = "Manifest";
        protected static final String Manifest_id = "manifest_id";
        protected static final String Manifest_asset = "manifest_asset";
        protected static final String Manifest_meter = "manifest_meter";
        protected static final String Manifest_toys = "manifest_toys";
        protected static final String Manifest_coin = "manifest_coin";
        protected static final String Manifest_bills = "manifest_bills";
        protected static final String Manifest_testplays = "manifest_testplays";
        protected static final String Manifest_refunds = "manifest_refunds";
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
        protected static final String Receiving_timestamp = "Receiving_timestamp";
        protected static final String Receiving_po = "Receiving_po";
        protected static final String Receiving_carrier = "Receiving_carrier";
        protected static final String Receiving_route = "Receiving_route";
        protected static final String Receiving_user = "Receiving_user";
    }

    protected static class RECEIVED {
        protected static final String Table_Received = "Received";
        protected static final String Received_id = "received_id";
        protected static final String Received_expected = "Received_expected";
        protected static final String Received_received = "Received_received";
        protected static final String Received_type = "Received_type";
        protected static final String Received_receivingID = "Received_receivingID";
        protected static final String Received_specificname = "Received_specificname";
        protected static final String Received_user = "Received_user";
    }

    protected static class INVENTORY {
        protected static final String Table_Inventory = "Inventory";
        protected static final String Inventory_id = "inventory_id";
        protected static final String Inventory_type = "inventory_type";
        protected static final String Inventory_specificname = "inventory_specificname";
        protected static final String Inventory_timestamp = "inventory_timestamp";
        protected static final String Inventory_user = "inventory_user";
        protected static final String Inventory_notes = "inventory_notes";
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

    protected static class BULKFILLS {
        protected static final String Table_BulkFills = "bulk_fills";
        protected static final String BulkFills_id = "bulkfills_id";
        protected static final String BulkFills_TypeId = "TypeId";
        protected static final String BulkFills_Display = "Display";
        protected static final String BulkFills_AltDisplay = "AltDisplay";
        protected static final String BulkFills_GenericCategory = "GenericCategory";
        protected static final String BulkFills_IsGeneric = "IsGeneric";
        protected static final String BulkFills_User = "UserId";
        protected static final String BulkFills_Timestamp = "Timestamp";
    }

    protected static class BULKLAYOUT {
        protected static final String Table_BulkLayout = "bulk_layout";
        protected static final String BulkLayout_id = "bulkfills_id";
        protected static final String BulkLayout_AssetId = "AssetId";
        protected static final String BulkLayout_Layout = "Layout";
    }

    protected static class BULKRACK {
        protected static final String Table_BulkRack = "bulk_rack";
        protected static final String BulkRack_id = "bulkrack_id";
        protected static final String BulkRack_TypeId = "TypeId";
        protected static final String BulkRack_DisplayId = "DisplayId";
        protected static final String BulkRack_Level = "level";
        protected static final String BulkRack_SlotInRack = "SlotInRack";
        protected static final String BulkRack_Timestamp = "timestamp";
        protected static final String BulkRack_UserId = "UserId";
    }

    protected static class TYPE {
        protected static final String Table_Type = "type";
        protected static final String Type_id = "type_id";
        protected static final String Type_typename = "typename";
        protected static final String Type_whole = "whole";
        protected static final String Type_part = "part";
        protected static final String Type_phonebookID = "phonebookID";
    }

    protected static class ROUTE {
        protected static final String Table_Route = "Route";
        protected static final String Route_id = "route_id";
        protected static final String Route_routename = "routename";
        protected static final String Route_routenumber = "routenumber";
        protected static final String Route_warehouseID = "warehouseID";
        protected static final String Route_reportstooffice = "reportstooffice";
        protected static final String Route_TM = "tm";
        protected static final String Route_JTM = "jtm";

    }

    protected static class OFFICE {
        protected static final String Table_Office = "Office";
        protected static final String Office_id = "office_id";
        protected static final String Office_OfficeManagerID = "officemanagerid";
        protected static final String Office_BookkeeperID = "bookkeeperid";
        protected static final String Office_OfficePhonenumber = "officephonenumber";
        protected static final String Office_Address = "address";
        protected static final String Office_City = "city";
        protected static final String Office_State = "state";
        protected static final String Office_Zip = "zip";
        protected static final String Office_OfficeID = "officeid";
        protected static final String Office_OfficeType = "officetype";
        protected static final String Office_CompanyName = "companyname";
        protected static final String Office_LocationReportsTo = "locationreportsto";
    }

    protected static class Phonebook {
        protected static final String Table_Phonebook = "Phonebook";
        protected static final String Phonebook_id = "phonebook_id";
        protected static final String Phonebook_FirstName = "officemanagerid";
        protected static final String Phonebook_LastName = "bookkeeperid";
        protected static final String Phonebook_Phonenumber = "officephonenumber";
        protected static final String Phonebook_Extension = "extension";
        protected static final String Phonebook_Email = "email";
        protected static final String Phonebook_Address = "address";
        protected static final String Phonebook_City = "city";
        protected static final String Phonebook_State = "state";
        protected static final String Phonebook_Zip = "zip";
        protected static final String Phonebook_RouteID = "routeid";
        protected static final String Phonebook_OfficeID = "officeid";
        protected static final String Phonebook_Company = "company";
    }

    protected static class FILLTYPE {
        protected static final String Table_FillType = "FillType";
        protected static final String FillType_id = "FillType_id";
        protected static final String FillType_Name = "FillType_Name";
        protected static final String FillType_Team = "FillType_Team";
        protected static final String FillType_Whole = "FillType_Whole";
        protected static final String FillType_Part = "FillType_Part";
        protected static final String FillType_UserId = "FillType_UserId";
        protected static final String FillType_Timestamp = "FillType_Timestamp";
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
            + ASSETS.Assets_machinetype + " INTEGER NOT NULL, "
            + ASSETS.Assets_user + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_AREAS = "CREATE TABLE " + AREAS.Table_Areas + "("
            + AREAS.Areas_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AREAS.Areas_area + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_CRANEFILL = "CREATE TABLE " + CRANEFILL.Table_CraneFill + "("
            + CRANEFILL.CraneFill_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CRANEFILL.CraneFill_name + " TEXT NOT NULL, "
            + CRANEFILL.CraneFill_abbr + " TEXT NOT NULL, "
            + CRANEFILL.CraneFill_rowprice + " INTEGER NOT NULL, "
            + CRANEFILL.CraneFill_vendtarget + " INTEGER NOT NULL, "
            + CRANEFILL.CraneFill_countby + " TEXT NOT NULL, "
            + CRANEFILL.CraneFill_thenby + " TEXT NOT NULL, "
            + CRANEFILL.CraneFill_user + " TEXT NOT NULL, "
            + CRANEFILL.CraneFill_timestamp + " TEXT NOT NULL, "
            + CRANEFILL.CraneFill_notes + " TEXT NOT NULL "
            +");";


    private static final String SQL_CREATE_TABLE_MANIFEST = "CREATE TABLE " + MANIFEST.Table_Manifest + "("
            + MANIFEST.Manifest_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MANIFEST.Manifest_meter + " INTEGER NOT NULL, "
            + MANIFEST.Manifest_toys + " INTEGER NOT NULL, "
            + MANIFEST.Manifest_coin + " INTEGER NOT NULL, "
            + MANIFEST.Manifest_bills + " INTEGER NOT NULL, "
            + MANIFEST.Manifest_testplays + " INTEGER NOT NULL, "
            + MANIFEST.Manifest_refunds + " INTEGER NOT NULL, "
            + MANIFEST.Manifest_asset + " INTEGER NOT NULL, "
            + MANIFEST.Manifest_user + " TEXT NOT NULL, "
            + MANIFEST.Manifest_timestamp + " TEXT NOT NULL, "
            + MANIFEST.Manifest_notes + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_PRODUCT_REQUESTS = "CREATE TABLE " + PRODUCTREQUESTS.Table_ProductRequests + "("
            + PRODUCTREQUESTS.ProductRequests_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRODUCTREQUESTS.ProductRequests_quantity + " INTEGER NOT NULL, "
            + PRODUCTREQUESTS.ProductRequests_fill + " TEXT NOT NULL, "
            + PRODUCTREQUESTS.ProductRequests_timestamp + " TEXT NOT NULL, "
            + PRODUCTREQUESTS.ProductRequests_user + " TEXT NOT NULL, "
            + PRODUCTREQUESTS.ProductRequests_notes + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_TIMECARD = "CREATE TABLE " + TIMECARD.Table_TimeCard + "("
            + TIMECARD.Timecard_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TIMECARD.Timecard_date + " INTEGER NOT NULL, "
            + TIMECARD.Timecard_time + " INTEGER NOT NULL, "
            + TIMECARD.Timecard_description + " TEXT NOT NULL, "
            + TIMECARD.Timecard_user + " TEXT NOT NULL, "
            + TIMECARD.Timecard_notes + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_RECEIVING = "CREATE TABLE " + RECEIVING.Table_Receiving + "("
            + RECEIVING.Receiving_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RECEIVING.Receiving_timestamp + " TEXT NOT NULL, "
            + RECEIVING.Receiving_po + " INTEGER NOT NULL, "
            + RECEIVING.Receiving_carrier + " TEXT NOT NULL, "
            + RECEIVING.Receiving_route + " INTEGER NOT NULL, "
            + RECEIVING.Receiving_user + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_RECEIVED = "CREATE TABLE " + RECEIVED.Table_Received + "("
            + RECEIVED.Received_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RECEIVED.Received_expected + " INTEGER NOT NULL, "
            + RECEIVED.Received_received + " INTEGER NOT NULL, "
            + RECEIVED.Received_type + " INTEGER NOT NULL, "
            + RECEIVED.Received_receivingID + " INTEGER NOT NULL, "
            + RECEIVED.Received_specificname + " TEXT NOT NULL, "
            + RECEIVED.Received_user + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_INVENTORY = "CREATE TABLE " + INVENTORY.Table_Inventory + "("
            + INVENTORY.Inventory_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + INVENTORY.Inventory_type + " INTEGER NOT NULL, "
            + INVENTORY.Inventory_specificname + " TEXT NOT NULL, "
            + INVENTORY.Inventory_timestamp + " TEXT NOT NULL, "
            + INVENTORY.Inventory_user + " INTEGER NOT NULL, "
            + INVENTORY.Inventory_notes + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_PART_REQUESTS = "CREATE TABLE " + PARTREQUESTS.Table_PartRequests + "("
            + PARTREQUESTS.PartRequests_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PARTREQUESTS.PartRequests_asset + " INTEGER NOT NULL, "
            + PARTREQUESTS.PartRequests_part + " INTEGER NOT NULL, "
            + PARTREQUESTS.PartRequests_timestamp + " INTEGER NOT NULL, "
            + PARTREQUESTS.PartRequests_user + " INTEGER NOT NULL, "
            + PARTREQUESTS.PartRequests_notes + " INTEGER NOT NULL, "
            + PARTREQUESTS.PartRequests_status + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_VACATION = "CREATE TABLE " + VACATION.Table_Vacation + "("
            + VACATION.Vacation_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + VACATION.Vacation_start + " TEXT NOT NULL, "
            + VACATION.Vacation_end + " TEXT NOT NULL, "
            + VACATION.Vacation_status + " TEXT NOT NULL, "
            + VACATION.Vacation_user + " INTEGER NOT NULL, "
            + VACATION.Vacation_vacationer + " INTEGER NOT NULL "
            +");";


    private static final String SQL_CREATE_TABLE_REMINDERMESSAGE = "CREATE TABLE " + REMINDERMESSAGE.Table_ReminderMessage + "("
            + REMINDERMESSAGE.ReminderMessage_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + REMINDERMESSAGE.ReminderMessage_note + " TEXT NOT NULL, "
            + REMINDERMESSAGE.ReminderMessage_whenid + " TEXT NOT NULL, "
            + REMINDERMESSAGE.ReminderMessage_ArriveDepart + " TEXT NOT NULL, "
            + REMINDERMESSAGE.ReminderMessage_place + " TEXT NOT NULL, "
            + REMINDERMESSAGE.ReminderMessage_user + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_USER = "CREATE TABLE " + USER.Table_User + "("
            + USER.User_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER.User_firstname + " TEXT NOT NULL, "
            + USER.User_lastname + " TEXT NOT NULL, "
            + USER.User_email + " TEXT NOT NULL, "
            + USER.User_phone + " INTEGER NOT NULL, "
            + USER.User_route + " INTEGER NOT NULL, "
            + USER.User_bossid + " INTEGER NOT NULL, "
            + USER.User_officeid + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_BULK_FILLS = "CREATE TABLE " + BULKFILLS.Table_BulkFills + "("
            + BULKFILLS.BulkFills_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BULKFILLS.BulkFills_TypeId + " INTEGER NOT NULL, "
            + BULKFILLS.BulkFills_Display + " TEXT NOT NULL, "
            + BULKFILLS.BulkFills_AltDisplay + " TEXT NOT NULL, "
            + BULKFILLS.BulkFills_GenericCategory + " INTEGER NOT NULL, "
            + BULKFILLS.BulkFills_IsGeneric + " BOOLEAN NOT NULL, "
            + BULKFILLS.BulkFills_User + " INTEGER NOT NULL, "
            + BULKFILLS.BulkFills_Timestamp + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_BULK_LAYOUT = "CREATE TABLE " + BULKLAYOUT.Table_BulkLayout + "("
            + BULKLAYOUT.BulkLayout_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BULKLAYOUT.BulkLayout_AssetId + " INTEGER NOT NULL, "
            + BULKLAYOUT.BulkLayout_Layout + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_BULK_RACK = "CREATE TABLE " + BULKRACK.Table_BulkRack + "("
            + BULKRACK.BulkRack_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BULKRACK.BulkRack_TypeId + " INTEGER NOT NULL, "
            + BULKRACK.BulkRack_DisplayId + " INTEGER NOT NULL, "
            + BULKRACK.BulkRack_Level + " INTEGER NOT NULL, "
            + BULKRACK.BulkRack_SlotInRack + " INTEGER NOT NULL, "
            + BULKRACK.BulkRack_Timestamp + " TEXT NOT NULL, "
            + BULKRACK.BulkRack_UserId + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_TYPE = "CREATE TABLE " + TYPE.Table_Type + "("
            + TYPE.Type_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TYPE.Type_typename + " INTEGER NOT NULL, "
            + TYPE.Type_whole + " INTEGER NOT NULL, "
            + TYPE.Type_part + " INTEGER NOT NULL, "
            + TYPE.Type_phonebookID + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_ROUTE = "CREATE TABLE " + ROUTE.Table_Route + "("
            + ROUTE.Route_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ROUTE.Route_routename + " TEXT NOT NULL, "
            + ROUTE.Route_routenumber + " INTEGER NOT NULL, "
            + ROUTE.Route_warehouseID + " INTEGER NOT NULL, "
            + ROUTE.Route_reportstooffice + " INTEGER NOT NULL, "
            + ROUTE.Route_TM + " INTEGER NOT NULL, "
            + ROUTE.Route_JTM + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_OFFICE = "CREATE TABLE " + OFFICE.Table_Office + "("
            + OFFICE.Office_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + OFFICE.Office_OfficeManagerID + " INTEGER NOT NULL, "
            + OFFICE.Office_BookkeeperID + " INTEGER NOT NULL, "
            + OFFICE.Office_OfficePhonenumber + " INTEGER NOT NULL, "
            + OFFICE.Office_Address + " TEXT NOT NULL, "
            + OFFICE.Office_City + " TEXT NOT NULL, "
            + OFFICE.Office_State + " TEXT NOT NULL, "
            + OFFICE.Office_Zip + " INTEGER NOT NULL, "
            + OFFICE.Office_OfficeID + " INTEGER NOT NULL, "
            + OFFICE.Office_OfficeType + " TEXT NOT NULL, "
            + OFFICE.Office_CompanyName + " TEXT NOT NULL, "
            + OFFICE.Office_LocationReportsTo + " INTEGER NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_PHONEBOOK = "CREATE TABLE " + Phonebook.Table_Phonebook + "("
            + Phonebook.Phonebook_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Phonebook.Phonebook_FirstName + " TEXT NOT NULL, "
            + Phonebook.Phonebook_LastName + " TEXT NOT NULL, "
            + Phonebook.Phonebook_Phonenumber + " INTEGER NOT NULL, "
            + Phonebook.Phonebook_Extension + " INTEGER NOT NULL, "
            + Phonebook.Phonebook_Email + " TEXT NOT NULL, "
            + Phonebook.Phonebook_Address + " TEXT NOT NULL, "
            + Phonebook.Phonebook_City + " TEXT NOT NULL, "
            + Phonebook.Phonebook_State + " TEXT NOT NULL, "
            + Phonebook.Phonebook_Zip + " INTEGER NOT NULL, "
            + Phonebook.Phonebook_RouteID + " INTEGER NOT NULL, "
            + Phonebook.Phonebook_OfficeID + " INTEGER NOT NULL, "
            + Phonebook.Phonebook_Company + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_FILLTYPE = "CREATE TABLE " + FILLTYPE.Table_FillType + "("
            + FILLTYPE.FillType_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FILLTYPE.FillType_Name + " TEXT NOT NULL, "
            + FILLTYPE.FillType_Team + " TEXT NOT NULL, "
            + FILLTYPE.FillType_Whole + " TEXT NOT NULL, "
            + FILLTYPE.FillType_Part + " TEXT NOT NULL, "
            + FILLTYPE.FillType_UserId + " TEXT NOT NULL, "
            + FILLTYPE.FillType_Timestamp + " TEXT NOT NULL "
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
        database.execSQL(SQL_CREATE_TABLE_CRANEFILL);
        database.execSQL(SQL_CREATE_TABLE_MANIFEST);
        database.execSQL(SQL_CREATE_TABLE_PRODUCT_REQUESTS);
        database.execSQL(SQL_CREATE_TABLE_TIMECARD);
        database.execSQL(SQL_CREATE_TABLE_RECEIVING);
        database.execSQL(SQL_CREATE_TABLE_INVENTORY);
        database.execSQL(SQL_CREATE_TABLE_PART_REQUESTS);
        database.execSQL(SQL_CREATE_TABLE_REMINDERMESSAGE);
        database.execSQL(SQL_CREATE_TABLE_VACATION);
        database.execSQL(SQL_CREATE_TABLE_USER);
        database.execSQL(SQL_CREATE_TABLE_BULK_FILLS);
        database.execSQL(SQL_CREATE_TABLE_BULK_LAYOUT);
        database.execSQL(SQL_CREATE_TABLE_BULK_RACK);
        database.execSQL(SQL_CREATE_TABLE_RECEIVED);
        database.execSQL(SQL_CREATE_TABLE_TYPE);
        database.execSQL(SQL_CREATE_TABLE_ROUTE);
        database.execSQL(SQL_CREATE_TABLE_OFFICE);
        database.execSQL(SQL_CREATE_TABLE_PHONEBOOK);
        database.execSQL(SQL_CREATE_TABLE_FILLTYPE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + LOCATIONS.Table_Locations);
        db.execSQL("DROP TABLE IF EXISTS "+ ASSETLOCATION.Table_AssetLocation);
        db.execSQL("DROP TABLE IF EXISTS "+ ASSETS.Table_Assets);
        db.execSQL("DROP TABLE IF EXISTS "+ AREAS.Table_Areas);
        db.execSQL("DROP TABLE IF EXISTS "+ CRANEFILL.Table_CraneFill);
        db.execSQL("DROP TABLE IF EXISTS "+ MANIFEST.Table_Manifest);
        db.execSQL("DROP TABLE IF EXISTS "+ PRODUCTREQUESTS.Table_ProductRequests);
        db.execSQL("DROP TABLE IF EXISTS "+ TIMECARD.Table_TimeCard);
        db.execSQL("DROP TABLE IF EXISTS "+ RECEIVING.Table_Receiving);
        db.execSQL("DROP TABLE IF EXISTS "+ RECEIVED.Table_Received);
        db.execSQL("DROP TABLE IF EXISTS "+ INVENTORY.Table_Inventory);
        db.execSQL("DROP TABLE IF EXISTS "+ PARTREQUESTS.Table_PartRequests);
        db.execSQL("DROP TABLE IF EXISTS "+ REMINDERMESSAGE.Table_ReminderMessage);
        db.execSQL("DROP TABLE IF EXISTS "+ VACATION.Table_Vacation);
        db.execSQL("DROP TABLE IF EXISTS "+ USER.Table_User);
        db.execSQL("DROP TABLE IF EXISTS "+ BULKLAYOUT.Table_BulkLayout);
        db.execSQL("DROP TABLE IF EXISTS "+ BULKFILLS.Table_BulkFills);
        db.execSQL("DROP TABLE IF EXISTS "+ BULKRACK.Table_BulkRack);
        db.execSQL("DROP TABLE IF EXISTS "+ TYPE.Table_Type);
        db.execSQL("DROP TABLE IF EXISTS "+ ROUTE.Table_Route);
        db.execSQL("DROP TABLE IF EXISTS "+ OFFICE.Table_Office);
        db.execSQL("DROP TABLE IF EXISTS "+ Phonebook.Table_Phonebook);
        db.execSQL("DROP TABLE IF EXISTS "+ FILLTYPE.Table_FillType);
        onCreate(db);
    }
}
