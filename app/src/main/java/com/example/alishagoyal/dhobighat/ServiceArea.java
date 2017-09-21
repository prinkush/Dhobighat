package com.example.alishagoyal.dhobighat;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;

public class ServiceArea extends AppCompatActivity {
    ImageView ivarrow5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_area);
        ivarrow5=(ImageView) findViewById(R.id.ivarrow5);
        ivarrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceArea.this,homescreen.class);
                startActivity(intent);
            }
        });
    }
}
