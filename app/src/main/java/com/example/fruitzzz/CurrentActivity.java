package com.example.fruitzzz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class CurrentActivity extends AppCompatActivity {
    private static final String TAG = CurrentActivity.class.getSimpleName();
    Toolbar toolbar;
    String id, name, username, emaill, passwordd, photo;
    TextView GreetName;

    String usr;

    AnimatedBottomBar animatedBottomBar;
    FragmentManager fragmentManager;

    SharedPreferences sharedPreferences, sp1;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        GreetName = findViewById(R.id.nameGreet);

        sharedPreferences = getSharedPreferences("AllData", MODE_PRIVATE);
        sp1 = getSharedPreferences("AllData", MODE_PRIVATE);

        usr = sharedPreferences.getString("username",null);
        Toast.makeText(CurrentActivity.this, "welcome " + usr, Toast.LENGTH_SHORT).show();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        animatedBottomBar = findViewById(R.id.bottom_nav);
        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int lastIndex, @Nullable AnimatedBottomBar.Tab lastTab, int newIndex, @NonNull AnimatedBottomBar.Tab newTab) {
                Fragment fragment = null;
                switch (newTab.getId()){
                    case R.id.home:
                        fragment = new HomePage();
                        break;
                    case R.id.recipe:
                        fragment = new RecipePage();
                        break;
                    case R.id.fruitzzz:
                        fragment = new FruitPage();
                        break;
                    case R.id.info:
                        fragment = new FruitIntoPage();
                        break;
                    case R.id.profile:
                        fragment = new ProfilePage();
                        break;
                }
                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                } else {
                    Log.d(TAG, "Error ii we fokus ko");
                }
            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {
            }
        });

        animatedBottomBar.selectTabAt(2,true);
        animatedBottomBar.isShown();
        getData ();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dropdown, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notif) {
            startActivity(new Intent(CurrentActivity.this, AlarmActivity.class));
        } else if (id == R.id.profile) {
            startActivity(new Intent(CurrentActivity.this, ProfilePage.class));
        } else if (id == R.id.setting) {
            Toast.makeText(getApplicationContext(), "settings clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.info) {
            startActivity(new Intent(CurrentActivity.this, Information.class));
        } else if (id == R.id.logout) {
            logout();
        }
        return true;
    }

    public void getData(){
        AndroidNetworking.post("http://tkjb2019.com/mobile/api_kelompok_3/getDataCurrent.php")
                .addBodyParameter("email",emaill)
                .setPriority( Priority.MEDIUM)
                .setTag("getData")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener () {
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
                                    id = jo.getString ( "id");
                                    name = jo.getString("name");
                                    username = jo.getString("username");
                                    emaill = jo.getString("email");
                                    passwordd = jo.getString("password");
                                    photo = jo.getString("photo");
                                }
                                SharedPreferences.Editor editor = sp1.edit();
                                editor.clear ();
                                editor.commit ();
                                editor.putString ( "id",id );
                                editor.putString ( "name",name );
                                editor.putString ( "username",username );
                                editor.putString ( "email",emaill );
                                editor.putString ( "password",passwordd );
                                editor.putString ( "photo",photo );
                                editor.commit ();

//                              Toast.makeText(getActivity ().getApplicationContext (), "berhasil "+ nama, Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(CurrentActivity.this, "error", Toast.LENGTH_SHORT).show();
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
    void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent toLogin;
        toLogin = new Intent(CurrentActivity.this, SignInActivity.class);
        startActivity(toLogin);
    }
}