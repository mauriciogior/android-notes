package com.rosanarogiski.notes.adapter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.rosanarogiski.notes.NewNoteActivity;
import com.rosanarogiski.notes.R;
import com.rosanarogiski.notes.util.BitmapCompressor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauricio on 4/24/15.
 */
public class PictureAdapter extends BaseAdapter {

    private List<Uri> dataSet = new ArrayList<>();
    private List<String> dataSetCurated = new ArrayList<>();
    private NewNoteActivity activity;

    public PictureAdapter(NewNoteActivity activity) {
        this.activity = activity;
    }

    public List<String> getDataSetCurated() {
        return dataSetCurated;
    }

    public List<Uri> getDataSet() {
        return dataSet;
    }

    public void addToDataSet(Uri uri) {
        dataSet.add(uri);
        dataSetCurated.add(uri.toString());
    }

    public void setDataSet(List<Uri> uris, List<String> strings) {
        dataSet = uris;
        dataSetCurated = strings;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

        activity.onDataSizeChangeListener(dataSet.size());
    }

    @Override
    public int getCount() {
        return dataSet.size() + 1;
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
        public ImageView deleteButton;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.adapter_picture, null);

            viewHolder = new ViewHolder();

            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.deleteButton = (ImageView) convertView.findViewById(R.id.deleteButton);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == dataSet.size()) {

            viewHolder.image.setImageResource(android.R.drawable.ic_menu_add);
            viewHolder.deleteButton.setVisibility(View.GONE);

            viewHolder.deleteButton.setOnClickListener(null);

            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.askNewImage();
                }
            });

        } else {
            viewHolder.deleteButton.setVisibility(View.VISIBLE);
            viewHolder.image.setOnClickListener(null);

            viewHolder.deleteButton.setTag(position);
            viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();

                    dataSet.remove(position);
                    dataSetCurated.remove(position);
                    notifyDataSetChanged();
                }
            });

            BitmapCompressor.compressAndSet(dataSet.get(position),
                    viewHolder.image,
                    activity);
        }

        return convertView;
    }
}
