package com.example.alishagoyal.dhobighat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignUp extends AppCompatActivity
{
    EditText etName,etEmail,etPass,etConpass,etNum,etCity;
    Button bt1;
    ImageView ivarrowsignup;

    public static final String URL = "http://10.0.2.2:8080/Internship/Addcontacts.php";
    RequestHandler rh=new RequestHandler();
    ProgressDialog pdialog;
    AsyncTask<Void, Void, Void> task;
    String name,email,password,conpassword,number,city,emailPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ivarrowsignup=(ImageView) findViewById(R.id.ivarrowsignup);
        ivarrowsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,homescreen.class);
                startActivity(intent);
            }
        });

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        etConpass = (EditText) findViewById(R.id.etConpass);
        etNum = (EditText) findViewById(R.id.etNum);
        bt1 = (Button) findViewById(R.id.bt1);
        etCity=(EditText) findViewById(R.id.etCity);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                emailPattern="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                password = etPass.getText().toString();
                conpassword = etConpass.getText().toString();
                number = etNum.getText().toString();
                city = etCity.getText().toString();
               task= new SignUp1().execute();
            }
        });

    }

       class SignUp1 extends AsyncTask<Void,Void,Void>
       {
           String json = null;
           int success;
           String desc;

           @Override
           protected void onPreExecute() {
               super.onPreExecute();
               pdialog  = new ProgressDialog(SignUp.this);
               pdialog.setTitle("process is running....");
               pdialog.setCancelable(false);
               pdialog.show();
           }

           @Override
           protected Void doInBackground(Void... params)
           {
               HashMap<String,String> map = new HashMap<>();
               map.put("name",name);
               if(email.matches(emailPattern))
               {
                   map.put("email", email);
               }
               else
               {    map.put("email","-1");
                   //Toast.makeText(SignUp.this,"Invalid Email Address",Toast.LENGTH_SHORT).show();
                   //task.cancel(true);
               }
               map.put("city",city);
             //  map.put("password",password);
              if(password.equals(conpassword))
               {
                   map.put("password",password);
               }
               else
               {    map.put("password","-1");
                   //Toast.makeText(SignUp.this,"password does not match...",Toast.LENGTH_SHORT).show();
                   //task.cancel(true);
               }
               map.put("mobile_number",number);

               json = rh.sendPostRequest(URL,map);
              // String test="http://127.0.0.1/Internship/Addcontacts.php?name=Alisha&email=cghjb&password=12345&mobile_number=99977";
               //json = (test);

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
               Toast.makeText(SignUp.this,desc,Toast.LENGTH_SHORT).show();

           }
       }

}
