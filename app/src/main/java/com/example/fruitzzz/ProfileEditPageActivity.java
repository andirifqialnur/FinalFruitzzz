package com.example.fruitzzz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileEditPageActivity extends AppCompatActivity {

    CircleImageView TakePhoto, Photo;

    SharedPreferences sharedPreferences, sp1;

    EditText Name, Username, Email;
    String id, name, username, email, photo, pilihan;

    AppCompatButton Save, Back;

    ProgressDialog progressDialog;

    // Gallery
    private static final int PHOTO_REQUEST_GALLERY = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    final int CODE_GALLERY_REQUEST = 999;
    String rPilihan[]= { "Take a photo", "From Album" };
    public String photoFileName = "photo.jpg";
    public final String APP_TAG = "MyApp";
    Bitmap bitMap = null;
    File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_page);

        Save = findViewById(R.id.save_btn);
        Back = findViewById(R.id.btn_back);

        TakePhoto = findViewById(R.id.take_photo);
        Photo = findViewById(R.id.photo);

        Name = findViewById(R.id.name);
        Username = findViewById(R.id.username);
        Email = findViewById(R.id.email);

        sp1 = getSharedPreferences("AllData", MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("AllData", MODE_PRIVATE);

        id = sp1.getString("id", null);
        name = sp1.getString("name", null);
        username = sp1.getString("username", null);
        email = sp1.getString("email", null);
        photo = sp1.getString("photo", null);

        Name.setText(name);
        Username.setText(username);
        Email.setText(email);

        if (photo.equals ( "" )) {
            Picasso.get().load("https://tkjb2019.com/mobile/api_kelompok_3/image/profile_default.png").into(Photo);
        } else {
            Picasso.get().load("x`"+photo).into (Photo);
        }

        // Function Take Photo
        TakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bitMap != null) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProfileEditPageActivity.this);
                    alertDialogBuilder.setMessage("Do yo want to take photo again?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(context,"You clicked yes button",Toast.LENGTH_LONG).show();
                            //call fuction of TakePhoto
                            TakePhoto();
                        }
                    });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                } else {
                    TakePhoto();
                }
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder ( ProfileEditPageActivity.this )
                        .setMessage("Apakah anda ingin memperbarui "+ name +" ?")
                        .setCancelable(false)
                        .setPositiveButton ( "Ya", new DialogInterface.OnClickListener ( ) {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

//                                progressDialog.setMessage("Updating Data");
//                                progressDialog.setCancelable(false);
//                                progressDialog.show();

                                name = Name.getText().toString();
                                username = Username.getText().toString();
                                email = Email.getText().toString();
                                photo = getStringImage(bitMap);

                                if (bitMap == null) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEditPageActivity.this);
                                    builder.setMessage("Please take a photo ");
                                    AlertDialog alert1 = builder.create();
                                    alert1.show();
                                    progressDialog.dismiss();
                                }

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        validateData();
                                    }
                                },1000);
                            }
                        })
                        .setNegativeButton ( "Tidak", new DialogInterface.OnClickListener ( ) {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        } )
                        .show ();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed ();
            }
        });
    }

    void validateData(){
        if(username.equals("") || name.equals("") || email.equals("")){
            progressDialog.dismiss();
            Toast.makeText(ProfileEditPageActivity.this, "Re-Check Your Input!", Toast.LENGTH_SHORT).show();
        }else {
            updateData();
        }
    }

    public  void TakePhoto(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEditPageActivity.this);
        builder.setTitle("Select");
        builder.setItems(rPilihan, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pilihan = String.valueOf(rPilihan[which]);
                if (pilihan.equals("Take a photo")) {
                    // create Intent to take a picture and return control to the calling application
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // Create a File reference to access to future access
                    photoFile = getPhotoFileUri(photoFileName);

                    String authorities = getPackageName() + ".fileprovider";
                    Uri fileProvider = FileProvider.getUriForFile(ProfileEditPageActivity.this, authorities, photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

                    // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
                    // So as long as the result is not null, it's safe to use the intent.
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        // Start the image capture intent to take photo
                        startActivityForResult(intent, REQUEST_TAKE_PHOTO);

                    }
                } else {
                    ActivityCompat.requestPermissions(ProfileEditPageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_GALLERY_REQUEST);
                }

            }
        });
        builder.show();
    }

    //permission
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CODE_GALLERY_REQUEST){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), CODE_GALLERY_REQUEST);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access gallery!", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //set photo size
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                bitMap = decodeSampledBitmapFromFile(String.valueOf(photoFile), 1000, 700);
                Photo.setImageBitmap(bitMap);
            } else { // Result was a failure
                Toast.makeText(ProfileEditPageActivity.this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (intent != null) {
                Uri photoUri = intent.getData();
                // Do something with the photo based on Uri
                bitMap = null;
                try {
                    bitMap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Load the selected image into a preview
                Photo.setImageBitmap(bitMap);
            }
        }
    }

    public File getPhotoFileUri(String fileName)  {
        // Only continue if the SD Card is mounted
        if (isExternalStorageAvailable()) {
            File mediaStorageDir = new File(getExternalFilesDir( Environment.DIRECTORY_PICTURES), APP_TAG);
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
                Log.d(APP_TAG, "failed to create directory");
            }
            File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

            return file;

        }
        return null;
    }

    //set photo
    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) { // BEST QUALITY MATCH
        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight) {
            inSampleSize = Math.round((float) height / (float) reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }

        options.inSampleSize = inSampleSize;
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public String getStringImage(Bitmap bmp){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    void updateData(){
        String photo = getStringImage(bitMap);
        AndroidNetworking.post("https://tkjb2019.com/mobile/api_kelompok_3/updateUsers.php")
                .addBodyParameter("id","" + id)
                .addBodyParameter("name","" + name)
                .addBodyParameter("username","" + username)
                .addBodyParameter("email","" + email)
                .addBodyParameter("photo","" + photo)
                .setTag("Update Data")
                .setPriority( Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        progressDialog.dismiss();
                        Log.d("responEdit",""+response);
                        try{
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Toast.makeText(ProfileEditPageActivity.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            if(status){
                                new AlertDialog.Builder(ProfileEditPageActivity.this)
                                        .setMessage("Berhasil Mengupdate Data")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
//                                                Intent i = new Intent(editprofile.this,dashboard.class);
//                                                startActivity(i);
//                                                setResult(RESULT_OK,i);
//                                                editprofile.this.finish();
//                                                startActivity ( new Intent ( ProfileEditPageActivity.this, CurrentActivity.class ) );
                                                onBackPressed();

                                            }
                                        })
                                        .show();
                            } else {
                                new AlertDialog.Builder(ProfileEditPageActivity.this)
                                        .setMessage("Gagal Mengupdate Data")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
//                                                Intent i = getIntent();
//                                                setResult(RESULT_CANCELED,i);
//                                                ProfileEditPageActivity.this.finish();
                                            }
                                        })
                                        .show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {}
                });
    }

}