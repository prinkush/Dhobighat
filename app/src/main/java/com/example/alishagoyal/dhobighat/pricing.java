package com.example.alishagoyal.dhobighat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class pricing extends AppCompatActivity {
    ImageView ivarrow6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing);
        ivarrow6=(ImageView) findViewById(R.id.ivarrow6);
        ivarrow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pricing.this,homescreen.class);
                startActivity(intent);
            }
        });
    }
}
