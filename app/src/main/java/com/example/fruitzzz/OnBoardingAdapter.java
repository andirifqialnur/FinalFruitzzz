package com.example.fruitzzz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder> {

    private List<OnBoardingItem> onBoardingItemList;

    public OnBoardingAdapter(List<OnBoardingItem> onBoardingItemList) {
        this.onBoardingItemList = onBoardingItemList;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_lay, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItemList.size();
    }

    class OnBoardingViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView description;
        private ImageView background;

        OnBoardingViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.id_title);
            description = itemView.findViewById(R.id.id_desc);
            background = itemView.findViewById(R.id.image_background);
        }
        void setOnBoardingData(OnBoardingItem onBoardingItem){
            title.setText(onBoardingItem.getTitle());
            description.setText(onBoardingItem.getDescription());
            background.setImageResource(onBoardingItem.getImage());
        }
    }

}
