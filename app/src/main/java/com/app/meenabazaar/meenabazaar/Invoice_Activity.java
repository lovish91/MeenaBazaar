package com.app.meenabazaar.meenabazaar;

import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.meenabazaar.meenabazaar.model.Article_Model;
import com.app.meenabazaar.meenabazaar.model.UserDetail;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Invoice_Activity extends AppCompatActivity {

    String TAG = "Response";
    String article;
    String SOAP_ACTION = "http://tempuri.org/IErpService/ArticleSearch";
    String METHOD_NAME = "ArticleSearch";
    String NAMESPACE = "http://tempuri.org/";
    String URL = "http://182.18.174.48/zordererpapi/ErpService.svc/basic";
    int Timeout = 50000;
    Toolbar mActionBarTool;
    UserDetail userDetail = new UserDetail();
    AutoCompleteTextView Article;
    Button button;
    TextView abcd;
    String Articleno = "ArticleNo";
    String Description = "description";
     SearchView searchViewAndroidActionBar;
    ListView Artic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        mActionBarTool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarTool);
        getSupportActionBar().setTitle("");
        mActionBarTool.setTitle("");
        abcd = (TextView)  findViewById(R.id.abcd);
        mActionBarTool.inflateMenu(R.menu.menu);
        Artic = (ListView) findViewById(R.id.artc);
        //Article = (AutoCompleteTextView) findViewById(R.id.search);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //article = Article.getText().toString();
               //AsyncCallWS task = new AsyncCallWS();
                //Call execute
                //task.execute();
            }
        });

    }

    private class AsyncCallWS extends AsyncTask<String, Void, List<Article_Model>> {


        @Override
        protected ArrayList<Article_Model> doInBackground(String... params) {
            Log.i(TAG, "doInBackground");
            // login();

            SoapObject x_deals;
            List<Article_Model> artclelist = new ArrayList<Article_Model>();
                artclelist.clear();

            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
                Request.addProperty("searchStr", article);
                Request.addProperty("rowCount", "5");



                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                // soapEnvelope.addMapping(NAMESPACE, "Bean", new UserDetail().getClass());

                HttpTransportSE transport = new HttpTransportSE(URL,Timeout);

                transport.call(SOAP_ACTION, soapEnvelope);
                SoapObject resultSOAP = (SoapObject) soapEnvelope.bodyIn;

                SoapObject root = (SoapObject) resultSOAP.getProperty(0);
                SoapObject s_deals = (SoapObject) root.getProperty("diffgram");
                SoapObject t_deals = (SoapObject) s_deals.getProperty("NewDataSet");
                int co = t_deals.getPropertyCount();

                for (int i=0; i <= co; i++ ){
                    x_deals = (SoapObject) t_deals.getProperty("tArticleSearch");
                    String ArticleNo = x_deals.getProperty("ArticleNo").toString();
                    String ExtDescription = x_deals.getProperty("ExtDescription").toString();

                   Article_Model xyz = new Article_Model(ArticleNo,ExtDescription);

                    artclelist.add(xyz);

                    // Log.i(TAG,co + t_deals.toString());
                    Log.i(TAG, ArticleNo +   ExtDescription);
                }

                Log.i(TAG,Integer.toString(artclelist.size()));

            return null;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
                return null;

        }

        @Override
        protected void onPostExecute(List<Article_Model> result) {
//           Log.i(TAG,Integer.toString(result.size()));
           // NewAdapter abc = new NewAdapter(getApplication(),result);
            //BookAutoCompleteAdapter bookAutoCompleteAdapter = new BookAutoCompleteAdapter(getApplicationContext(),result);
            //Artic.setAdapter(abc);
            //abc.notifyDataSetChanged();
            Log.i(TAG, "onPostExecute");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.menu_search);
         searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() > 2){
                    article = newText.toString();
                    //abcd.setText(abc);
                    AsyncCallWS task = new AsyncCallWS();
                    //Call execute
                    task.execute();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
