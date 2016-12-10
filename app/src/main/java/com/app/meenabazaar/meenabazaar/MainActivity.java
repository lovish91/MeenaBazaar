package com.app.meenabazaar.meenabazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Toolbar mActionBarToolbar;
    Button newProduct;
    Button createInvoice;
    Button my_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newProduct = (Button) findViewById(R.id.addproduct);
        createInvoice=(Button) findViewById(R.id.createInvoice);
        my_cart=(Button) findViewById(R.id.my_cart);
        my_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),Cart_Activity.class);
                startActivity(i);
            }
        });
        newProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Invoice_Activity.class);
                startActivity(i);
            }
        });
        createInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Article_Activity.class);
                startActivity(i);
            }
        });
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("");

    }
}