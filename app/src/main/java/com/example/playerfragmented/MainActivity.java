package com.example.playerfragmented;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler;
    ArrayList<Song> listDatos = new ArrayList<>();
    int id = 0;
    private static final int MY_PERMISSION_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        permissions();
        AdapterSongs adapterSongs = new AdapterSongs(listDatos);
        recycler.setAdapter(adapterSongs);
        adapterSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int g = v.getId();
                    Bundle bundle = new Bundle();
                    Intent intentVid = new Intent(MainActivity.this, Player.class);
                    intentVid.putExtra("path",listDatos.get(recycler.getChildAdapterPosition(v)).getPath());
                    int id = listDatos.get(recycler.getChildAdapterPosition(v)).getId();
                    intentVid.putExtra("id",id);
                    bundle.putSerializable("song",listDatos);
                    intentVid.putExtras(bundle);
                    startActivity(intentVid);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void permissions() {
        try {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
                }
            }else{
                getMusic();
            }

        }catch (Exception e ){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void getMusic(){
        try {
            ContentResolver contentResolver = getContentResolver();
            Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Cursor songCursor = contentResolver.query(songUri,null,null,null);
            if(songCursor != null && songCursor.moveToFirst()){
                int songTitle =  songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                int songArtist =  songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                int songPath =  songCursor.getColumnIndex((MediaStore.Audio.Media.DATA));
                do {
                    Song song = new Song();
                    song.title = songCursor.getString(songTitle);
                    song.artist= songCursor.getString(songArtist);
                    song.path= songCursor.getString(songPath);
                    song.id= id;
                    listDatos.add(song);
                    id++;
                }while (songCursor.moveToNext());
            }

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
