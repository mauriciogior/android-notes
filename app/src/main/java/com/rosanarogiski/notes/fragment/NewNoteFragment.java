package com.rosanarogiski.notes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.rosanarogiski.notes.NewNoteActivity;
import com.rosanarogiski.notes.R;
import com.rosanarogiski.notes.bean.Note;

import java.util.Date;
import java.util.List;

/**
 * Created by mauricio on 4/24/15.
 */
public class NewNoteFragment extends Fragment {

    private NewNoteActivity activity;

    private EditText noteTitle;
    private EditText noteSubject;
    private EditText noteCourse;
    private EditText noteTags;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_note, null);

        activity = (NewNoteActivity) getActivity();

        noteTitle = (EditText) view.findViewById(R.id.noteTitle);
        noteSubject = (EditText) view.findViewById(R.id.noteSubject);
        noteCourse = (EditText) view.findViewById(R.id.noteCourse);
        noteTags = (EditText) view.findViewById(R.id.noteTags);

        return view;
    }

    public boolean addNote(List<String> imageList) {

        Note note = new Note(activity);

        note.setId(String.valueOf(new Date().getTime()));

        note.setTitle(noteTitle.getText().toString());
        note.setSubject(noteSubject.getText().toString());
        note.setCourse(noteCourse.getText().toString());
        note.setTags(noteTags.getText().toString());
        note.setImagesSrc(imageList);

        note.save();

        return true;
    }
}
