package com.rosanarogiski.notes.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rosanarogiski.notes.NewNoteActivity;
import com.rosanarogiski.notes.R;
import com.rosanarogiski.notes.adapter.NoteAdapter;
import com.rosanarogiski.notes.bean.Note;

import java.util.Collections;
import java.util.List;

/**
 * Created by mauricio on 4/21/15.
 */
public class NoteListFragment extends Fragment {

    private ListView listView;
    private NoteAdapter listAdapter;

    private int type;

    public final static int ALL = 1;
    public final static int RECENT = 2;
    public final static int UPLOAD = 3;
    public final static int DOWNLOAD = 4;

    public NoteListFragment() {}

    public NoteListFragment(int type) {
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null);

        listView = (ListView) rootView.findViewById(R.id.listView);

        listAdapter = new NoteAdapter(getActivity());
        listView.setAdapter(listAdapter);

        List<Note> noteList = new Note(getActivity()).findAll();

        Collections.sort(noteList);

        for (Note note : noteList) {
            switch (type) {
                case ALL:
                    listAdapter.addToDataList(note);

                    break;

                case RECENT:
                    if (note.isVisualized()) {
                        listAdapter.addToDataList(note);
                    }

                    break;

                case UPLOAD:
                    if (note.isUpload()) {
                        listAdapter.addToDataList(note);
                    }

                    break;

                case DOWNLOAD:
                    if (note.isDownload()) {
                        listAdapter.addToDataList(note);
                    }

                    break;
            }
        }

        listAdapter.notifyDataSetChanged();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_add_note:
                Intent intent = new Intent(getActivity(), NewNoteActivity.class);
                getActivity().startActivityForResult(intent, 10);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
