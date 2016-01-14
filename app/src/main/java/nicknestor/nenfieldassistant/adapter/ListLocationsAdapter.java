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

    public ListLocationsAdapter(Context context, List<Location> listLocations) {
        this.setItems(listLocations);
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
            holder.txtStoreName = (TextView) v.findViewById(R.id.txt_storename);
            holder.txtStoreAbbr = (TextView) v.findViewById(R.id.txt_storeabbr);
            holder.txtStoreNumber = (TextView) v.findViewById(R.id.txt_storeid);
            holder.txtAddress = (TextView) v.findViewById(R.id.txt_address);
            holder.txtCity = (TextView) v.findViewById(R.id.txt_city);
            holder.txtPhone = (TextView) v.findViewById(R.id.txt_phone_number);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Location currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtStoreName.setText(currentItem.getStore() + " #" + currentItem.getStoreID());
            holder.txtStoreAbbr.setText(currentItem.getAbbr());
            holder.txtStoreNumber.setText(currentItem.getStoreID());
            holder.txtAddress.setText(currentItem.getAddress());
            holder.txtCity.setText(currentItem.getCity() + ", " + currentItem.getState() + " " + currentItem.getZip());
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
        TextView txtPhone;
    }

}
