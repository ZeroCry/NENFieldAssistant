package nicknestor.nenfieldassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.model.Location;


/**
 * Created by Nick on 12/19/2015.
 */
public class ListLocationsAdapter extends BaseAdapter {

    public static final String TAG = "ListLocationsAdapter";

    private List<Location> mItems;
    private LayoutInflater mInflater;

    public ListLocationsAdapter(Context context, List<Location> listCompanies) {
        this.setItems(listCompanies);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Location getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_location, parent, false);
            holder = new ViewHolder();
            holder.txtStoreName = (TextView) v.findViewById(R.id.AddLocation_StoreName);
            holder.txtStoreAbbr = (TextView) v.findViewById(R.id.AddLocation_StoreAbbr);
            holder.txtStoreNumber = (TextView) v.findViewById(R.id.AddLocation_StoreId);
            holder.txtAddress = (TextView) v.findViewById(R.id.AddLocation_Address);
            holder.txtCity = (TextView) v.findViewById(R.id.AddLocation_City);
            holder.txtState = (TextView) v.findViewById(R.id.AddLocation_State);
            holder.txtZip = (TextView) v.findViewById(R.id.AddLocation_Zip);
            holder.txtPhone = (TextView) v.findViewById(R.id.AddLocation_PhoneNumber);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Location currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtStoreName.setText(currentItem.getStore());
            holder.txtStoreAbbr.setText(currentItem.getAbbr());
            holder.txtStoreNumber.setText(currentItem.getStoreNumber());
            holder.txtAddress.setText(currentItem.getAddress());
            holder.txtCity.setText(currentItem.getCity());
            holder.txtState.setText(currentItem.getState());
            holder.txtZip.setText(currentItem.getZip());
            holder.txtPhone.setText(currentItem.getPhone());
        }

        return v;
    }

    public List<Location> getItems() {
        return mItems;
    }

    public void setItems(List<Location> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtStoreName;
        TextView txtStoreAbbr;
        TextView txtStoreNumber;
        TextView txtAddress;
        TextView txtCity;
        TextView txtState;
        TextView txtZip;
        TextView txtPhone;
    }

}
