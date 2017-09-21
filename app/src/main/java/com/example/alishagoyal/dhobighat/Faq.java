package com.example.alishagoyal.dhobighat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Faq extends AppCompatActivity {
    ImageView ivarrow9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ivarrow9=(ImageView) findViewById(R.id.ivarrow9);
        ivarrow9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Faq.this,homescreen.class);
                startActivity(intent);
            }
        });
    }
}
