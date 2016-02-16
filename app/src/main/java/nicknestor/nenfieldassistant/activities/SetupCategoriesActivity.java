package nicknestor.nenfieldassistant.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.adapter.ListCategoriesAdapter;
import nicknestor.nenfieldassistant.dao.CategoriesDAO;
import nicknestor.nenfieldassistant.dao.CategoryDAO;
import nicknestor.nenfieldassistant.model.Category;

public class SetupCategoriesActivity extends Activity implements OnItemClickListener {

    private CategoriesDAODAO mCategoriesDao;

    private static final String TAG = "SetupCategoriesActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_categories);

        // initialize views
        initViews();

        // fill the listView
        mCategoriesDao = new CategoriesDAO(this);
        mListCategories = mCategoriesDao.getAllCategories();
        if(mListCategories != null && !mListCategories.isEmpty()) {
            mAdapter = new ListCategoriesAdapter(this, mListCategories);
            mListviewCategories.setAdapter(mAdapter);
        }
        else {
            mTxtEmptyListCategories.setVisibility(View.VISIBLE);
            mListviewCategories.setVisibility(View.GONE);
        }
    }
    
    private void initViews() {
        this.mListviewCategories = (ListView) findViewById(R.id.list_categories);
        this.mTxtEmptyListCategories = (TextView) findViewById(R.id.txt_empty_list_Categories);
        this.mBtnAddCategory = (ImageButton) findViewById(R.id.btn_add_category);
        this.mListviewCategories.setOnItemClickListener(this);
        this.mListviewCategories.setOnItemLongClickListener(this);
        this.mBtnAddCategory.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_category:
                Intent intent = new Intent(this, AddCategoryActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_CATEGORY);
                break;

            default:
                break;
        }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        mCategoryDao.close();
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long Category_id) {
        Category clickedCategory = mAdapter.getItem(position);
        Log.d(TAG, "clickedItem : " + clickedCategory.getStore() + ", " + clickedCategory.getCategoryId());
        Intent intent = new Intent(this, ListAssetsActivity.class);
        intent.putExtra(ListAssetsActivity.EXTRA_SELECTED_CATEGORY_ID, clickedCategory.getCategoryId());
        startActivity(intent);
    }

}
