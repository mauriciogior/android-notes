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

import java.util.List;

/**
 * Created by mauricio on 4/21/15.
 */
public class NoteListFragment extends Fragment {

    private ListView listView;
    private NoteAdapter listAdapter;

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

        Note note = new Note(getActivity());

        List<Note> noteList = note.findAll();

        listAdapter.setDataList(noteList);

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
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
