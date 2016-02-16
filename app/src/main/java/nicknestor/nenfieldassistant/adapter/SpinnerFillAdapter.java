package nicknestor.nenfieldassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.model.Fill;

public class SpinnerFillAdapter extends BaseAdapter {

    public static final String TAG = "SpinnerFillsAdapter";

    private List<Fill> mItems;
    private LayoutInflater mInflater;

    public SpinnerFillAdapter(Context context, List<Fill> listFills) {
        this.setItems(listFills);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Fill getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getFillId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.spinner_item_fill, parent, false);
            holder = new ViewHolder();
            holder.txtFill = (TextView) v.findViewById(R.id.txt_spinner_fill);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Fill currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtFill.setText(currentItem.getFill());
        }

        return v;
    }

    public List<Fill> getItems() {
        return mItems;
    }

    public void setItems(List<Fill> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtFill;
    }
}
