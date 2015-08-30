package com.airgohan.airgohan;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airgohan.airgohan.model.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by masaya3 on 15/08/29.
 */
public class ShareListAdapter extends ArrayAdapter<Event> {

    private static class ViewHolder{
        public ImageView gohanImage;
        public ImageView hostImage;
        public TextView hostName;
        public TextView mainText;
    }

    /** */
    private LayoutInflater mInflater;
    /** */
    private int mResource;

    public ShareListAdapter(Context context, int resource, List<Event> objects) {
        super(context, resource, objects);
        this.mResource = resource;
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Resources resources = getContext().getResources();

        if(convertView == null){
            convertView = this.mInflater.inflate(this.mResource, null);
            holder = new ViewHolder();

            holder.gohanImage = (ImageView)convertView.findViewById(R.id.foodImage);
            holder.hostImage = (ImageView)convertView.findViewById(R.id.hostImage);
            holder.hostName = (TextView)convertView.findViewById(R.id.hostText);
            holder.mainText = (TextView) convertView.findViewById(R.id.mainText);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        Event data = ((Event)getItem(position));
        //holder.gohanImage.setImageResource(R.mipmap.yo_eat);
        //holder.hostImage.setImageResource(R.mipmap.sample_host);
        holder.hostName.setText(data.getName() + "さん");

        holder.mainText.setVisibility(data.getMainMenu().isEmpty() ? View.INVISIBLE: View.VISIBLE);
        holder.mainText.setText(data.getMainMenu());

        return convertView;
    }

}
