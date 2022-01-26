package com.example.fruitzzz;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class FruitPage extends Fragment {

    View view;

    private int CurrentMineralProgress = 0;
    private int CurrentVitCProgress = 0;
    private int CurrentVitAProgress = 0;
    private int max = 100;

    ListView recyclerView;
    ProgressBar progressBarMineral, progressBarVitC, progressBarVitA;

    ArrayList<String> array_fruit, array_photo, array_id, array_mineral, array_vitc, array_vita;
    ProgressDialog progressDialog;
    String id, fruit, mineral, vitc, vita;

    Dialog dialog;

    ImageView apple, avocado, blueberry, dragon, grape, kiwi, lemon, mango, mangosteen, orange, pineapple, watermelon;

    public FruitPage() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fruitz_page, container, false);

        progressBarMineral = (ProgressBar) view.findViewById(R.id.prog_mineral);
        progressBarVitC = (ProgressBar) view.findViewById(R.id.prog_vitc);
        progressBarVitA = (ProgressBar) view.findViewById(R.id.prog_vita);

        apple = (ImageView) view.findViewById(R.id.apple);
        avocado = (ImageView) view.findViewById(R.id.avocado);
        blueberry = (ImageView) view.findViewById(R.id.blueberry);
        dragon = (ImageView) view.findViewById(R.id.dragon);
        grape = (ImageView) view.findViewById(R.id.grape);
        kiwi = (ImageView) view.findViewById(R.id.kiwi);
        lemon = (ImageView) view.findViewById(R.id.lemon);
        mango = (ImageView) view.findViewById(R.id.mango);
        mangosteen = (ImageView) view.findViewById(R.id.mangosteen);
        orange = (ImageView) view.findViewById(R.id.orange);
        pineapple = (ImageView) view.findViewById(R.id.pineapple);
        watermelon = (ImageView) view.findViewById(R.id.watermelon);

        dialog = new Dialog(getActivity());

        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 10;
                CurrentVitCProgress += 3;
                CurrentVitAProgress += 5;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        avocado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 7;
                CurrentVitCProgress += 1;
                CurrentVitAProgress += 2;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        blueberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 8;
                CurrentVitCProgress += 3;
                CurrentVitAProgress += 7;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        dragon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 13;
                CurrentVitCProgress += 6;
                CurrentVitAProgress += 7;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        grape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 4;
                CurrentVitCProgress += 5;
                CurrentVitAProgress += 2;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        kiwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 9;
                CurrentVitCProgress += 6;
                CurrentVitAProgress += 6;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        lemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 8;
                CurrentVitCProgress += 15;
                CurrentVitAProgress += 4;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        mango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 6;
                CurrentVitCProgress += 12;
                CurrentVitAProgress += 8;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        mangosteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 7;
                CurrentVitCProgress += 5;
                CurrentVitAProgress += 9;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 8;
                CurrentVitCProgress += 14;
                CurrentVitAProgress += 7;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        pineapple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 7;
                CurrentVitCProgress += 7;
                CurrentVitAProgress += 5;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

        watermelon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentMineralProgress += 20;
                CurrentVitCProgress += 15;
                CurrentVitAProgress += 7;

                progressBarMineral.setProgress(CurrentMineralProgress);
                progressBarMineral.setMax(max);
                progressBarVitC.setProgress(CurrentVitCProgress);
                progressBarVitC.setMax(max);
                progressBarVitA.setProgress(CurrentVitAProgress);
                progressBarVitA.setMax(max);
            }
        });

//        recyclerView = view.findViewById(R.id.recycle_view_fruit);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setLayoutManager(layoutManager);

//        getData();

        if (progressBarVitA.getProgress() == progressBarVitA.getMax()){
            openCongratsDialog();
        }
        return view;


//        Toast.makeText(getContext(), "Vit A are met", Toast.LENGTH_LONG).show();
    }

    private void openCongratsDialog() {
        dialog.setContentView(R.layout.congrats_pop);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AppCompatButton button = dialog.findViewById(R.id.pop_ok);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    void initializeArray(){
        array_id       = new ArrayList<String >();
        array_fruit    = new ArrayList<String>();
        array_mineral  = new ArrayList<String>();
        array_vitc     = new ArrayList<String >();
        array_vita     = new ArrayList<String >();
        array_photo    = new ArrayList<String>();

        array_id.clear();
        array_fruit.clear();
        array_mineral.clear();
        array_vitc.clear();
        array_vita.clear();
        array_photo.clear();
    }

    public void getData(){
        initializeArray();
        AndroidNetworking.post("http://tkjb2019.com/mobile/api_kelompok_3/getFruit.php")
                .setPriority( Priority.MEDIUM)
                .setTag("getData")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekdata",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
//                            String pesan = response.getString("result");
//                            Toast.makeText(getActivity ().getApplicationContext (), ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status","" + status);
                            if (status) {
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);
                                    array_id.add(jo.getString("id"));
                                    array_fruit.add(jo.getString("fruitName"));
                                    array_mineral.add(jo.getString("mineral"));
                                    array_vita.add(jo.getString("vitC"));
                                    array_vitc.add(jo.getString("vitA"));
                                    array_photo.add(jo.getString("photo"));
                                }
//                                SharedPreferences.Editor editor = sp1.edit();
//                                editor.clear ();
//                                editor.commit ();
//                                editor.putString ( "id",id );
//                                editor.putString ( "name",name );
//                                editor.putString ( "username",username );
//                                editor.putString ( "email",emaill );
//                                editor.putString ( "password",passwordd );
//                                editor.putString ( "photo",photo );
//                                editor.commit ();

//                              Toast.makeText(getActivity ().getApplicationContext (), "berhasil "+ nama, Toast.LENGTH_SHORT).show();

//                                final FruitzPages adapter = new FruitzPages(getActivity(), array_id, array_fruit, array_mineral, array_vitc, array_vita, array_photo);
//                                recyclerView.setAdapter(adapter);

                            } else {
                                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorLogin",""+anError.getErrorBody());
                    }
                });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}