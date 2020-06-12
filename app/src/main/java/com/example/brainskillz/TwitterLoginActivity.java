package com.example.brainskillz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


public class TwitterLoginActivity extends AppCompatActivity {


    Button TwitterButton;
    TwitterLoginButton TwitterLogin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser User;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_login);


        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getResources().getString(R.string.twitter_consumer_key),
                        getResources().getString(R.string.twitter_consumer_secret)))
                .debug(true)
                .build();
        Twitter.initialize(config);

        setContentView(R.layout.activity_twitter_login);

        mAuth=FirebaseAuth.getInstance();
        User=mAuth.getCurrentUser();

        TwitterLogin=findViewById(R.id.twitterloginbtn);
        TwitterButton=findViewById(R.id.twitterbtn);


        TwitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwitterLogin.performClick();
            }
        });

//        mAuthListener = new FirebaseAuth.AuthStateListener(){
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
//                FirebaseUser User=mAuth.getCurrentUser();
//                if (User!= null){
//                    Intent in=new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(in);
//                }
//            }
//        };

        TwitterLogin.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                signInToFirebaseWithTwitterSession(result.data);

            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(),"Authentication Fail",Toast.LENGTH_SHORT).show();
            }
        });



    }


    private void signInToFirebaseWithTwitterSession(TwitterSession session){
        AuthCredential credential = TwitterAuthProvider.getCredential(session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(), "Signed in firebase twitter successful", Toast.LENGTH_LONG).show();
                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Auth firebase twitter failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TwitterLogin.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onStart() {
        super.onStart();
        //mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        // mAuth.removeAuthStateListener(mAuthListener);


    }
}
