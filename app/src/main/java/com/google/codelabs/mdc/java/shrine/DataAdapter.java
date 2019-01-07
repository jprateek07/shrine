package com.google.codelabs.mdc.java.shrine;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<DepartmentCategory> android;
    private Context context;

    public DataAdapter(Context context,ArrayList<DepartmentCategory> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardforcategory, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(android.get(i).getName());
        Log.i("Image",android.get(i).imagePath);

        Glide.with(context).load(android.get(i).getImagePath()).into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_android;
        private CircleImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (CircleImageView) view.findViewById(R.id.img_android);

        }
    }

}