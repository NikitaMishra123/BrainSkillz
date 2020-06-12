package com.example.brainskillz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String Phone_Number;
    EditText phone_number;
    Button sendphone;
    String CountryPhoneNoCode;
    FirebaseAuth mAuth;

    Button googleauth,Twitter;
    FirebaseAuth.AuthStateListener authStateListener;

    Spinner spinnerCountries;

    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mCountryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Twitter=findViewById(R.id.twitter);



        googleauth=findViewById(R.id.google);

        phone_number=findViewById(R.id.phone_input);
        sendphone=findViewById(R.id.next);
        spinnerCountries=findViewById(R.id.spinnercountryitems);

        initList();

        mAuth= FirebaseAuth.getInstance();

        mCountryAdapter=new CountryAdapter(this,mCountryList);
        spinnerCountries.setAdapter(mCountryAdapter);

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem selectedCountry= (CountryItem) parent.getItemAtPosition(position);
                CountryPhoneNoCode=selectedCountry.getCountryCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sendphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone_number!=null){

                    String n=phone_number.getText().toString();
                    Phone_Number=CountryPhoneNoCode+n;

                    Intent in=new Intent(MainActivity.this,Phone_numberOtpActivity.class);
                    in.putExtra("phoneno_intent",Phone_Number);
                    startActivity(in);
                }
            }
        });
    }

    private void initList(){
        mCountryList=new ArrayList<>();
        mCountryList.add(new CountryItem(R.mipmap.india,"In","+91"));
        mCountryList.add(new CountryItem(R.mipmap.usa,"Us","+1"));
        mCountryList.add(new CountryItem(R.mipmap.france,"Fr","+33"));
        mCountryList.add(new CountryItem(R.mipmap.canada,"Ca","+33"));
        mCountryList.add(new CountryItem(R.mipmap.russia,"Ru","+7"));




        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser User=mAuth.getCurrentUser();
                if (User!=null){
                    Intent in=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(in);
                    finish();
                }
            }
        };


        googleauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),GoogleActivity.class);
                startActivity(in);
            }
        });


        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,TwitterLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAuth.removeAuthStateListener(authStateListener);






    }
}
