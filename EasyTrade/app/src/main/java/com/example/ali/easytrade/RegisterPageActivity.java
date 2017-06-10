package com.example.ali.easytrade;

        import android.app.PendingIntent;
        import android.app.ProgressDialog;
        import android.app.TaskStackBuilder;
        import android.content.Intent;
        import android.support.annotation.MainThread;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;


        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.*;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

public class RegisterPageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registerButton;
    private EditText phoneNumberEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText dateOfBirthEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "RegisterPageActivity";


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        progressDialog = new ProgressDialog(this);

        registerButton = (Button) findViewById(R.id.registerButton);
        emailEditText = (EditText) findViewById(R.id.emailTextView);
        passwordEditText = (EditText) findViewById(R.id.repeatPasswordTextView);
        repeatPassword = (EditText) findViewById(R.id.repeatPasswordTextView) ;

        
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity ( new Intent(getApplicationContext(), MainActivity.class));
        this.finish();
    }
    //check for the email and password and repeat password and if all is gpo
    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String rp = repeatPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            // stopping the function execution further
        }
         else if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            // stopping the function execution further
        }
         else if (TextUtils.isEmpty(rp)) {
            Toast.makeText(this, "Please repeat the password", Toast.LENGTH_SHORT).show();
        }

        //progressDialog.setMessage("Register User...");
        //progressDialog.show();

         else if (rp.equals(password)) {


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //user is successfully registered and logged in
                                // we will start profile activity here
                                // right now lets display a toast only
                                Toast.makeText(RegisterPageActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(RegisterPageActivity.this, HomePageActivity.class));
                            } else {
                                Toast.makeText(RegisterPageActivity.this, "Could not Register...  Please try again", Toast.LENGTH_SHORT).show();

                            }
                            // ...
                        }
                    });

        }
        else{
            Toast.makeText(this, "The Passwords Were Not A Mach! Try Again!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.registerButton){
            registerUser();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}