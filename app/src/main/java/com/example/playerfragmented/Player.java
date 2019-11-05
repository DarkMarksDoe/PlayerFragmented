package com.example.playerfragmented;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player extends AppCompatActivity {
    ImageButton next, preview, play;
    ProgressBar musicBar;
    TextView start, end, songName;
    ImageView album;
    private MediaPlayer medPlay;
    String path;
    int id = 0;
    ArrayList<Song> listDatos = new ArrayList<>();


    @Override
    protected void onStop() {
        super.onStop();
        if (medPlay.isPlaying()){
            medPlay.stop();
            try {
                medPlay.prepare();
            }catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(medPlay!=null){
            if (!medPlay.isPlaying()){
                medPlay.start();
                medPlay.setLooping(true);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (medPlay.isPlaying()){
            medPlay.pause();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (medPlay.isPlaying()){
            medPlay.stop();
            try {
                medPlay.prepare();
            }catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        startControls();
        getMessages();
        actions();
        playing();
    }

    private void actions() {
        songName.setText(listDatos.get(id).title);
        play.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                try {
                    if (medPlay != null) {
                        //First Time
                        playing();

                    }
                    {
                    }
                } catch (Exception e) {
                    Toast.makeText(Player.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id++;
                if (id == listDatos.size()) {
                    id = 0;
                }
                playing();
            }
        });

        //Preview button
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id--;
                if (id < 0) {
                    id = listDatos.size() - 1;
                }
                playing();
            }
        });


    }

    private void getMessages() {
        try {
            Intent intent = getIntent();
            path  = intent.getStringExtra("path");
            id = intent.getIntExtra("id",id);
          listDatos = (ArrayList<Song>) getIntent().getSerializableExtra("song");
        }catch (Exception e){
           Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void startControls() {
        songName = findViewById(R.id.txtSongName);
        next = findViewById(R.id.next_song);
        preview = findViewById(R.id.preview_song);
        play = findViewById(R.id.play_song);
        musicBar = findViewById(R.id.size_audio);
        start = findViewById(R.id.textIn);
        end = findViewById(R.id.textOut);
        album = findViewById(R.id.image_Album);
    }
    private void playing() {
        try {
            if (medPlay != null) {
                medPlay.release();
            }
            medPlay = new MediaPlayer();
            medPlay.setAudioStreamType(AudioManager.STREAM_MUSIC);
            medPlay.setDataSource(listDatos.get(id).getPath());
            medPlay.prepare();
            updatePlayer(medPlay.getDuration());
            medPlay.seekTo(0);
            songName.setText(listDatos.get(id).getTitle());
            medPlay.start();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void updatePlayer(int currentDuration){
        //End Text
        long min = TimeUnit.MILLISECONDS.toMinutes(currentDuration);
        long sec = TimeUnit.MILLISECONDS.toSeconds(currentDuration-TimeUnit.MINUTES.toMillis(currentDuration));
        end.setText(min+":"+sec);
    }
}
