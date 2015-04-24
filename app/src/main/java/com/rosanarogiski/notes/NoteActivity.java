package com.rosanarogiski.notes;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.meetme.android.horizontallistview.HorizontalListView;
import com.rosanarogiski.notes.adapter.NotePictureAdapter;
import com.rosanarogiski.notes.bean.Note;

/**
 * Created by mauricio on 4/24/15.
 */
public class NoteActivity extends ActionBarActivity {

    private Note note;
    private NotePictureAdapter notePictureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String noteID = "-1";

        if (savedInstanceState != null) {
            noteID = savedInstanceState.getString("id", "-1");
        } else {
            noteID = getIntent().getExtras().getString("id", "-1");
        }

        if (noteID.equals("-1")) {
            finish();
            return;
        }

        note = new Note(this).find(noteID);

        TextView noteTitle = (TextView) findViewById(R.id.noteTitle);
        HorizontalListView noteImages = (HorizontalListView) findViewById(R.id.noteImages);
        TextView noteSubject = (TextView) findViewById(R.id.noteSubject);
        TextView noteCourse = (TextView) findViewById(R.id.noteCourse);
        TextView noteTags = (TextView) findViewById(R.id.noteTags);

        notePictureAdapter = new NotePictureAdapter(this);
        noteImages.setAdapter(notePictureAdapter);

        for (String imageSrc : note.getImagesSrc()) {
            notePictureAdapter.addToDataSet(Uri.parse(imageSrc));
        }

        noteTitle.setText(note.getTitle());
        noteSubject.setText(note.getSubject());
        noteCourse.setText(note.getCourse());
        noteTags.setText(note.getTags());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("id", note.getId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
