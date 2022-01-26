package com.example.fruitzzz;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    TextView textSignIn;
    AppCompatButton SignUp;
    // menampung nilai dari inputan
    String name, username, email, password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText ETName, ETUsername, ETEmail, ETPassword;
    // loading
    ProgressDialog progressDialog;
    boolean nameValid, usernameValid, emailValid, passwordValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ETName        = findViewById(R.id.name);
        ETUsername    = findViewById(R.id.username);
        ETEmail       = findViewById(R.id.email);
        ETPassword    = findViewById(R.id.pass);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_sign_up);

        SignUp = (AppCompatButton) findViewById(R.id.SignUp_btn);
        textSignIn = (TextView) findViewById(R.id.signinn_btn);

        progressDialog = new ProgressDialog(this);

        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(i);
                SignUpActivity.this.finish();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ambil data
                name        = ETName.getText().toString().trim();
                username    = ETUsername.getText().toString().trim();
                email       = ETEmail.getText().toString().trim();
                password    = ETPassword.getText().toString().trim();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validasiData();
                    }
                },1000);
            }
        });
    }

    // berisi kondisi
    void validasiData(){

        // cek nama
        if(name.isEmpty()) {
            ETName.setError("Please enter your name!");
            nameValid = false;
        } else {
            nameValid = true;
        }

        // cek username
        if(username.isEmpty()) {
            ETUsername.setError("Please enter your username!");
            usernameValid = false;
        } else if(username.length() < 6) {
            ETUsername.setError("Username must contain more than 6 characters!");
            usernameValid = false;
        } else {
            usernameValid = true;
        }

        // cek email
        if(email.isEmpty()) {
            ETEmail.setError("Please enter your email!");
            emailValid = false;
        } else if(!email.matches(emailPattern)) {
            ETEmail.setError("Please enter valid email!");
            emailValid = false;
        } else {
            emailValid = true;
        }

        // cek password
        if(password.isEmpty()) {
            ETPassword.setError("Please enter your password!");
            passwordValid = false;
        } else if(password.length() < 6) {
            ETPassword.setError("Password must contain more than 6 characters!");
            passwordValid = false;
        } else {
            passwordValid = true;
        }

        if (nameValid && usernameValid && emailValid && passwordValid) {
            kirimData();
        }

    }

    // kirim data
    void kirimData(){
        progressDialog.setMessage("Sign Up..."); // pesan yang muncul
        progressDialog.setCancelable(false); // mengirim data tidak dapat di cancel
        progressDialog.show(); // menampilkan dialog

        // menggunakan library dengan menggunakan post untuk mengirim data
        AndroidNetworking.post("https://tkjb2019.com/mobile/api_kelompok_3/signUp.php")
                // parameter2 / apa2 saja yg ingin dimasukkan
                .addBodyParameter("name",""+name)
                .addBodyParameter("username",""+username)
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
                            Toast.makeText(SignUpActivity.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            // jika status true
                            if(status){
                                new AlertDialog.Builder(SignUpActivity.this)
                                        .setMessage("Registration completed successfully! Please sign in to access your account!")
                                        .setCancelable(false)
                                        .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                                                startActivity(i);
                                                SignUpActivity.this.finish();
                                            }
                                        })
                                        .show();
                            }
                            // jika status false
                            else{
                                new AlertDialog.Builder(SignUpActivity.this)
                                        .setMessage("Registration failed! Please try again.")
                                        .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                                //Intent i = new Intent(SignUpActivity.this, SignUpActivity.class);
                                                //startActivity(i);
                                                //SignUpActivity.this.finish();
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
}