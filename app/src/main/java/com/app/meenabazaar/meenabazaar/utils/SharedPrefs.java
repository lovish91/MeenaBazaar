package com.app.meenabazaar.meenabazaar.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.meenabazaar.meenabazaar.model.Article;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lovishbajaj on 09/12/16.
 */

public class SharedPrefs {
    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String CART = "cart_item";

    public SharedPrefs() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, ArrayList<Article> cart_items) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(cart_items);

        editor.putString(CART, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, Article article) {
        ArrayList<Article> cart_items = getCartItems(context);
        if (cart_items == null) {
            cart_items = new ArrayList<Article>();
        }
        cart_items.add(article);
        saveFavorites(context, cart_items);
    }

    public void removeFavorite(Context context, Article article) {
        ArrayList<Article> cart_items = getCartItems(context);
        if (cart_items != null) {
            cart_items.remove(article);
            saveFavorites(context, cart_items);
        }
    }

    public ArrayList<Article> getCartItems(Context context) {
        SharedPreferences settings;
        List<Article> cart_items;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(CART)) {
            String jsonFavorites = settings.getString(CART, null);
            Gson gson = new Gson();
            Article[] cartItems = gson.fromJson(jsonFavorites,
                    Article[].class);

            cart_items = Arrays.asList(cartItems);
            cart_items = new ArrayList<>(cart_items);
        } else {
            return null;
        }

        return (ArrayList<Article>) cart_items;
    }
}
