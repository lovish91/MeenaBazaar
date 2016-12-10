package com.app.meenabazaar.meenabazaar.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.meenabazaar.meenabazaar.R;
import com.app.meenabazaar.meenabazaar.model.Article;

import java.util.List;

/**
 * Created by lovishbajaj on 05/12/16.
 */

public class NewAdapter extends BaseAdapter {
    String TAG = "Response";

    Context context;
    LayoutInflater inflater;
    List<Article> articles;

    public NewAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    public int getCount() {
        return articles.size();
    }

    public Object getItem(int position) {
        return articles.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView productNameTxt;
        TextView productDescTxt;
        TextView productPriceTxt;
        TextView article_pp;
        ImageView btnFavourite;
    }

    public View getView(final int position, View convertView,ViewGroup parent) {

        final ViewHolder holder;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.cart_item, parent, false);
            holder = new ViewHolder();
            holder.productNameTxt = (TextView) convertView.findViewById(R.id.article_no_title);
            holder.productDescTxt = (TextView) convertView.findViewById(R.id.article_desc);
            holder.productPriceTxt = (TextView) convertView.findViewById(R.id.article_msp);
            //holder.btnFavo = (ImageView) convertView.findViewById(R.id.favouritesToggleImg);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.productNameTxt.setText(articles.get(position).getArticleNo());
        holder.productDescTxt.setText(articles.get(position).getMrp());
        holder.productPriceTxt.setText(articles.get(position).getMsp());
       // holder.btnFavourite.setImageResource(R.drawable.broken_link);

        return convertView;
    }
}

