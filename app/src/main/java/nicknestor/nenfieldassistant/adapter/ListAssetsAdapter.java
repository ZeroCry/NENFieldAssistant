package nicknestor.nenfieldassistant.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.model.Asset;

public class ListAssetsAdapter extends BaseAdapter {

    public static final String TAG = "ListAssetsAdapter";

    private List<Asset> mItems;
    private LayoutInflater mInflater;

    public ListAssetsAdapter(Context context, List<Asset> listAssets) {
        this.setItems(listAssets);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Asset getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getAssetId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_asset, parent, false);
            holder = new ViewHolder();
            holder.txtAssetNumber = (TextView) v.findViewById(R.id.txt_AssetNumber);
            holder.txtCategory = (TextView) v.findViewById(R.id.txt_Category);
            holder.txtMachineType = (TextView) v.findViewById(R.id.txt_MachineType);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Asset currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtAssetNumber.setText(currentItem.getAssetNumber());
            holder.txtCategory.setText(currentItem.getCategory());
            holder.txtMachineType.setText(currentItem.getMachineType());
        }

        return v;
    }

    public List<Asset> getItems() {
        return mItems;
    }

    public void setItems(List<Asset> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtAssetNumber;
        TextView txtCategory;
        TextView txtMachineType;
    }

}
