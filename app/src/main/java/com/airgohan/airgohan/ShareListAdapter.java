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

import org.json.JSONObject;

import java.util.List;

/**
 * Created by masaya3 on 15/08/29.
 */
public class ShareListAdapter extends ArrayAdapter<JSONObject> {

    private static class ViewHolder{
        public ImageView gohanImage;
        public ImageView hostImage;
        public TextView hostName;
    }

    /** */
    private LayoutInflater mInflater;
    /** */
    private int mResource;

    public ShareListAdapter(Context context, int resource, int textViewResourceId, List<JSONObject> objects) {
        super(context, resource, textViewResourceId, objects);
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
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }

}
