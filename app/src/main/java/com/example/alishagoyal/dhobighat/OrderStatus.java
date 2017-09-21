package com.example.alishagoyal.dhobighat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderStatus extends AppCompatActivity {
    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> services = new ArrayList<String>(0);
    ImageView ivarrow2;
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    public static final String URL = "http://10.0.2.2:8080/Internship/orderstatus.php";
    RequestHandler rh=new RequestHandler();
    ProgressDialog pdialog;
//    AsyncTask<Void, Void, Void> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        ivarrow2=(ImageView) findViewById(R.id.ivarrow2);
        ivarrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderStatus.this,homescreen.class);
                startActivity(intent);
            }
        });
        new orderstatus1().execute();


/*

        for(int i=0; i < mobileArray.length ; i++) {
           // json_data = jArray.getJSONObject(i);
            //int id=json_data.getInt("id");
            String name=mobileArray[i];
            items.add(name);
           // Log.d(name,"Output");
        }

        mArrayAdapter = new ArrayAdapter<String>(OrderStatus.this,android.R.layout.simple_expandable_list_item_1,items);
        ListView lv=(ListView)findViewById(R.id.lv1);
       lv.setAdapter(mArrayAdapter);
*/



        ////lv.setAdapter(adapter);
    }

    ///////////////////////////////////////////
    class orderstatus1 extends AsyncTask<Void,Void,Void>
    {   JSONArray arr;
        String json = null;
        int success;
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog  = new ProgressDialog(OrderStatus.this);
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
                        services.add("ORDER_ID:"+jsonObject.getInt("oid")+"\t\t\tService type: "+jsonObject.getString("services").toString()+"\nPickup Location: "+jsonObject.getString("parea").toString()+"\nPickup Time: "+jsonObject.getString("ptime").toString());
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
            mArrayAdapter = new ArrayAdapter<String>(OrderStatus.this,android.R.layout.simple_expandable_list_item_1,services);
            ListView lv=(ListView)findViewById(R.id.lv1);
            lv.setAdapter(mArrayAdapter);

        }

    }






}