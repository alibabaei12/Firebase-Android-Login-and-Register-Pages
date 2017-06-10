package com.example.ali.easytrade;

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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText email;
    private Button resetPasswordButton;
    private RelativeLayout activity_forgot;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = (EditText) findViewById(R.id.emailForgotPasswordEditTex);
        resetPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);
        activity_forgot = (RelativeLayout) findViewById(R.id.activity_forgotPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().isEmpty() )
                {
                    Snackbar sB = Snackbar.make(activity_forgot,"You should write your email above.", Snackbar.LENGTH_SHORT);
                    sB.show();
                }
                else{
                    resetPassword(email.getText().toString());

                }
            }
        });


    }


    private void resetPassword(final String em){
        firebaseAuth.sendPasswordResetEmail(em)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Snackbar sB = Snackbar.make(activity_forgot,"We have sent you a new Password to email:"+em, Snackbar.LENGTH_SHORT);
                            sB.show();
                        }
                        else{
                            Snackbar sB = Snackbar.make(activity_forgot,"Failed to sent a password, There is no such email", Snackbar.LENGTH_SHORT);
                            sB.show();
                        }

                    }
                });
    }
}
