package com.example.fruitzzz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FruitInfoPageAdapter extends ArrayAdapter<String> {

    private final Activity context;
//    private ArrayList<String> ID;
    private ArrayList<String> FruitName;
    private ArrayList<String> Description;
    private ArrayList<String> Mineral;
    private ArrayList<String> VitC;
    private ArrayList<String> VitA;
    private ArrayList<String> Photo;

    public FruitInfoPageAdapter(Activity context, ArrayList<String> fruitName, ArrayList<String> description, ArrayList<String> mineral, ArrayList<String> vitc, ArrayList<String> vita,  ArrayList<String> photo){
        super(context, R.layout.fruit_lay_info, fruitName);
        this.context = context;
//        this.ID = id;
        this.FruitName = fruitName;
        this.Description = description;
        this.Mineral = mineral;
        this.VitC = vitc;
        this.VitA = vita;
        this.Photo = photo;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.fruit_lay_info, null, true);

        TextView fruitName = rowView.findViewById(R.id.fruit_name);
        TextView mineral = rowView.findViewById(R.id.mineral);
        TextView vita = rowView.findViewById(R.id.vita);
        TextView vitc = rowView.findViewById(R.id.vitc);

        CircleImageView photo = rowView.findViewById(R.id.fruit_image);

        fruitName.setText(FruitName.get(position));
        mineral.setText(Mineral.get(position));
        vita.setText(VitA.get(position));
        vitc.setText(VitC.get(position));

        Picasso.get().load("https://tkjb2019.com/mobile/api_kelompok_3/image/" + Photo.get(position)).into(photo);
        return rowView;
    }
}
