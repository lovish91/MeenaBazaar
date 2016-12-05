package com.app.meenabazaar.meenabazaar;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Iterator;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {

    String TAG = "Response";
    EditText userIdet;
    EditText userPasswrd;
    Button login;
    String emailid;
    String passwrd;
    SoapObject resultString;
    TextView userid;
    TextView username;
    String USERNAME;
    String EMAILID;
    String USERID;
    String SOAP_ACTION = "http://tempuri.org/IMISService/UserLogin";
    String METHOD_NAME = "UserLogin";
    String NAMESPACE = "http://tempuri.org/";
    String URL = "http://182.18.174.48/zordermisapi/MISService.svc/basic";
    private Bundle bundleResult=new Bundle();
    private JSONObject JSONObj;
    private JSONArray JSONArr;
    UserDetail userDetail = new UserDetail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userid = (TextView) findViewById(R.id.email) ;
        username = (TextView) findViewById(R.id.userName);
        userIdet = (EditText) findViewById(R.id.useridET);
        userPasswrd = (EditText) findViewById(R.id.passwordET);
        login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    //emailid = userIdet.getText().toString();
                    //passwrd = userPasswrd.getText().toString();

                    //AsyncCallWS task = new AsyncCallWS();

                    //task.execute();


                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            Log.i(TAG, "doInBackground");
            //getFahrenheit(celcius);
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);
           // login();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            //tv.setText(fahren + "° F");
            //userid.setText(userDetail.UserId);
            //username.setText(USERNAME);
            //username.setText(userDetail.);
        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");

            //tv.setText("Calculating...");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i(TAG, "onProgressUpdate");
        }

    }

    public void login() {

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("emailId", emailid);
            Request.addProperty("password",passwrd);


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);
           // soapEnvelope.addMapping(NAMESPACE, "Bean", new UserDetail().getClass());

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            SoapObject resultSOAP = (SoapObject) soapEnvelope.bodyIn;

            SoapObject root = (SoapObject) resultSOAP.getProperty(0);
            SoapObject s_deals = (SoapObject) root.getProperty("diffgram");
            SoapObject t_deals = (SoapObject) s_deals.getProperty("NewDataSet");
            SoapObject x_deals = (SoapObject) t_deals.getProperty("tUserInfo");

            userDetail.UserId=x_deals.getProperty("UserId").toString();
             EMAILID = x_deals.getProperty("EmailId").toString();
             //USERID = x_deals.getProperty("UserId").toString();
             USERNAME = x_deals.getProperty("UserName").toString();

            int co = x_deals.getPropertyCount();
            Log.i(TAG, EMAILID + USERID +USERNAME+ x_deals.toString());

        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }

    }
}
