package com.example.fruitzzz;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePage extends Fragment {

    View view;

    AppCompatButton logout, edit;

    SharedPreferences sharedPreferences, sp1;

    CircleImageView Photo;

    TextView Name, Username, Email;
    String name, username, email, photo;

    public ProfilePage() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_page, container, false);  

        logout = (AppCompatButton) view.findViewById(R.id.logout_btn);
        edit = (AppCompatButton) view.findViewById(R.id.edit_btn);

        Name = view.findViewById(R.id.name);
        Username = view.findViewById(R.id.username);
        Email = view.findViewById(R.id.email);
        Photo = view.findViewById(R.id.Photo);

        sp1 = getActivity().getSharedPreferences("AllData", MODE_PRIVATE);
        sharedPreferences = getActivity().getSharedPreferences("AllData", MODE_PRIVATE);

        name     = sp1.getString( "name", null );
        username = sp1.getString( "username", null );
        email    = sp1.getString( "email", null );
        photo    = sp1.getString( "photo", null );

        Name.setText ( name );
        Username.setText ( username );
        Email.setText ( email );

        if ( photo.equals("") ) {
            Picasso.get().load("https://tkjb2019.com/mobile/api_kelompok_3/image/profile_default.png").into(Photo);
        } else {
            Picasso.get().load("https://tkjb2019.com/mobile/api_kelompok_3/image/" + photo).into(Photo);
        }

        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });

        edit.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileEditPageActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void clear() {
        SharedPreferences.Editor editor = sp1.edit();
        editor.clear();
        editor.commit();
    }
}