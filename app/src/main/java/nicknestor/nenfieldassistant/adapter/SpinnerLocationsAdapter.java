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

public class SpinnerLocationsAdapter extends BaseAdapter {

    public static final String TAG = "SpinnerLocationsAdapter";

    private List<Location> mItems;
    private LayoutInflater mInflater;

    public SpinnerLocationsAdapter(Context context, List<Location> listLocations) {
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
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getLocationId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.spinner_item_location, parent, false);
            holder = new ViewHolder();
            holder.txtStoreName = (TextView) v.findViewById(R.id.txt_spinner_location);
             holder.txtAddress = (TextView) v.findViewById(R.id.txt_spinner_address);
            holder.txtCityStateZip = (TextView) v.findViewById(R.id.txt_spinner_citystatezip);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Location currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtStoreName.setText(currentItem.getStore() + " #" + currentItem.getStoreID());
            holder.txtAddress.setText(currentItem.getAddress());
            holder.txtCityStateZip.setText(currentItem.getCity() + ", " + currentItem.getState() + " " + currentItem.getZip());
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
        TextView txtStoreId;
        TextView txtAddress;
        TextView txtCityStateZip;
    }
}
