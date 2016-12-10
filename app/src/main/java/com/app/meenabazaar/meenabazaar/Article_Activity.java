package com.app.meenabazaar.meenabazaar;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.meenabazaar.meenabazaar.model.Article;
import com.app.meenabazaar.meenabazaar.utils.SharedPrefs;
import com.squareup.picasso.Picasso;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Article_Activity extends AppCompatActivity {

    String TAG = "Response";
    String url="http://182.18.174.48/zordererpapi/ErpService.svc/basic";
    String namespace="http://tempuri.org/";
    String method = "ArticleInfo";
    String soap_action = "http://tempuri.org/IErpService/ArticleInfo";
    String articleId = "031000000000235";
    int Timeout = 50000;
    Button addtocart;
    Button gotocart;
    ImageView article_imageview;
    TextView article_No;
    TextView article_Description;
    TextView article_msp;
    TextView article_mrp;
    TextView article_purprice;
    Toolbar toolbar;
    SharedPrefs sharedPrefs;
    String ArticleId;
    String ArticleNo;
    String Description;
    String ArticleWSP;
    String ArticleMRP;
    String ArticlePurPrice;
    String Quatity;
    String MinStockUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        sharedPrefs = new SharedPrefs() ;

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        addtocart = (Button) findViewById(R.id.addtocart);
        gotocart = (Button) findViewById(R.id.gotocart);
        article_No = (TextView) findViewById(R.id.article_no);
        article_Description = (TextView) findViewById(R.id.article_Description);
        article_msp = (TextView) findViewById(R.id.article_msp);
        article_mrp = (TextView) findViewById(R.id.article_wsp);
        article_purprice = (TextView) findViewById(R.id.article_purprice);
        article_imageview = (ImageView) findViewById(R.id.article_image);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Async async = new Async();
                async.addtocart();
                //addtocart();
            }
        });
        gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Async async = new Async();
                async.execute();
            }
        });
    }
    private  class Async extends AsyncTask <String,Void,Void>{
        ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(String... params) {
            try {
                SoapObject Request = new SoapObject(namespace, method);
                Request.addProperty("articleId", articleId);
                //Request.addProperty("rowCount", "5");



                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                // soapEnvelope.addMapping(NAMESPACE, "Bean", new UserDetail().getClass());

                HttpTransportSE transport = new HttpTransportSE(url,Timeout);

                transport.call(soap_action, soapEnvelope);
                SoapObject resultSOAP = (SoapObject) soapEnvelope.bodyIn;

                SoapObject root = (SoapObject) resultSOAP.getProperty(0);
                SoapObject s_deals = (SoapObject) root.getProperty("diffgram");
                SoapObject t_deals = (SoapObject) s_deals.getProperty("NewDataSet");
                SoapObject x_deals = (SoapObject) t_deals.getProperty("tArticleInfo");

                ArticleId = x_deals.getProperty("ArticleId").toString();
                ArticleNo = x_deals.getProperty("ArticleNo").toString();
                Description = x_deals.getProperty("Description").toString();
                ArticleWSP = x_deals.getProperty("ArticleWSP").toString();
                ArticleMRP = x_deals.getProperty("ArticleMRP").toString();
                ArticlePurPrice = x_deals.getProperty("ArticlePurPrice").toString();
                // Log.i(TAG,co + t_deals.toString());
                Log.i(TAG, ArticleNo +  " "+ Description);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Article_Activity.this);
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
           // super.onPostExecute(aVoid);
            article_No.setText(ArticleNo);
            article_Description.setText(Description);
            article_mrp.setText(getResources().getString(R.string.Rs)+ ArticleWSP);
            article_msp.setText(getResources().getString(R.string.Rs)+ ArticleMRP);
            article_purprice.setText( getResources().getString(R.string.Rs)+ ArticlePurPrice);
            String uri= "http://182.18.174.48/image/"+"WOW-821-SPC-GGT-60GM"+".jpg";
            progressDialog.dismiss();
            Picasso.with(Article_Activity.this)
                    .load(uri)
                        //.resize(50,50)
                    .error(R.drawable.broken_link)
                    .into(article_imageview);
        }
        public void addtocart(){
            Article abc = new Article(ArticleId,ArticleNo,Description,Quatity,ArticleMRP,ArticleWSP,ArticlePurPrice);
            //abc.setArticleId(ArticleId);
            //abc.setArticleNo(ArticleNo);
            //abc.setDescription(Description);
            //abc.setMrp(ArticleMRP);
            //abc.setMsp(ArticleWSP);
            //abc.setPurchasePrise(ArticlePurPrice);
            sharedPrefs.addFavorite(getBaseContext(), abc);
            Log.i(TAG, "onPostExecute" + ArticleNo);
        }
    }

}

