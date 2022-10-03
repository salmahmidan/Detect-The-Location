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

public class LoginPassenger extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText UserP,PassP;
    private Button LoginP;
    private TextView forgetTextLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_passenger);
        UserP=findViewById(R.id.EmailLoginP);
        PassP=findViewById(R.id.passLoginP);
        LoginP=findViewById(R.id.buttonLoginP);
        mAuth = FirebaseAuth.getInstance();

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0060FF"));
        actionBar.setBackgroundDrawable(colorDrawable);

        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent = new Intent(LoginPassenger.this, HomeActivityForPassenger.class);
            startActivity(intent);}
    }
    public void LoginPass(View view) {
        String email= String.valueOf(UserP.getText());
        String password= String.valueOf(PassP.getText());
        if (String.valueOf(UserP.getText()).isEmpty()){
            UserP.setError("Email required!");
            return;

        }
        if (String.valueOf(PassP.getText()).isEmpty()){
            PassP.setError("password required!");
            return;
        }
        if (String.valueOf(PassP.getText()).length()<6){
            PassP.setError("Password must contain more than 5 digits");
            return;

        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginPassenger.this,"You Are Logged In",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginPassenger.this, HomeActivityForPassenger.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginPassenger.this, "User Not Found",
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
    public void ResetPassP(View view) {
        EditText resetMail= new EditText(view.getContext());
        AlertDialog.Builder PasswordResetP= new AlertDialog.Builder(view.getContext());
        PasswordResetP.setTitle("Reset Password ?");
        PasswordResetP.setMessage("Enter your email to Receive reset link");
        PasswordResetP.setView(resetMail);
        PasswordResetP.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mail=resetMail.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(LoginPassenger.this,"Reset link sent to your email",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginPassenger.this,"ERROR!! Reset link is not sent"+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        PasswordResetP.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        PasswordResetP.create().show();

    }

    public void buttonClickGoToSignRider(View view) {
        Intent intent = new Intent(LoginPassenger.this, SignUpPassenger.class);
        startActivity(intent);
    }
}
