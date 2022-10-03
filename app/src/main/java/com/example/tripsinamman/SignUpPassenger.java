package com.example.tripsinamman;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpPassenger extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    // [START declare_auth]
    FirebaseAuth mAuth;
    private static final int PICK_IMAGE_REQUEST = 1;
    //private StorageReference storageReference;
    // private FirebaseStorage storage;
    public Uri mImageUri;
    ImageView img;
    FirebaseFirestore fStore;
    EditText UserS,passS,UserName,PhoneN,Location;
    Button signUpB , mButtonChooseImage;
    RadioGroup G1;
    RadioButton ChC,DonC;
    TextView AccT;
    String CharId,UsersId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_rider);
        UserS=findViewById(R.id.emailsignupP);
        passS=findViewById(R.id.passsignupP);
        UserName=findViewById(R.id.namesignupP);
        signUpB=findViewById(R.id.buttonsignupP);

        mAuth = FirebaseAuth.getInstance();
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0060FF"));
        actionBar.setBackgroundDrawable(colorDrawable);
        if(mAuth.getCurrentUser() != null){
            Toast.makeText(this,"You already have account",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
    public void AddToDB() {
        Toast.makeText(SignUpPassenger.this, "yes", Toast.LENGTH_SHORT).show();
        fStore = FirebaseFirestore.getInstance();
        //if (ChC.isChecked()){
        CharId = FirebaseAuth.getInstance().getUid();
        Map<String, Object> CharUsers = new HashMap<>();
        CharUsers.put("PassengerId", CharId);
        CharUsers.put("PassengerEmail", UserS.getText().toString());
        //CharUsers.put("charityPhone", PhoneN.getText().toString());
        CharUsers.put("PassengerName", UserName.getText().toString());
        //CharUsers.put("charityLoc",Location.getText().toString());
        //CharUsers.put("charityDes",CharId);
        CharUsers.put("typeOfUser","Pas");
        //CharUsers.put("accept",false);

        DocumentReference documentReference = fStore.collection("Passenger").document(CharId);
        documentReference.set(CharUsers).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(SignUpPassenger.this, "mabrooooooook", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(SignUpPassenger.this, "toz 3lekom", Toast.LENGTH_LONG).show();
                Toast.makeText(SignUpPassenger .this, CharId, Toast.LENGTH_LONG).show();
                Log.d("myTag", e.getMessage());

            }
        });
    }

    public void signUpPass(View view) {
        String email= String.valueOf(UserS.getText()).trim();
        String password= String.valueOf(passS.getText()).trim();
        if (String.valueOf(UserS.getText()).isEmpty()){
            UserS.setError("Email required!");
            return;
        }
        if (String.valueOf(passS.getText()).isEmpty()){
            passS.setError("password required!");
            return;
        }

        if (String.valueOf(passS.getText()).length()<6){
            passS.setError("email must contain more than 5 digits");
            return;
        }
        if (String.valueOf(UserName.getText()).isEmpty()){
            UserName.setError("Phone number required!");
            return;
        }
        //createAccount(email,password);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignUpPassenger.this,"You Are Signed Up",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpPassenger.this, HomeActivityForPassenger.class);
                    startActivity(intent);
                    AddToDB();
                    finish();
                }else{
                    Toast.makeText(SignUpPassenger.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();}
            }
        });

    }


    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }

    public void buttonClickGoToLoginRider(View view) {
        Intent intent = new Intent(SignUpPassenger.this, LoginPassenger.class);
        startActivity(intent);
    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_rider);
    }
    public void buttonClickGoToLoginPassenger(View view) {
        Intent i = new Intent(SignUpPassenger.this, login_Passenger.class);
        SignUpPassenger.this.startActivity(i);
    }
    public void buttonClickGoToHomePassenger(View view) {
        Intent i = new Intent(SignUpPassenger.this, HomeActivityForPassenger.class);
        SignUpPassenger.this.startActivity(i);
    }
    */

}