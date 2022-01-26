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

public class InfoPageAdapter extends RecyclerView.Adapter<InfoPageAdapter.InfoPageViewHolder> {

    List<InfoPages> mInfoPages;
    Context mContext;

    public InfoPageAdapter(Context context, List<InfoPages> DataInfoPage){
        mInfoPages = DataInfoPage;
        mContext = context;
    }

    @NonNull
    @Override
    public InfoPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_info, parent, false);
        InfoPageViewHolder infoPageViewHolder = new InfoPageViewHolder(view);
        return infoPageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoPageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.Titlee.setText(mInfoPages.get(position).getTitleInfo());
        holder.Authorr.setText(mInfoPages.get(position).getAuthor());
        Glide.with(mContext).asBitmap().load(mInfoPages.get(position).getImage()).into(holder.Imagee);
        Glide.with(mContext).asBitmap().load(mInfoPages.get(position).getViewAll()).into(holder.ViewAll);

        holder.ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mInfoPages.get(position).getTitleInfo(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInfoPages.size();
    }

    public class InfoPageViewHolder  extends RecyclerView.ViewHolder{
            TextView Titlee, Authorr;
            ImageView Imagee, ViewAll;
        public InfoPageViewHolder(@NonNull View itemView) {
            super(itemView);
            Titlee = itemView.findViewById(R.id.title);
            Authorr = itemView.findViewById(R.id.author);
            Imagee = itemView.findViewById(R.id.image_back);
            ViewAll = itemView.findViewById(R.id.view_all);
        }
    }
}
