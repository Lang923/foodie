package com.herlangga.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private byte quantity = 0;
    private TextView quantityTextView;

    private String foodName = "Fried Rice";
    public static final String EXTRA_FOOD_NAME = "NAME";
    public static final String EXTRA_FOOD_QUANTITY = "QUANTITY";
    public static final int ORDER_REQUEST = 1;
    private String chefNote;
    private EditText chefNoteEditText;
    public static final String EXTRA_CHEF_NOTE = "CHEFNOTE";

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private boolean isTakeAway;
    public static final String EXTRA_DELIVERY_OPT = "DELIVERYOPT";
    private RecyclerView recyclerView;
    private ArrayList<Dish> dishes = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_recyclerView);
        recyclerView.setHasFixedSize(true);
        dishes.addAll(getDishes());
        showRecyclerList();



//        quantityTextView = findViewById(R.id.quantityTextView);
        chefNoteEditText = findViewById(R.id.chefNote_editText);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(LOG_TAG, "onCreate");


//        if (savedInstanceState != null) {
//            quantity = savedInstanceState.getByte("SAVED_QUANTITY");
//            quantityTextView.setText(Byte.toString(quantity));
//        }

    }
    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DishesAdapter dishAdapter = new DishesAdapter(dishes);
        recyclerView.setAdapter(dishAdapter);
    }
    private ArrayList<Dish> getDishes() {
        String[] dataName = getResources().getStringArray(R.array.names);
        String[] dataDescription =
                getResources().getStringArray(R.array.descriptions);
        int[] dataQuantity =
                getResources().getIntArray(R.array.quantities);
        int[] dataPrice = getResources().getIntArray(R.array.prices);
        TypedArray dataPhoto =
                getResources().obtainTypedArray(R.array.images);
        ArrayList<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Dish dish = new Dish();
            dish.setName(dataName[i]);
            dish.setDescription(dataDescription[i]);
            dish.setQuantity(dataQuantity[i]);
            dish.setPrice(dataPrice[i]);
            dish.setPhoto(dataPhoto.getResourceId(i, -1));
            dishes.add(dish);
        }
        return dishes;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putByte("SAVED_QUANTITY", quantity);
    }

    public void addOne(View view) {
        quantity ++;
        quantityTextView.setText(Byte.toString(quantity));
    }
    public void substractOne(View view) {
        quantity --;
        quantityTextView.setText(Byte.toString(quantity));
    }
    public void launchDetailActivity(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_FOOD_NAME, foodName);
        startActivityForResult(intent, ORDER_REQUEST);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            @Nullable Intent data
    ) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ORDER_REQUEST) {
            if (resultCode == RESULT_OK) {
                int orderRequest = data.getIntExtra(DetailActivity.EXTRA_ORDER,0);
                quantityTextView.setText(String.valueOf(orderRequest));
            }
        }
    }

    public void launchAboutActivity(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
    public void launchAboutActivity(MenuItem item) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void launchCartActivity(View view) {
        chefNote = chefNoteEditText.getText().toString();

        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra(EXTRA_FOOD_NAME, foodName);
        intent.putExtra(EXTRA_FOOD_QUANTITY, quantity);
        intent.putExtra(EXTRA_CHEF_NOTE, chefNote);
        startActivity(intent);
    }

    public void showCart(View view) {
        chefNote = chefNoteEditText.getText().toString();
        isTakeAway = true;
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra(EXTRA_CHEF_NOTE, chefNote);
        intent.putExtra(EXTRA_DELIVERY_OPT, isTakeAway);
        intent.putExtra("DISHES", dishes);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    public void setDiningOption(View view) {
        boolean radioChecked = ((RadioButton) view).isChecked();
        if (view.getId() == R.id.dine_radio) {
            if(radioChecked) isTakeAway = false;
        } else if (view.getId() == R.id.take_radio) {
            if (radioChecked) isTakeAway = true;
        }
    }

}