package com.example.kelvinbun.pipancaindra;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import uk.co.senab.photoview.PhotoViewAttacher;

public class DetailActivity extends AppCompatActivity {

    private int position;
    DataBaseHelper database;
    ImageView gambar;
    VideoView video;
    TextView penjelasan1;
    TextView penjelasan2;
    TextView penjelasan3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("Nilai");
            //Log.d("Sudah Pindah", String.valueOf(position));
        }

        try {
            database = new DataBaseHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gambar = (ImageView) findViewById(R.id.gambar);
        PhotoViewAttacher photoView = new PhotoViewAttacher(gambar);
        video = (VideoView) findViewById(R.id.video);
        penjelasan1 = (TextView) findViewById(R.id.penjelasan1);
        penjelasan2 = (TextView) findViewById(R.id.penjelasan2);
        penjelasan3 = (TextView) findViewById(R.id.penjelasan3);
        video.setMediaController(new MediaController(this));
        Uri  uri =  Uri.parse("android.resource://"+getPackageName()+"/"+getResourceId(database.getpancaindra(position).get(4),R.raw.class));
        video.setVideoURI(uri);
        //digunakan untuk mengidentifikasi resource seperti lokasi video
        video.requestFocus();
        //video.start();

        try {
            // get input stream
            InputStream ims = getAssets().open("pancaindra/" + database.getpancaindra(position).get(0));
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView8
            gambar.setImageDrawable(d);

        } catch (IOException ex) {
            return;
        }
        penjelasan1.setText(database.getpancaindra(position).get(1));
        penjelasan2.setText(database.getpancaindra(position).get(2));
        penjelasan3.setText(database.getpancaindra(position).get(3));

    }
    @SuppressWarnings("rawtypes")
    public static int getResourceId(String name, Class resType) {

        try {
            Class res = null;
            if (resType == R.drawable.class)
                res = R.drawable.class;
            if (resType == R.id.class)
                res = R.id.class;
            if (resType == R.string.class)
                res = R.string.class;
            if (resType == R.raw.class)
                res = R.raw.class;
            Field field = res.getField(name);
            int retId = field.getInt(null);
            return retId;
        } catch (Exception e) {
            // Log.d(TAG, "Failure to get drawable id.", e);
        }
        return 0;
    }

}
