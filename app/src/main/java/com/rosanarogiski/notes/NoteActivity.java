package com.rosanarogiski.notes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.meetme.android.horizontallistview.HorizontalListView;
import com.rosanarogiski.notes.adapter.NotePictureAdapter;
import com.rosanarogiski.notes.bean.Note;

import java.util.Date;

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
        getSupportActionBar().setTitle("Note");

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

        note.setVisualized(true);
        note.setTimestamp(new Date().getTime());
        note.save();

        TextView noteTitle = (TextView) findViewById(R.id.noteTitle);
        HorizontalListView noteImages = (HorizontalListView) findViewById(R.id.noteImages);
        RatingBar noteRating = (RatingBar) findViewById(R.id.noteRating);
        TextView noteAuthor = (TextView) findViewById(R.id.noteAuthor);
        TextView noteSubject = (TextView) findViewById(R.id.noteSubject);
        TextView noteCourse = (TextView) findViewById(R.id.noteCourse);
        TextView noteTags = (TextView) findViewById(R.id.noteTags);

        notePictureAdapter = new NotePictureAdapter(this);
        noteImages.setAdapter(notePictureAdapter);

        noteImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NotePictureAdapter.ViewHolder viewHolder = (NotePictureAdapter.ViewHolder) view.getTag();

                Intent intent = new Intent(NoteActivity.this, NotePictureActivity.class);
                intent.putExtra("imageSrc", viewHolder.imageSrc.toString());

                startActivity(intent);
            }
        });

        noteRating.setRating(note.getRating());

        noteRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                note.setRating(rating);
                note.save();
            }
        });

        for (String imageSrc : note.getImagesSrc()) {
            notePictureAdapter.addToDataSet(Uri.parse(imageSrc));
        }

        noteAuthor.setText(note.getAuthor());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.action_download:
                note.setDownload(true);
                note.save();
                Toast.makeText(this, "Note downloaded successfully!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);

                shareIntent.setType("plain/text");
                shareIntent.putExtra(Intent.EXTRA_TEXT, note.getTitle());
                startActivity(Intent.createChooser(shareIntent, "Share using"));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
