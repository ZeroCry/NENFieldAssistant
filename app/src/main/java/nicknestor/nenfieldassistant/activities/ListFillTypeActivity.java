package nicknestor.nenfieldassistant.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.adapter.ListFillTypeAdapter;
import nicknestor.nenfieldassistant.dao.FillTypeDAO;
import nicknestor.nenfieldassistant.model.FillType;


public class ListFillTypeActivity extends NavigationDrawer implements OnItemLongClickListener, OnItemClickListener, NavigationView.OnNavigationItemSelectedListener
        {

    private static final String TAG = "ListFillTypesActivity";

    private static final int REQUEST_CODE_ADD_LOCATION = 40;
    public static final String EXTRA_ADDED_LOCATION = "extra_key_added_FillType";

    private ListView mListviewFillTypes;
    private TextView mTxtEmptyListFillTypes;
    private ImageButton mBtnAddFillType;

    private ListFillTypeAdapter mAdapter;
    private List<FillType> mListFillTypes;
    private FillTypeDAO mFillTypeDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
//Menu Stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listfilltypes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_listfilltypes);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_listfilltypes);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListFillTypeActivity.this, AddFillTypeActivity.class);
                startActivity(intent);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


//normal stuff
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_filltypes);

        // initialize views
        initViews();

        // fill the listView
        mFillTypeDao = new FillTypeDAO(this);
        mListFillTypes = mFillTypeDao.getAllFillTypes();
        if(mListFillTypes != null && !mListFillTypes.isEmpty()) {
            mAdapter = new ListFillTypeAdapter(this, mListFillTypes);
            mListviewFillTypes.setAdapter(mAdapter);
        }
        else {
            mTxtEmptyListFillTypes.setVisibility(View.VISIBLE);
            mListviewFillTypes.setVisibility(View.GONE);
        }
    }

    private void initViews() {
        this.mListviewFillTypes = (ListView) findViewById(R.id.list_filltypes);
        this.mTxtEmptyListFillTypes = (TextView) findViewById(R.id.txt_empty_list_filltype);
        //this.mBtnAddFillType = (ImageButton) findViewById(R.id.btn_add_filltype);
        this.mListviewFillTypes.setOnItemClickListener(this);
        this.mListviewFillTypes.setOnItemLongClickListener(this);
        //this.mBtnAddFillType.setOnClickListener(this);
    }

/*    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_filltype:
                Intent intent = new Intent(this, AddFillTypeActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_LOCATION);
                break;

            default:
                break;
        }
    }
*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_ADD_LOCATION) {
            if(resultCode == RESULT_OK) {
                // add the added FillType to the listFillTypes and refresh the listView
                if(data != null) {
                    FillType createdFillType = (FillType) data.getSerializableExtra(EXTRA_ADDED_LOCATION);
                    if(createdFillType != null) {
                        if(mListFillTypes == null)
                            mListFillTypes = new ArrayList<FillType>();
                        mListFillTypes.add(createdFillType);

                        if(mAdapter == null) {
                            if(mListviewFillTypes.getVisibility() != View.VISIBLE) {
                                mListviewFillTypes.setVisibility(View.VISIBLE);
                                mTxtEmptyListFillTypes.setVisibility(View.GONE);
                            }

                            mAdapter = new ListFillTypeAdapter(this, mListFillTypes);
                            mListviewFillTypes.setAdapter(mAdapter);
                        }
                        else {
                            mAdapter.setItems(mListFillTypes);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFillTypeDao.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long FillType_id) {
        FillType clickedFillType = mAdapter.getItem(position);
        Log.d(TAG, "clickedItem : " + clickedFillType.getFillTypeName() + ", " + clickedFillType.getFillTypeId());
//ListFillTypesActivity
        Intent intent = new Intent(ListFillTypeActivity.this, ListBulkActivity.class);
        intent.putExtra(ListBulkActivity.EXTRA_SELECTED_LOCATION_ID, clickedFillType.getFillTypeId());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//TODO Replace this section with EditFillType instead of DeleteFillType
/*
        FillType clickedFillType = mAdapter.getItem(position);
        Intent intent = new Intent(this, AddFillTypeActivity.class);

        startActivityForResult(intent, REQUEST_CODE_ADD_LOCATION);
        mFillTypeDao.editFillType(clickedFillType);

        showDeleteDialogConfirmation(clickedFillType);
*/        return true;
    }



    private void showDeleteDialogConfirmation(final FillType clickedFillType) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setMessage("Are you sure you want to delete the \""+clickedFillType.getFillTypeName()+"\" filltype ?");

        // set positive button YES message
        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete the FillType and refresh the list
                if (mFillTypeDao != null) {
                    //mFillTypeDao.deleteFillType(clickedFillType);
                    mListFillTypes.remove(clickedFillType);

                    //refresh the listView
                    if (mListFillTypes.isEmpty()) {
                        mListviewFillTypes.setVisibility(View.GONE);
                        mTxtEmptyListFillTypes.setVisibility(View.VISIBLE);
                    }
                    mAdapter.setItems(mListFillTypes);
                    mAdapter.notifyDataSetChanged();
                }

                dialog.dismiss();
                Toast.makeText(ListFillTypeActivity.this, R.string.FillType_deleted_successfully, Toast.LENGTH_SHORT).show();
            }
        });

        // set neutral button OK
        alertDialogBuilder.setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }

//More Menu Stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
