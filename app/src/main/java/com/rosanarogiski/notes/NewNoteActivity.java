package com.rosanarogiski.notes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.rosanarogiski.notes.fragment.NewNoteFragment;
import com.rosanarogiski.notes.fragment.PictureSelectorFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by mauricio on 4/21/15.
 */
public class NewNoteActivity extends ActionBarActivity {

    private PictureSelectorFragment pictureSelectorFragment;
    private NewNoteFragment newNoteFragment;
    private Button continueButton;
    private Button cancelButton;

    private boolean inPictureSelector = true;

    private List<String> selectedImages;
    private List<Uri> selectedImagesForGrid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_note);

        getSupportActionBar().setTitle("New Note");

        continueButton = (Button) findViewById(R.id.continueButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        continueButton.setEnabled(false);

        pictureSelectorFragment = new PictureSelectorFragment();
        newNoteFragment = new NewNoteFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, pictureSelectorFragment).commitAllowingStateLoss();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inPictureSelector) {
                    finish();
                } else {
                    cancelButton.setText("Cancel");
                    continueButton.setText("Continue");

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, pictureSelectorFragment)
                            .commitAllowingStateLoss();

                    pictureSelectorFragment.pictureAdapter.setDataSet(selectedImagesForGrid, selectedImages);
                    pictureSelectorFragment.pictureAdapter.notifyDataSetChanged();

                    inPictureSelector = true;
                }
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inPictureSelector) {
                    selectedImages = pictureSelectorFragment.pictureAdapter.getDataSetCurated();
                    selectedImagesForGrid = pictureSelectorFragment.pictureAdapter.getDataSet();

                    continueButton.setText("Finish");
                    cancelButton.setText("Back");

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, newNoteFragment)
                            .commitAllowingStateLoss();

                    inPictureSelector = false;
                } else {
                    if (newNoteFragment.addNote(selectedImages)) {
                        finish();
                    }
                }
            }
        });

        askNewImage();
    }

    public void onDataSizeChangeListener(int size) {
        if (size == 0) {
            continueButton.setEnabled(false);
        } else {
            continueButton.setEnabled(true);
        }
    }

    public void askNewImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Intent chooser = Intent.createChooser(intent, "Select Picture");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { takePhotoIntent });

        startActivityForResult(chooser, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && data != null) {
            Uri selectedImage = null;

            if (data.getData() != null) {
                selectedImage = data.getData();
            } else {
                try {
                    Bitmap img = (Bitmap) data.getExtras().get("data");

                    if (img == null) {
                        finish();
                        return;
                    }

                    String path = Environment.getExternalStorageDirectory().toString();
                    File file = new File(path, "notes" + String.valueOf(new Date().getTime()) + ".jpg"); // the File to save to
                    OutputStream fOut = new FileOutputStream(file);

                    img.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
                    fOut.flush();
                    fOut.close(); // do not forget to close the stream

                    MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());

                    selectedImage = Uri.fromFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    return;
                }
            }

            if (selectedImage != null) {
                pictureSelectorFragment.addImage(selectedImage);
            } else {
                return;
            }
        } else {
            return;
        }
    }

}
