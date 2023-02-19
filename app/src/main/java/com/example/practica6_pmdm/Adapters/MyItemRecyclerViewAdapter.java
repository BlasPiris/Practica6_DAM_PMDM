package com.example.practica6_pmdm.Adapters;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practica6_pmdm.Activities.MainActivity;
import com.example.practica6_pmdm.Activities.MediaActivity;
import com.example.practica6_pmdm.Pojos.Resource;
import com.example.practica6_pmdm.R;
import com.example.practica6_pmdm.databinding.FragmentItemBinding;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {


    //VARIABLES DEL ADAPTER
    private final ArrayList<Resource> mValues;
    private Boolean[] settings;
    private boolean isPlaying;
    private  MediaPlayer md;
    private Context c;
    private MainActivity ma;
    private ArrayList<ImageView> playButton;
    private int lastId;



    public MyItemRecyclerViewAdapter(ArrayList<Resource> resources, Boolean[] settings,MainActivity ma) {
        mValues = resources;
        this.settings=settings;
        isPlaying=false;
        this.c=ma.getApplicationContext();
        playButton=new ArrayList<>();
        lastId=-1;
        this.ma=ma;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int val= Integer.parseInt(mValues.get(position).getType());
            holder.artistResource.setText(mValues.get(position).getName());
            holder.titleResource.setText(mValues.get(position).getDescription());
            holder.imageResource.setImageBitmap(mValues.get(position).getImage());
            holder.typeResource.setImageResource(getImageType(mValues.get(position).getType()));

            //AL PULSAR EL BOTON DE PLAY, IREMOS AL METODO QUE ELIGE EL TIPO DE REPRODUCCION
            holder.playResource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playResource(mValues.get(position),holder.playResource,view);
                }
            });

            //GUARDAMOS LOS BOTONES DE PLAY EN UN ARRAYLIST
            playButton.add(holder.playResource);

            //SOLO MOSTRAREMOS LOS RECURSOS QUE TENGAMOS SELECCIONADOS EN LA CONFIGURACION
        if(!settings[val]){
            holder.cardView.setVisibility(View.GONE);
        }



    }


//METODO QUE DEVOLVERÁ EL ICONO CORRESPONDIENTE DEPENDIENDO DEL TIPO DE RECURSO
    private int getImageType(String type) {
        if(type.equals("0")){
            return R.drawable.audio;
        }else if(type.equals("1")){
            return R.drawable.video;
        }else if(type.equals("2")){
            return R.drawable.streaming;
        }else{
            return 0;
        }
    }

//METODO QUE GESTIONARÁ LA REPRODUCCION DE CADA TIPO DE RECURSO
    private void playResource(Resource resource, ImageView playResource, View view) {
        int resID = c.getResources().getIdentifier(resource.getUri(), "raw",c.getPackageName());
        if(resource.getType().equals("0")) {
            musicPlayer(playResource,resID);
        }else{
            if(md!=null && md.isPlaying()){
                md.stop();
                setAllResourceStopButton();
            }
            Intent intent = new Intent(c, MediaActivity.class);
            intent.putExtra("Uri", resID);
            intent.putExtra("link",resource.getUri());
            intent.putExtra("type",resource.getType());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(intent);
        }
    }

    //METODO QUE GESTIONA LA REPRODUCCION DE LA MUSICA
    private void musicPlayer(ImageView playResource,int resID){
        if(md==null || !md.isPlaying()){
            setResourcePlayMusic(playResource,resID);
            playResource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(lastId!=resID){
                        setResourceStopMusic(playResource,resID);
                        setResourcePlayMusic(playResource,resID);
                    }else{
                        if(md==null || !md.isPlaying()){
                            setResourcePlayMusic(playResource,resID);
                        }else{
                            setResourceStopMusic(playResource,resID);
                        }
                    }
                }
            });
        }else{
            setResourceStopMusic(playResource,resID);
            if(lastId!=resID){
                setResourcePlayMusic(playResource,resID);
            }
        }
    }

    //METODO QUE REPRODUCE UNA CANCION
    private void setResourcePlayMusic(ImageView playResource,int id){
        playResource.setImageResource(R.drawable.ic_baseline_stop_24);
        md=MediaPlayer.create(playResource.getContext(),id);
        ma.setMd(md);
        lastId=id;
        md.start();
    }

    //METODO QUE PARA UNA CANCIÓN
    private void setResourceStopMusic(ImageView playResource,int id){
        md.stop();
        setAllResourceStopButton();
    }

    //METODO QUE DEVUELVE A TODOS LOS BOTONES A LA POSICION DE PLAY
    private void setAllResourceStopButton(){
        for (ImageView imageView: playButton) {
            imageView.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        }
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageResource, typeResource,playResource;
        TextView artistResource,titleResource;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            cardView=binding.cardView;
            imageResource=binding.imageResource;
            typeResource=binding.TypeResource;
            playResource=binding.playResource;
            artistResource=binding.ArtistResource;
            titleResource=binding.TitleResource;
        }
    }

}