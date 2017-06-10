package com.example.ali.easytrade;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity   {

    private EditText emailView;
    private EditText passwordView;
    private Button loginButton;
    private Button registerButton;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private RelativeLayout activity_mainLayout;
    private Button forgotPasswordButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        loginButton = (Button) findViewById(R.id.logInButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        emailView = (EditText) findViewById(R.id.emailLoginView);
        passwordView = (EditText) findViewById(R.id.passwordLoginEditText);

        activity_mainLayout = (RelativeLayout) findViewById(R.id.activity_main);


        forgotPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, HomePageActivity.class));
        }

        //if clicked forgot password
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, ForgotPasswordActivity.class));
                finish();
            }
            });

        // if clicked register
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, RegisterPageActivity.class));
                finish();
            }
        });

        //if clicked log in

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin(emailView.getText().toString(), passwordView.getText().toString());
            }
        });
    }


    private void userLogin(String em, final String pass){


        firebaseAuth.signInWithEmailAndPassword(em, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            startActivity(new Intent(getApplicationContext(), Profile.class));

                        }
                        else{
                            if (!task.isSuccessful()){

                                    Snackbar sB = Snackbar.make(activity_mainLayout,"The Password is wrong", Snackbar.LENGTH_SHORT);
                                    sB.show();

                            }
                        }
                    }
                });

    }

}
