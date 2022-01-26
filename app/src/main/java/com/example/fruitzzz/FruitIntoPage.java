package com.example.fruitzzz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FruitIntoPage extends Fragment {

    ListView listView;
    ArrayList<String>arr_id, arr_fruit_name, arr_description, arr_mineral, arr_vit_c, arr_vit_a, arr_photo;
    public FruitIntoPage() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fruit_into_page, container, false);

        listView = view.findViewById(R.id.listFruitInfo);

        getData();

        return view;
    }

    void initializeArray(){
        arr_id            = new ArrayList<String>();
        arr_fruit_name    = new ArrayList<String>();
        arr_description   = new ArrayList<String>();
        arr_mineral       = new ArrayList<String>();
        arr_vit_a         = new ArrayList<String>();
        arr_vit_c         = new ArrayList<String>();
        arr_photo         = new ArrayList<String>();

        arr_id.clear();
        arr_fruit_name.clear();
        arr_description.clear();
        arr_mineral.clear();
        arr_vit_c.clear();
        arr_vit_a.clear();
        arr_photo.clear();
    }

    public void getData() {
        initializeArray();
        AndroidNetworking.get("https://tkjb2019.com/mobile/api_kelompok_3/getFruit.php")
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Boolean status = response.getBoolean("status");
                            if (status) {
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon", "" + ja);
                                for (int i = 0; i < ja.length(); i++) {
                                    JSONObject jo = ja.getJSONObject(i);

                                    arr_id.add(jo.getString("id"));
                                    arr_fruit_name.add(jo.getString("fruitName"));
                                    arr_description.add(jo.getString("description"));
                                    arr_mineral.add(jo.getString("mineral"));
                                    arr_vit_c.add(jo.getString("vitC"));
                                    arr_vit_a.add(jo.getString("vitA"));
                                    arr_photo.add(jo.getString("photo"));
                                }

                                FruitInfoPageAdapter adapter = new FruitInfoPageAdapter( getActivity(), arr_fruit_name, arr_description, arr_vit_c, arr_vit_a, arr_mineral, arr_photo );
                                listView.setAdapter(adapter);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent i = new Intent(getActivity(), FruitInfoDetailActivity.class);

                                        i.putExtra("id",arr_id.get(position));
                                        i.putExtra("fruitName",arr_fruit_name.get(position));
                                        i.putExtra("description",arr_description.get(position));
                                        i.putExtra("mineral",arr_mineral.get(position));
                                        i.putExtra("vitC",arr_vit_c.get(position));
                                        i.putExtra("vitA",arr_vit_a.get(position));
                                        i.putExtra("photo",arr_photo.get(position));

                                        startActivity(i);
                                    }
                                });
                            } else {
                                Toast.makeText(getActivity(), "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) { }
                });
    }}