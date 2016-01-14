package nicknestor.nenfieldassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nicknestor.nenfieldassistant.R;
import nicknestor.nenfieldassistant.model.Area;

public class SpinnerAreasAdapter extends BaseAdapter {

    public static final String TAG = "SpinnerAreasAdapter";

    private List<Area> mItems;
    private LayoutInflater mInflater;

    public SpinnerAreasAdapter(Context context, List<Area> listAreas) {
        this.setItems(listAreas);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Area getItem(int position) {
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
            v = mInflater.inflate(R.layout.spinner_item_area, parent, false);
            holder = new ViewHolder();
            holder.txtArea = (TextView) v.findViewById(R.id.txt_spinner_area);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Area currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtArea.setText(currentItem.getArea());
        }

        return v;
    }

    public List<Area> getItems() {
        return mItems;
    }

    public void setItems(List<Area> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtArea;
    }
}
