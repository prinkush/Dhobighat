package com.example.alishagoyal.dhobighat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class


MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText etMob,etPassword;
    Button btnLogin,btnSignup;
    public static final String URL="http://10.0.2.2:8080/Internship/login.php";
    RequestHandler rh=new RequestHandler();
    ProgressDialog pDialog;
    String Mob,Password;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs=getApplicationContext().getSharedPreferences("MyPrefs",0);
        etMob= (EditText) findViewById(R.id.etMob);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnSignup=(Button) findViewById(R.id.btnSignup);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLogin:
            {
                Mob=etMob.getText().toString();
                Password=etPassword.getText().toString();
                new Login1().execute();
                break;
            }
            case R.id.btnSignup:
            {
                Intent i=new Intent(MainActivity.this,SignUp.class);
                startActivity(i);
                break;
            }
        }


    }
    class Login1 extends AsyncTask<Void,Void,Void>
    {
        String json=null;
        int success;
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog  = new ProgressDialog(MainActivity.this);
            pDialog.setTitle("process is running....");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HashMap<String,String> map=new HashMap<>();
            map.put("mobile_number",Mob);
            map.put("password",Password);
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
            super.onPostExecute(aVoid);
            if (success==0)
            {
                SharedPreferences.Editor editor=prefs.edit();
                editor.putString("mobile_number",Mob);
                editor.commit();
                Intent i=new Intent(MainActivity.this,homescreen.class);
                startActivity(i);
                finish();
            }
            else
            {
                if (pDialog.isShowing())
                    pDialog.cancel();
                Toast.makeText(MainActivity.this,desc,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
