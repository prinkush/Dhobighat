package com.example.alishagoyal.dhobighat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Wallet extends AppCompatActivity {
    ImageView ivarrow7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ivarrow7=(ImageView) findViewById(R.id.ivarrow7);
        ivarrow7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Wallet.this,homescreen.class);
                startActivity(intent);
            }
        });
    }
}
