package com.example.playerfragmented;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSongs extends RecyclerView.Adapter<AdapterSongs.ViewHolderSongs> implements View.OnClickListener {
    ArrayList<Song> listDatos;
    private View.OnClickListener listener;
    public AdapterSongs(ArrayList<Song> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderSongs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        view.setOnClickListener(this);
        return new ViewHolderSongs(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSongs holder, int position) {
    holder.asignSongs(listDatos.get(position));

    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    @Override
    public void onClick(View v) {
        if (listener!=null) {
            listener.onClick(v);
        }
    }

    public class ViewHolderSongs extends RecyclerView.ViewHolder {
        TextView idTitle;
        TextView idArtist;
        TextView idPath;
        View view;

        public ViewHolderSongs(@NonNull View itemView) {
            super(itemView);
            idTitle = itemView.findViewById(R.id.idTitle);
            idArtist = itemView.findViewById(R.id.idArtist);
            idPath = itemView.findViewById(R.id.idPath);
            view = itemView.findViewById(R.id.circuloSong);
        }

        public void asignSongs(Song datos) {
            idTitle.setText(datos.title);
            idArtist.setText(datos.artist);
            idPath.setText(datos.path);
            /**
            final int min = 0;
            final int max = 13;
            final int random = new Random().nextInt((max - min) + 1) + min;
            switch (random){
                case 1:
                    view.setBackgroundColor(R.string.blue);
                    break;
                case 2:
                    view.setBackgroundColor(R.string.indigo);
                    break;
                case 3:
                    view.setBackgroundColor(R.string.red);
                    break;
                case 4:
                    view.setBackgroundColor(R.string.green);
                    break;
                case 5:
                    view.setBackgroundColor(R.string.orange);
                    break;
                case 6:
                    view.setBackgroundColor(R.string.grey);
                    break;
                case 7:
                    view.setBackgroundColor(R.string.amber);
                    break;
                case 8:
                    view.setBackgroundColor(R.string.deeppurple);
                    break;
                case 9:
                    view.setBackgroundColor(R.string.bluegrey);
                    break;
                case 10:
                    view.setBackgroundColor(R.string.yellow);
                    break;
                case 11:
                    view.setBackgroundColor(R.string.cyan);
                    break;
                case 12:
                    view.setBackgroundColor(R.string.brown);
                    break;
                case 13:
                    view.setBackgroundColor(R.string.teal);
                    break;
            }
             */
        }
    }

}
