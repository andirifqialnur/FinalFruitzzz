package com.example.fruitzzz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomeInfoAdapter extends RecyclerView.Adapter<HomeInfoAdapter.InfoViewHolder> {
    List<HomeInfo> mDataInfo;
    Context mContext;

    public HomeInfoAdapter(Context context, List<HomeInfo> DataInfo){
        mDataInfo = DataInfo;
        mContext = context;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.info_lay, parent, false);
        InfoViewHolder infoViewHolder = new InfoViewHolder(view);
        return infoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.Titleee.setText(mDataInfo.get(position).getTitleInfo());
        holder.Authorrr.setText(mDataInfo.get(position).getAuthor());
        Glide.with(mContext).asBitmap().load(mDataInfo.get(position).getImage()).into(holder.Imageee);
        Glide.with(mContext).asBitmap().load(mDataInfo.get(position).getViewAll()).into(holder.ViewAlll);

        holder.ViewAlll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mDataInfo.get(position).getTitleInfo(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() { return mDataInfo.size(); }

    public class InfoViewHolder extends RecyclerView.ViewHolder{
        TextView Titleee, Authorrr;
        ImageView Imageee, ViewAlll;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            Titleee = itemView.findViewById(R.id.title);
            Authorrr = itemView.findViewById(R.id.author);
            Imageee = itemView.findViewById(R.id.image_back);
            ViewAlll = itemView.findViewById(R.id.view_all);
        }
    }
}