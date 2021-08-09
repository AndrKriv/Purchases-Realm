package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    EditText shop_date;
    EditText shop_place;
    EditText shop_price;
    Button add;
    Button delete;
    RecyclerView recyclerView;
    ShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        realm = Realm.getInstance(new RealmConfiguration.Builder()
                .name("newDB.realm")// newDB.realm (3 столбца)
                .build());

        shop_date = findViewById(R.id.date);
        shop_place = findViewById(R.id.place);
        shop_price = findViewById(R.id.price);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        // создаем адаптер
        RealmResults<Shop_info> result =  realm.where(Shop_info.class).findAll();
        adapter = new ShopAdapter(this, result);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        // начальная инициализация списка
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addItem();
            }});
        delete.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
                delBut();
           }});
    }

    private void addItem() {
        if (getTrimmedDate().equals("")||
                getTrimmedPlace().equals("")||
                getTrimmedPrice().equals(""))
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                "Проверьте корректность введенных данных!\nПустое поле!", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            realm.beginTransaction();
            Shop_info info = realm.createObject(Shop_info.class);

            info.setDate(getTrimmedDate());
            info.setPlace(getTrimmedPlace());
            info.setPrice(getTrimmedPrice());

            realm.commitTransaction();

            RealmResults<Shop_info> result1 =
                    realm.where(Shop_info.class).findAll();
            for (Shop_info c : result1) {
                adapter = new ShopAdapter(this, result1);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    private void delBut(){
        if (getTrimmedDate().equals("")||
                getTrimmedPlace().equals("")||
                getTrimmedPrice().equals(""))
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Проверьте корректность введенных данных!", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            realm.beginTransaction();
            realm.where(Shop_info.class).equalTo("date", getTrimmedDate()).
                    equalTo("place", getTrimmedPlace()).
                    equalTo("price", getTrimmedPrice()).findAll().deleteAllFromRealm();
            realm.commitTransaction();
                recyclerView.setAdapter(adapter);
            }
    }

    private String getTrimmedPrice() {
        return shop_price.getText().toString().trim();
    }
    private String getTrimmedPlace() {
        return shop_place.getText().toString().trim();
    }
    private String getTrimmedDate() {
        return shop_date.getText().toString().trim();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
