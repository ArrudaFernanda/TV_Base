package com.a7apps.tvbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.assistant.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class AdapRV extends RecyclerView.Adapter<AdapRV.HolderAdapRV>{
    private Context context;
    private ArrayList<String> dataArray;

    public AdapRV(Context context, ArrayList<String> dataArray) {
        this.context = context;
        this.dataArray = dataArray;
    }

    public static class HolderAdapRV extends RecyclerView.ViewHolder{
        ImageView imageView;

        public HolderAdapRV(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivItemRV);
        }
    }

    @NonNull
    @Override
    public HolderAdapRV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recyclerview, parent, false);
        return new HolderAdapRV(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdapRV holder, int position) {
        Glide.with(context).load(Constants.getIMAGE_URL()+dataArray.get(position)).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataArray.size();
    }
}
