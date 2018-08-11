package com.example.shankaryadav.fireuser;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference();
    DatabaseReference userref = ref.child("users");


    EditText username,name,email;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myUserName = username.getText().toString().trim();
                String myName = name.getText().toString().trim();
                String myEmail = email.getText().toString().trim();

                if ((myUserName.isEmpty() || myName.isEmpty() || myEmail.isEmpty())){
                    Toast.makeText(getApplicationContext(),"Fill the required fields",Toast.LENGTH_SHORT).show();
                }else{

                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("Username", myUserName);
                    hashMap.put("Name", myName);
                    hashMap.put("E-mail", myEmail);

                    userref.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(getApplicationContext(),"user is registered",Toast.LENGTH_SHORT).show();
                       }else{
                           Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                       }
                        }
                    });
                }
            }
        });
    }
}
