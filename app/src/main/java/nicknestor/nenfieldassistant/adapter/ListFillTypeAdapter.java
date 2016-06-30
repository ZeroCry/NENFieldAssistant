package nicknestor.nenfieldassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.model.FillType;


/**
 * Created by Nick on 12/19/2015.
 */
public class ListFillTypeAdapter extends BaseAdapter {

    public static final String TAG = "ListFillTypesAdapter";

    private List<FillType> mItems;
    private LayoutInflater mInflater;

    public ListFillTypeAdapter(Context context, List<FillType> listFillTypes) {
        this.setItems(listFillTypes);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public FillType getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getFillTypeId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_filltype, parent, false);
            holder = new ViewHolder();
            holder.txtFillTypeName = (TextView) v.findViewById(R.id.txt_FillTypename);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        FillType currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtFillTypeName.setText(currentItem.getFillTypeName());
        }

        return v;
    }

    public List<FillType> getItems() {
        return mItems;
    }

    public void setItems(List<FillType> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtFillTypeName;
    }

}
