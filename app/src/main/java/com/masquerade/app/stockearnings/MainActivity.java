package com.masquerade.app.stockearnings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.masquerade.app.stockearnings.adapters.StockCardRecyclerViewAdapter;
import com.masquerade.app.stockearnings.models.Stock;

import java.util.ArrayList;
import java.util.Locale;

import com.masquerade.app.stockearnings.activities.AddStockActivity;

public class MainActivity extends AppCompatActivity {

    RecyclerView stockRecyclerView;
    StockCardRecyclerViewAdapter stockCardRecyclerViewAdapter;
    TextView netProfitTextView;
    FloatingActionButton addStockBUtton;
    ArrayList<Stock> stockData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*      Floating action button task                     */
        addStockBUtton = findViewById(R.id.fab);
        addStockBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addStockIntent = new Intent(MainActivity.this, AddStockActivity.class);
                startActivity(addStockIntent);
            }
        });

        /*      Floating action button task                     */
        /*
         * @todo
         *   Create a dedicated user class which will do the following
         *       - Fetch user subscribed stocks
         *       - Create an account in firebase so that all data stays online
         *       - Have support for google account sign in
         *       - Store the data online as well as local storage
         * */
        /*      To populate the recycler view with card         */
        stockRecyclerView = findViewById(R.id.stock_card_recyclerview);
        stockRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        netProfitTextView = findViewById(R.id.profit_amount);
        ArrayList<Stock> stockList = getUserSubscribedStockList();
        double netProfit = getNetProfit(stockList);
        netProfitTextView.setText(String.format(Locale.ENGLISH, "%.2f", netProfit));
        stockCardRecyclerViewAdapter = new StockCardRecyclerViewAdapter(this, stockList);
        stockRecyclerView.setAdapter(stockCardRecyclerViewAdapter);
        /*      To populate the recycler view with card         */


    }

    private double getNetProfit(ArrayList<Stock> stockList) {
        double prof = 0.0;
        for (int i = 0; i < stockList.size(); i++) {
            prof += stockList.get(i).getNetProfit();
        }
        return prof;
    }

    protected ArrayList<Stock> getUserSubscribedStockList() {
        ArrayList<Stock> temp = new ArrayList<>();
        temp.add(new Stock("500696 ", "Hindustan Uniliver", 183.57
                , 0, 100, 183.62));
        temp.add(new Stock("532540", "TCS Ltd", 1012.02
                , 30, 10, 2156.5));
        temp.add(new Stock("500325 ", "Reliance Industries", 1043.8
                , 0, 25, 900));
        return temp;
    }
}