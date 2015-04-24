package com.rosanarogiski.notes.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.rosanarogiski.notes.R;
import com.rosanarogiski.notes.util.BitmapCompressor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauricio on 4/24/15.
 */
public class NotePictureAdapter extends BaseAdapter {

    private List<Uri> dataSet = new ArrayList<>();
    private Activity activity;

    public NotePictureAdapter(Activity activity) {
        this.activity = activity;
    }

    public void addToDataSet(Uri uri) {
        dataSet.add(uri);

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        public ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.adapter_note_picture, null);

            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BitmapCompressor.compressAndSet(dataSet.get(position),
                viewHolder.image,
                activity);

        return convertView;
    }
}
