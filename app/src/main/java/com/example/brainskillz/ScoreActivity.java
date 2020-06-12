package com.example.brainskillz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {

    TextView tv;
    Button Try,Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Try=findViewById(R.id.tryAgain);
        Next=findViewById(R.id.next);

        tv = findViewById(R.id.score);
        int score = getIntent().getIntExtra("score", 0);
        tv.setText("Current Score : " + score);

        if(score<=30){
            Next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ScoreActivity.this, "Need More Points", Toast.LENGTH_SHORT).show();
                    Next.setEnabled(false);
                }
            });
        }
        else{
            Next.setEnabled(true);
            Next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(ScoreActivity.this,Level2Activity.class);
                    startActivity(i);
                }
            });
        }



    }

}
