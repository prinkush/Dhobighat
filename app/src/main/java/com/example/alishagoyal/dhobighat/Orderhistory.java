package com.example.alishagoyal.dhobighat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Orderhistory extends AppCompatActivity {
    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> services = new ArrayList<String>(0);
    ImageView ivarrow3;

    public static final String URL = "http://10.0.2.2:8080/Internship/orderhistory.php";
    RequestHandler rh=new RequestHandler();
    ProgressDialog pdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);
        ivarrow3=(ImageView) findViewById(R.id.ivarrow3);
        ivarrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Orderhistory.this,homescreen.class);
                startActivity(intent);
            }
        });

        new orderhistory1().execute();

    }
    class orderhistory1 extends AsyncTask<Void,Void,Void>
    {   JSONArray arr;
        String json = null;
        int success;
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog  = new ProgressDialog(Orderhistory.this);
            pdialog.setTitle("process is running....");
            pdialog.setCancelable(false);
            pdialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            //   HashMap<String,String> map = new HashMap<>();

            json = rh.sendGetRequest(URL);

            try
            {
                arr=new JSONArray(json);

                for(int i=0;i<arr.length();i++) {
                    try {
                        JSONObject jsonObject = arr.getJSONObject(i);
                        String temp="Status: ";
                        if(jsonObject.getInt("isReturned")==0)temp+="Yet to be Returned";
                        else temp+="Returned";
                        services.add("ORDER_ID:"+jsonObject.getInt("oid")+"\t\t\tService type: "+jsonObject.getString("services").toString()+"\nPickup Location: "+jsonObject.getString("parea").toString()+"\nPickup Time: "+jsonObject.getString("ptime").toString()+"\t\t\t\t"+temp);
                        //add similarly
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                   /* success = jsonObject.getInt("success");
                desc = jsonObject.getString("desc");*/

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
            mArrayAdapter = new ArrayAdapter<String>(Orderhistory.this,android.R.layout.simple_expandable_list_item_1,services);
            ListView lv=(ListView)findViewById(R.id.lv2);
            lv.setAdapter(mArrayAdapter);

        }

    }

}
