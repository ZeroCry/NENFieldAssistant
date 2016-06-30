package nicknestor.nenfieldassistant.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.dao.FillTypeDAO;
import nicknestor.nenfieldassistant.dao.LocationDAO;
import nicknestor.nenfieldassistant.model.FillType;
import nicknestor.nenfieldassistant.model.Location;


public class AddFillTypeActivity extends Activity {


    public static final String TAG = "AddFillTypeActivity";

    private EditText mAddFillType_Name;

    private FillTypeDAO mFillTypeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filltype);

        initViews();

        this.mFillTypeDao = new FillTypeDAO(this);
    }

    private void initViews() {
        this.mAddFillType_Name = (EditText) findViewById(R.id.AddFillType_Name);

    }

/*    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_filltype:
                Editable Name = this.mAddFillType_Name.getText();
                if (!TextUtils.isEmpty(Name)) {
                    FillType createdFillType = mFillTypeDao.createFillType(
                            Name.toString()
                            Log.d(TAG, "added FillType : " + createdFillType.getName());
                    Intent intent = new Intent(this, ListFillTypeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, R.string.empty_fields_message, Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }
*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFillTypeDao.close();
    }
}

