package com.rosanarogiski.notes;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;

import java.io.IOException;

/**
 * Created by mauricio on 4/24/15.
 */
public class NotePictureActivity extends ActionBarActivity {

    private String imageSrc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_picture);

        getSupportActionBar().hide();

        imageSrc = "-1";

        if (savedInstanceState != null) {
            imageSrc = savedInstanceState.getString("imageSrc", "-1");
        } else {
            imageSrc = getIntent().getExtras().getString("imageSrc", "-1");
        }

        if (imageSrc.equals("-1")) {
            finish();
            return;
        }

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(imageSrc));

            ((com.polites.android.GestureImageView) findViewById(R.id.image)).setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("imageSrc", imageSrc);
    }
}
