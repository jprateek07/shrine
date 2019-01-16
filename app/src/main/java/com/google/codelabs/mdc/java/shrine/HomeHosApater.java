package com.google.codelabs.mdc.java.shrine;


import android.content.Context;
import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeHosApater extends RecyclerView.Adapter<HomeHosApater.ViewHolder> {
    private ArrayList<HospitalData> android;
    private Context context;

    public HomeHosApater(Context context, ArrayList<HospitalData> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public HomeHosApater.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hospitalforhome, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeHosApater.ViewHolder viewHolder, int i) {

        viewHolder.name.setText(android.get(i).getName());
        Log.i("Image",android.get(i).getImg());
        viewHolder.rate.setRating(android.get(i).getRating());
        viewHolder.distance.setText(android.get(i).getKm());
        Glide.with(context).load(android.get(i).getImg()).into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,distance;
        RatingBar rate;
        private CircleImageView img_android;
        public ViewHolder(View view) {
            super(view);

            name = (TextView)view.findViewById(R.id.name);
            distance = (TextView)view.findViewById(R.id.distance);
            rate = (RatingBar) view.findViewById(R.id.rating);
            img_android = (CircleImageView) view.findViewById(R.id.img);

        }
    }

}