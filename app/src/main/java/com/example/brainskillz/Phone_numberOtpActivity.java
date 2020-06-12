package com.example.brainskillz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Phone_numberOtpActivity extends AppCompatActivity {

    TextView OtpTextView;
    String phone_numberfinal;
    FirebaseAuth mAuth;
    Button confirmotp;
    private String VerificationId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_otp);


        mAuth= FirebaseAuth.getInstance();
        OtpTextView=findViewById(R.id.PhoneNoOtptextView);
        confirmotp=findViewById(R.id.confirmotpButton);

        phone_numberfinal=getIntent().getStringExtra("phoneno_intent");

        sendVerificationCode(phone_numberfinal);

        confirmotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=OtpTextView.getText().toString();
                VerifyCode(code);
            }
        });

    }

    private void sendVerificationCode(String number){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone_numberfinal,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallback

        );

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback=
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    VerificationId=s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code=phoneAuthCredential.getSmsCode();
                    if (code!=null){
                        VerifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(getApplicationContext(),"Verification Failed",Toast.LENGTH_SHORT).show();
                }
            };

    private void VerifyCode(String code){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(VerificationId,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential){

        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Sign In successful",Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(in);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Firebase Error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
