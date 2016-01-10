package nicknestor.nenfieldassistant.activities;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import nicknestor.nenfieldassistant.R;

/**
 * Created by Nick on 12/18/2015.
 */
public class AddLocationActivity extends Activity implements OnClickListener {
    private EditText mAddLocation_StoreName;
    private EditText mAddLocation_StoreAbbr;
    private EditText mAddLocation_StoreId;
    private EditText mAddLocation_Address;
    private EditText mAddLocation_City;
    private EditText mAddLocation_State;
    private EditText mAddLocation_Zip;
    private EditText mAddLocation_PhoneNumber;
    private Button mBtn_Add_Location;

    private void initView() {
        this.mAddLocation_StoreName = (EditText) findViewById(R.id.AddLocation_StoreName);
        this.mAddLocation_StoreAbbr = (EditText) findViewById(R.id.AddLocation_StoreAbbr);
        this.mAddLocation_StoreId = (EditText) findViewById(R.id.AddLocation_StoreId);
        this.mAddLocation_Address = (EditText) findViewById(R.id.AddLocation_Address);
        this.mAddLocation_City = (EditText) findViewById(R.id.AddLocation_City);
        this.mAddLocation_State = (EditText) findViewById(R.id.AddLocation_State);
        this.mAddLocation_Zip = (EditText) findViewById(R.id.AddLocation_Zip);
        this.mAddLocation_PhoneNumber = (EditText) findViewById(R.id.AddLocation_PhoneNumber);
        this.mBtn_Add_Location = (Button) findViewById(R.id.Btn_Add_Location);

        this.mBtn_Add_Location.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Btn_Add_Location:
                Editable StoreName = mAddLocation_StoreName.getText();
                Editable StoreAbbr = mAddLocation_StoreAbbr.getText();
                Editable StoreNumber = mAddLocation_StoreId.getText();
                Editable Address = mAddLocation_Address.getText();
                Editable City = mAddLocation_City.getText();
                Editable State = mAddLocation_State.getText();
                Editable Zip = mAddLocation_Zip.getText();
                Editable PhoneNumber = mAddLocation_PhoneNumber.getText();
                if (!TextUtils.isEmpty(StoreName) && !TextUtils.isEmpty(StoreNumber)) {

                }
        }

        }
}
