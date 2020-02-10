package com.servicenowtask.app.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.servicenowtask.app.R;
import com.servicenowtask.app.ui.main.listener.OnItemClickListener;
import com.servicenowtask.app.ui.main.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FlickerAdapter extends RecyclerView.Adapter<FlickerAdapter.ViewHolder> {

    Context context;
    ArrayList<Photo> photoArrayList;
    private final OnItemClickListener listener;

    public FlickerAdapter(Context context, ArrayList<Photo> photosList,OnItemClickListener clickListener) {
        this.context = context;
        this.photoArrayList = photosList;
        listener = clickListener;
    }

    @NonNull
    @Override
    public FlickerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flicker_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickerAdapter.ViewHolder holder, int position) {
        Photo photo = photoArrayList.get(position);
        String url = "https://farm"+photo.farm+".static.flickr.com/"+photo.server+"/"+photo.id+"_"+photo.secret+".jpg";
        //https://farm66.static.flickr.com/65535/49515284107_fc7febfa79.jpg
        Picasso.get().load(url).into(holder.ivPhoto);
        holder.bind(photo, listener);
    }

    @Override
    public int getItemCount() {
        return photoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPhoto;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_flicker_icon);
        }

        void bind(final Photo photo, final OnItemClickListener listener) {
            ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(photo);
                }
            });
        }
    }
}
