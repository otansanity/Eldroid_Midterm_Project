package com.example.crudapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DatabaseHelperClass myDB;
    ArrayList<String> book_id, book_title, book_author, book_pages, amount_OfDays, contact_Number;
    DatabaseAdapter databaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerVIew);
        add_button = findViewById(R.id.addBook_button);
        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddItemsActivity.class);
            startActivity(intent);
        });

        myDB = new DatabaseHelperClass(MainActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        amount_OfDays = new ArrayList<>();
        contact_Number = new ArrayList<>();

        storeDataInArrays();

        databaseAdapter = new DatabaseAdapter(MainActivity.this, MainActivity.this, book_id, book_title,
                book_author, book_pages, amount_OfDays, contact_Number);
        recyclerView.setAdapter(databaseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
                amount_OfDays.add(cursor.getString(4));
                contact_Number.add(cursor.getString(5));
            }
        }
    }
}