package com.example.tripsinamman;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginDriver extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText UserD,PassD;
    private Button LoginD;
    private TextView forgetTextLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_driver);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        UserD=findViewById(R.id.EmailLoginD);
        PassD=findViewById(R.id.passLoginD);
        LoginD=findViewById(R.id.buttonLoginD);
        mAuth = FirebaseAuth.getInstance();
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0060FF"));
        actionBar.setBackgroundDrawable(colorDrawable);
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent = new Intent(LoginDriver.this, HomeActivityForDriver.class);
            startActivity(intent);}
    }
    public void LoginDriver(View view) {
        String email= String.valueOf(UserD.getText());
        String password= String.valueOf(PassD.getText());
        if (String.valueOf(UserD.getText()).isEmpty()){
            UserD.setError("Email required!");
            return;
        }
        if (String.valueOf(PassD.getText()).isEmpty()){
            PassD.setError("password required!");
            return;
        }
        if (String.valueOf(PassD.getText()).length()<6){
            PassD.setError("Password must contain more than 5 digits");
            return;

        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginDriver.this,"Succeeeeeded",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginDriver.this, HomeActivityForDriver.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginDriver.this, "User Not Found",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }

    /*
        public void MoveToS(View view) {
            if (FirebaseAuth.getInstance().getCurrentUser()!=null) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "You already have account", Toast.LENGTH_LONG).show();
            }else {
                Intent intent = new Intent(this, SignUpPassenger.class);
                startActivity(intent);}
        }
    */
    public void ResetPassD(View view) {
        EditText resetMail= new EditText(view.getContext());
        AlertDialog.Builder PasswordResetD= new AlertDialog.Builder(view.getContext());
        PasswordResetD.setTitle("Reset Password ?");
        PasswordResetD.setMessage("Enter your email to Receive reset link");
        PasswordResetD.setView(resetMail);
        PasswordResetD.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mail=resetMail.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(LoginDriver.this,"Reset link sent to your email",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginDriver.this,"ERROR!! Reset link is not sent"+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        PasswordResetD.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        PasswordResetD.create().show();

    }

    public void
    buttonClickGoToSignDriver(View view) {
        Intent intent = new Intent(LoginDriver.this, SignUpDriver.class);
        startActivity(intent);
    }
}