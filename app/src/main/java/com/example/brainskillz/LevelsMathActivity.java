package com.example.brainskillz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LevelsMathActivity extends AppCompatActivity {

    ImageView mathlevel1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_math);

        mathlevel1=findViewById(R.id.mathlevelbrain);

        mathlevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LevelsMathActivity.this,MathActivity.class);
                startActivity(intent);
            }
        });


    }
}
