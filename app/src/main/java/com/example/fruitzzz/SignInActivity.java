package com.example.fruitzzz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {

    ArrayList<String> id, name, username, emaill, passwordd, photo;

    EditText ETEmail, ETPassword;
    String email, password;

    TextView textSignup, forgotPass;
    AppCompatButton SignIn;

    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;

    boolean emailValid, passwordValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);

        textSignup = findViewById(R.id.signupp_btn);
        SignIn = findViewById(R.id.SignIn_btn);

        ETEmail = (EditText) findViewById(R.id.email);
        ETPassword = (EditText) findViewById(R.id.pass);

        progressDialog = new ProgressDialog(this);
        sharedPreferences = getSharedPreferences("AllData", MODE_PRIVATE);

        textSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = ETEmail.getText().toString().trim();
                password = ETPassword.getText().toString().trim();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validateData();
                    }
                },1000);
            }
        });
    }

    //  session
    @Override
    protected void onResume() {
        sharedPreferences = getSharedPreferences("AllData", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("email")) {
            startActivity(new Intent( getApplicationContext(), CurrentActivity.class));
        }
        super.onResume();
    }

    void validateData(){

        // Username Handler
        if (email.isEmpty()) {
            ETEmail.setError("Please enter your Email!");
            emailValid = false;
        } else {
            emailValid = true;
        }

        // Password Handler
        if (password.isEmpty()) {
            ETPassword.setError("Please enter your password!");
            passwordValid = false;
        } else if (password.length() < 6) {
            ETPassword.setError("Password must contain more than 6 characters!");
            passwordValid = false;
        } else {
            passwordValid = true;
        }

        if (emailValid && passwordValid) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    sendData();
                    getData();
                }
            },1000);
        }
    }

    void sendData(){
        progressDialog.setMessage("Sign In..."); // pesan yang muncul
        progressDialog.setCancelable(false); // mengirim data tidak dapat di cancel
        progressDialog.show(); // menampilkan dialog

        // menggunakan library dengan menggunakan post untuk mengirim data
        AndroidNetworking.post("https://tkjb2019.com/mobile/api_kelompok_3/signIn.php")
                // parameter2 / apa2 saja yg ingin dimasukkan
                .addBodyParameter("email",""+email)
                .addBodyParameter("password",""+password)
                // prioritas misalkan ada data yang ingin sama2 mengirim
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekTambah",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Toast.makeText(SignInActivity.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            // jika status true
                            if(status){
                                new AlertDialog.Builder(SignInActivity.this)
                                        .setMessage("Login Successfully!")
                                        .setCancelable(false)
                                        .show();
                                startActivity(new Intent(SignInActivity.this, CurrentActivity.class));
                            } else {
                                new AlertDialog.Builder(SignInActivity.this)
                                        .setMessage("Wrong password or username!")
                                        .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
//                                                Intent i = new Intent(SignInActivity.this, SignInActivity.class);
//                                                startActivity(i);
//                                                SignInActivity.this.finish();
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    // jika ada error
                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });
    }

    void allArray() {
        id = new ArrayList<String>();
        name = new ArrayList<String>();
        username = new ArrayList<String>();
        emaill = new ArrayList<String>();
        passwordd = new ArrayList<String>();
        photo = new ArrayList<String>();

        id.clear();
        name.clear();
        username.clear();
        emaill.clear();
        passwordd.clear();
        photo.clear();
    }

    public void getData(){
        allArray ();
        AndroidNetworking.post("https://tkjb2019.com/mobile/api_kelompok_3/getLogin.php")
                .addBodyParameter("email",""+email)
                .addBodyParameter("password",""+password)
                .setPriority(Priority.MEDIUM)
                .setTag("Login")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekLogin",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Log.d("status",""+status);
                            if (status) {
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respond",""+ja);
                                for (int i = 0 ; i < ja.length() ; i++) {
                                    JSONObject jo = ja.getJSONObject(i);
                                    id.add(jo.getString ( "id" ) );
                                    name.add(jo.getString("name"));
                                    username.add(jo.getString("username"));
                                    emaill.add(jo.getString("email"));
                                    passwordd.add(jo.getString("password"));
                                    photo.add(jo.getString("photo"));
                                }
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString ( "id", id.get ( 0 ) );
                                editor.putString ( "name", name.get ( 0 ) );
                                editor.putString ( "username", username.get ( 0 ) );
                                editor.putString ( "email", emaill.get ( 0 ) );
                                editor.putString ( "password", passwordd.get ( 0 ) );
                                editor.putString ( "photo", photo.get ( 0 ) );
                                editor.commit();
                            } else { }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorLogin",""+anError.getErrorBody());
                    }
                });
    }
}