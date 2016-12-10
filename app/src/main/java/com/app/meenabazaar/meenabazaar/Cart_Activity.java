package com.app.meenabazaar.meenabazaar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.meenabazaar.meenabazaar.Adapter.CartItem_Adapter;
import com.app.meenabazaar.meenabazaar.model.Article;
import com.app.meenabazaar.meenabazaar.utils.SharedPrefs;

import java.util.List;

public class Cart_Activity extends AppCompatActivity implements CartItem_Adapter.onItemsUpdated {

    String TAG = "Response";
    SharedPrefs sharedPrefs;
    List<Article> articles;
    ListView cartlist_view;
    CartItem_Adapter cartItem_adapter;
    TextView total;
    double j;

    @Override
    public void onItemsUpdate() {
        double totalValue = getGrandTotal(articles);
        total.setText(getResources().getString(R.string.Rs) + " " + totalValue);
    }

    public double getGrandTotal(List<Article> articles) {
        double total = 0;
        if (articles != null) {
            for (Article article : articles) {
                total += Double.parseDouble(article.getPurchasePrise());
            }
        }
        return total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartlist_view = (ListView) findViewById(R.id.cartlist_view);
        total = (TextView) findViewById(R.id.total_text);
        sharedPrefs = new SharedPrefs();
        articles = sharedPrefs.getCartItems(getBaseContext());
        if (articles == null) {
            Toast.makeText(getBaseContext(), "n", Toast.LENGTH_SHORT).show();
        } else {
            int i = articles.size();
            //Toast.makeText(getApplicationContext(), i +"",Toast.LENGTH_SHORT).show();
        }
        //NewAdapter newAdapter = new NewAdapter(getApplicationContext(),article_details);
        cartItem_adapter = new CartItem_Adapter(getBaseContext(), articles);
        cartItem_adapter.setItemsListener(this);
        cartlist_view.setAdapter(cartItem_adapter);
        double totalValue = getGrandTotal(articles);
        total.setText(getResources().getString(R.string.Rs) + " " + totalValue);
        //Toast.makeText(getApplicationContext(), j +"",Toast.LENGTH_SHORT).show();
        //sharedPrefs.removeFavorite(getApplicationContext(),article_details.get(position));
        cartlist_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article = (Article) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), article.toString(), Toast.LENGTH_SHORT).show();
                sharedPrefs.removeFavorite(getBaseContext(), articles.get(position));
                sharedPrefs.removeFavorite(getBaseContext(), article);
                cartItem_adapter.remove(articles.get(position));
            }
        });
    }
}
