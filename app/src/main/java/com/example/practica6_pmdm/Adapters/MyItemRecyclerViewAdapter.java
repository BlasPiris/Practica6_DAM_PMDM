package com.example.practica6_pmdm.Adapters;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practica6_pmdm.Pojos.Resource;
import com.example.practica6_pmdm.R;
import com.example.practica6_pmdm.databinding.FragmentItemBinding;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Resource> mValues;
    private Boolean[] settings;


    public MyItemRecyclerViewAdapter(ArrayList<Resource> resources) {
        mValues = resources;
    }

    public MyItemRecyclerViewAdapter(ArrayList<Resource> resources, Boolean[] settings) {
        mValues = resources;
        this.settings=settings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int val= Integer.parseInt(mValues.get(position).getType());



            holder.artistResource.setText(mValues.get(position).getName());
            holder.titleResource.setText(mValues.get(position).getDescription());
            holder.imageResource.setImageBitmap(mValues.get(position).getImage());
            holder.typeResource.setImageResource(getImageType(mValues.get(position).getType()));

            holder.playResource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    playResource(mValues.get(position));

                }
            });

        if(!settings[val]){
            holder.cardView.setVisibility(View.GONE);
        }



    }



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


    private void playResource(Resource resource) {
        if(resource.getType().equals("0")){

        }else if(resource.getType().equals("1")){

        }else if(resource.getType().equals("2")){

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