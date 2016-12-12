package com.app.meenabazaar.meenabazaar;

import android.content.Intent;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.meenabazaar.meenabazaar.model.ArticleModel;
import com.app.meenabazaar.meenabazaar.model.UserDetail;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceActivity extends AppCompatActivity {

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
    private MaterialSearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        mActionBarTool = (Toolbar) findViewById(R.id.toolbarers);
        setSupportActionBar(mActionBarTool);
        getSupportActionBar().setTitle("");
        mActionBarTool.setTitle("new");
        abcd = (TextView)  findViewById(R.id.abcd);
        //mActionBarTool.inflateMenu(R.menu.menu);
        searchView = (MaterialSearchView) findViewById(R.id.search_views);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        //searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 2) {
                    article = newText.toString();
                    //abcd.setText(abc);
                    AsyncCallWS task = new AsyncCallWS();
                    //Call execute
                    task.execute();
                }
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

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

    private class AsyncCallWS extends AsyncTask<String, Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(String... params) {
            Log.i(TAG, "doInBackground");
            // login();
            //SoapObject x_deals;
            ArrayList<String> artclelist = new ArrayList<String>();
                artclelist.clear();

            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
                Request.addProperty("searchStr", article);
                Request.addProperty("rowCount", "8");



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

                for (int i=0; i < co; i++ ){
                    Object property = t_deals.getProperty(i);
                    if (property instanceof SoapObject)
                    {
                        //SoapObject x_deals = (SoapObject) t_deals.getProperty("tArticleSearch");
                        SoapObject x_deals = (SoapObject) property;
                        String ArticleNo = x_deals.getProperty("ArticleNo").toString();
                        String ExtDescription = x_deals.getProperty("ExtDescription").toString();
                        //vector.addElement(ArticleNo);
                        //vector.addElement(ExtDescription);
                        //ArticleModel xyz = new ArticleModel(ArticleNo,ExtDescription);

                        artclelist.add(ArticleNo);

                        Log.i(TAG, co + ArticleNo.toString());
                        //Log.i(TAG, t_deals.toString());
                    }

                }
               // Log.i(TAG, x_deals.toString());
                Log.i(TAG,Integer.toString(artclelist.size()));

            return artclelist;

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
        protected void onPostExecute(ArrayList<String> artclelist) {
           Log.i(TAG,Integer.toString(artclelist.size()));

           // NewAdapter abc = new NewAdapter(getApplication(),result);
            //BookAutoCompleteAdapter bookAutoCompleteAdapter = new BookAutoCompleteAdapter(getApplicationContext(),result);
            //Artic.setAdapter(abc);
            //abc.notifyDataSetChanged();
            String[] stringArray = artclelist.toArray(new String[0]);
            searchView.setSuggestions(stringArray);
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
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
