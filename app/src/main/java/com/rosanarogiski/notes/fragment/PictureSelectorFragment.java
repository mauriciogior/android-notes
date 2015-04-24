package com.rosanarogiski.notes.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.rosanarogiski.notes.NewNoteActivity;
import com.rosanarogiski.notes.R;
import com.rosanarogiski.notes.adapter.PictureAdapter;

/**
 * Created by mauricio on 4/23/15.
 */
public class PictureSelectorFragment extends Fragment {

    public PictureAdapter pictureAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_selector, null);

        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        pictureAdapter = new PictureAdapter((NewNoteActivity) getActivity());

        gridView.setAdapter(pictureAdapter);

        return view;
    }

    public void addImage(Uri path) {
        pictureAdapter.addToDataSet(path);

        pictureAdapter.notifyDataSetChanged();
    }

}
