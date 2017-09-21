package com.example.alishagoyal.dhobighat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.alishagoyal.dhobighat.R.id.rg;

public class Order_Now extends AppCompatActivity {
    private Spinner spinner1, spinner2;
    private Button btnBook;
     RadioGroup Rg;
     RadioButton Rbt1,Rbt2,Rbt3;
    List<String> LPickupDay, LArea;
    TextView tvPD, tvArea;
    EditText etADD;
    TimePicker tp;
    TextView time;
    ImageView ivordernow;

    public static final String URL = "http://10.0.2.2:8080/Internship/ordernow.php";
    RequestHandler rh=new RequestHandler();
    ProgressDialog pdialog;
    AsyncTask<Void, Void, Void> task;
    String pday,services,parea,time1,paddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__now);
        ivordernow=(ImageView) findViewById(R.id.ivordernow);
        ivordernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Order_Now.this,homescreen.class);
                startActivity(intent);
            }
        });
        Rg=(RadioGroup) findViewById(rg);
        Rbt1=(RadioButton) findViewById(R.id.Rbt1);
        Rbt2=(RadioButton) findViewById(R.id.Rbt2);
        Rbt3=(RadioButton) findViewById(R.id.Rbt3);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnBook = (Button) findViewById(R.id.btnBook);
        tvPD = (TextView) findViewById(R.id.tvPD);
        tvArea = (TextView) findViewById(R.id.tvArea);
        etADD = (EditText) findViewById(R.id.etADD);
        time=(TextView) findViewById(R.id.time);
        tp=(TimePicker)  findViewById(R.id.tp);
        tp.setIs24HourView(false);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourofday, int minute) {
                time.setText(hourofday+":" +minute);
                time1=time.getText().toString();


            }
        });

        LPickupDay = new ArrayList<String>();
        LPickupDay.add("Choose Day");
        LPickupDay.add("Today");
        LPickupDay.add("Tomorrow");
        LPickupDay.add("Day after Tomorrow");

        LArea = new ArrayList<String>();
        LArea.add("Choose Area");
        LArea.add("Kumbhamarg");
        LArea.add("India Gate");
        LArea.add("Sitapura");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, LPickupDay);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {

                    case 0:
                        pday = "Today";
                        //tvPD.setText(str);
                        break;
                    case 1:
                        pday = "Tomorrow";
                        //tvPD.setText(str);
                        break;
                    case 2:
                        pday = "Day after Tomorrow";
                      //  tvPD.setText(str);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }

        });
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, LArea);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(arrayAdapter1);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position1, long l) {
                switch (position1) {

                    case 0:
                        parea = "Kumbhamarg";
                        //tvPD.setText(str);
                        break;
                    case 1:
                        parea = "Indiagate";
                        //tvPD.setText(str);
                        break;
                    case 2:
                        parea = "Sitapura";
                        //  tvPD.setText(str);

                        break;
                }

            }




            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
        btnBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                paddress=etADD.getText().toString();
                //pk test begin
              //  pday=etADD.getText().toString();
               // services=etADD.getText().toString();
             //   parea=etADD.getText().toString();
              //  time1=etADD.getText().toString();
                //pk test end
                int selectedId=Rg.getCheckedRadioButtonId();
                Rbt1=(RadioButton)findViewById(selectedId);
                services=Rbt1.getText().toString();//:)
                new ordernow1().execute();
            }
        });
    }
    /*
    public void onRadioButtonClicked(View view)
    {
        boolean checked=((RadioButton)view ).isChecked();
        switch (view.getId())
        {
            case R.id.Rbt1:
                if(checked)
                {
                    services="Wash";
                }
            case R.id.Rbt2:
                if(checked)
                {
                    services="wash and iron";

                }
            case R.id.Rbt3:
                {
                    if(checked)
                    {
                        services="Dryclean";
                    }
                }
        }

    }*/
    class ordernow1 extends AsyncTask<Void,Void,Void>
    {
        String json = null;
        int success;
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog  = new ProgressDialog(Order_Now.this);
            pdialog.setTitle("process is running....");
            pdialog.setCancelable(false);
            pdialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            HashMap<String,String> map = new HashMap<>();
            map.put("services",services);
            map.put("pday", pday);
            map.put("parea",parea);
            map.put("ptime",time1);
            map.put("address",paddress);

            json = rh.sendPostRequest(URL,map);

            try
            {
                JSONObject jsonObject = new JSONObject(json);
                success = jsonObject.getInt("success");
                desc = jsonObject.getString("desc");

            }
            catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            //if(isCancelled())return;
            super.onPostExecute(aVoid);
            if (pdialog.isShowing())
                pdialog.cancel();
            Toast.makeText(Order_Now.this,desc,Toast.LENGTH_SHORT).show();

        }

    }




}

