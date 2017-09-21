package com.example.alishagoyal.dhobighat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Help extends AppCompatActivity {
    ImageView ivarrow8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ivarrow8=(ImageView) findViewById(R.id.ivarrow8);
        ivarrow8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Help.this,homescreen.class);
                startActivity(intent);
            }
        });
    }
}
