package com.example.ali.easytrade;

import android.content.Intent;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity {

    private Button logoutB;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        logoutB = (Button) findViewById(R.id.logoutButton);
        firebaseAuth = FirebaseAuth.getInstance();
        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });

    }

    //dont logout if the press the back botton 
    public void onBackPressed(){

    }

    public void logoutUser(){
        firebaseAuth.signOut();
        if(firebaseAuth.getCurrentUser() == null);
        startActivity(new Intent(HomePageActivity.this, MainActivity.class));
        finish();
    }


}
