package com.app.meenabazaar.meenabazaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.meenabazaar.meenabazaar.Adapter.CartItem_Adapter;
import com.app.meenabazaar.meenabazaar.model.Article;
import com.app.meenabazaar.meenabazaar.utils.SharedPrefs;

import java.util.List;

public class Cart_Activity extends AppCompatActivity {

    String TAG = "Response";
    SharedPrefs sharedPrefs;
    List<Article> articles;
    ListView cartlist_view;
    CartItem_Adapter cartItem_adapter;
    TextView total;
    double j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartlist_view = (ListView) findViewById(R.id.cartlist_view);
        total = (TextView) findViewById(R.id.total_text);
        sharedPrefs = new SharedPrefs();
        articles = sharedPrefs.getCartItems(getBaseContext());
        if (articles == null){
            Toast.makeText(getBaseContext(),"n",Toast.LENGTH_SHORT).show();
        }else{
            int i = articles.size();
            //Toast.makeText(getApplicationContext(), i +"",Toast.LENGTH_SHORT).show();
        }
        //NewAdapter newAdapter = new NewAdapter(getApplicationContext(),article_details);
        cartItem_adapter = new CartItem_Adapter(getBaseContext(), articles);
        cartlist_view.setAdapter(cartItem_adapter);
       for (Article article : articles){
                double i = Double.parseDouble(article.getPurchasePrise());
           j += i;
        }
        total.setText(getResources().getString(R.string.Rs)+ " "+j);
        //Toast.makeText(getApplicationContext(), j +"",Toast.LENGTH_SHORT).show();
        //sharedPrefs.removeFavorite(getApplicationContext(),article_details.get(position));
        cartlist_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article = (Article) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), article.toString(),Toast.LENGTH_SHORT).show();
                sharedPrefs.removeFavorite(getBaseContext(),articles.get(position));
                sharedPrefs.removeFavorite(getBaseContext(),article);
                cartItem_adapter.remove(articles.get(position));
            }
        });
    }
}
