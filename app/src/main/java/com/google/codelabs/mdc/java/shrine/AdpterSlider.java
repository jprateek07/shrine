package com.google.codelabs.mdc.java.shrine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdpterSlider extends RecyclerView.Adapter<AdpterSlider.ViewHolder> {
private String[] android;
private Context context;

public AdpterSlider(Context context, String[] android) {
        this.android = android;
        this.context = context;
        }

@Override
public AdpterSlider.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sliderimage, viewGroup, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(AdpterSlider.ViewHolder viewHolder, int i) {


        Glide.with(context).load(android[i]).into(viewHolder.img_android);
        }

@Override
public int getItemCount() {
        return android.length;
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView tv_android;
    private ImageView img_android;
    public ViewHolder(View view) {
        super(view);
        img_android = (ImageView) view.findViewById(R.id.Sliderimg);

    }
}

}