package com.app.meenabazaar.meenabazaar.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.meenabazaar.meenabazaar.R;
import com.app.meenabazaar.meenabazaar.model.Article;
import com.app.meenabazaar.meenabazaar.utils.SharedPrefs;

import java.util.List;

/**
 * Created by lovishbajaj on 09/12/16.
 */

public class CartItem_Adapter extends ArrayAdapter<Article> {
    String TAG = "Response";

    private Context context;
    List<Article> articles;
    SharedPrefs sharedPrefs;


        public CartItem_Adapter(Context context, List<Article> articles) {
            super(context, R.layout.cart_item, articles);
            this.context = context;
            this.articles = articles;
            sharedPrefs = new SharedPrefs();
        }
    private class ViewHolder {
        TextView productNameTxt;
        TextView productDescTxt;
        TextView productPriceTxt;
        TextView article_pp;
        }
    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Article getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cart_item, null);
            holder = new ViewHolder();
            holder.productNameTxt = (TextView) convertView
                    .findViewById(R.id.article_no_title);
            holder.productDescTxt = (TextView) convertView
                    .findViewById(R.id.article_desc);
            holder.productPriceTxt = (TextView) convertView
                    .findViewById(R.id.article_msp);
            holder.article_pp = (TextView) convertView
                    .findViewById(R.id.article_pp);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Article article = (Article) getItem(position);
        holder.productNameTxt.setText(article.getArticleNo());
        holder.productDescTxt.setText(article.getDescription());
        holder.productPriceTxt.setText(article.getMrp() );
        holder.article_pp.setText(article.getPurchasePrise());
        //Log.i(TAG, "onPostExecute" + article.getArticleNo() + article.getDescription() + article.getPurchasePrise()+article.getPurchasePrise());

		/*If a product exists in shared preferences then set heart_red drawable
		 * and set a tag*/
        //if (checkFavoriteItem(article_detail)) {
            //holder.article_pp.setImageResource(R.drawable.heart_red);
          //  holder.article_pp.setTag("red");
        //} else {
           // holder.favoriteImg.setImageResource(R.drawable.heart_grey);
          //  holder.article_pp.setTag("grey");
        //}

        return convertView;
    }

    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(Article checkProduct) {
        boolean check = false;
        List<Article> favorites = sharedPrefs.getCartItems(context);
        if (favorites != null) {
            for (Article articleDetail : favorites) {
                if (articleDetail.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    public void add(Article article_detail) {
        super.add(article_detail);
        articles.add(article_detail);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Article article_detail) {
        super.remove(article_detail);
        articles.remove(article_detail);
        notifyDataSetChanged();
    }
    }
