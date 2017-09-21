package com.example.alishagoyal.dhobighat;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homescreen extends AppCompatActivity {
    private TextView tv1;
    private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9;

    public homescreen()
    {
        super();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
        bt4=(Button)findViewById(R.id.bt4);
        bt5=(Button)findViewById(R.id.bt5);
        bt6=(Button)findViewById(R.id.bt6);
        bt7=(Button)findViewById(R.id.bt7);
        bt8=(Button)findViewById(R.id.bt8);
        bt9=(Button)findViewById(R.id.bt9);
        tv1=(TextView)findViewById(R.id.tv1);

        bt1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(homescreen.this,Order_Now.class);
                        startActivity(intent);

                    }
                }


        );

        bt2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(homescreen.this,OrderStatus.class);
                        startActivity(intent);

                    }
                }


        );

        bt3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(homescreen.this,Orderhistory.class);
                        startActivity(intent);

                    }
                }


        );

        bt4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(homescreen.this,profile.class);
                        startActivity(intent);

                    }
                }


        );

        bt5.setOnClickListener
                (

                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(homescreen.this,ServiceArea.class);
                                startActivity(intent);

                            }
                        }


                );

        bt6.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(homescreen.this,pricing.class);
                        startActivity(intent);

                    }
                }


        );
        bt7.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {

                        Intent intent = new Intent(homescreen.this,Wallet.class);
                        startActivity(intent);

                    }
                }


        );
        bt8.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(homescreen.this,Help.class);
                        startActivity(intent);

                    }
                }


        );
        bt9.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(homescreen.this,Faq.class);
                        startActivity(intent);

                    }
                }


        );
    }


}



