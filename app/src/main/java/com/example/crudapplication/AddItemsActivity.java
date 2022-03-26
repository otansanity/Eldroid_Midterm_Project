package com.example.crudapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemsActivity extends AppCompatActivity {

    EditText title, author, pages, amountOfDays, contactNumber;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        title = findViewById(R.id.editText_update_title);
        author = findViewById(R.id.editText_update_author);
        pages = findViewById(R.id.editText_update_pages);
        amountOfDays = findViewById(R.id.editText_update_borrowed);
        contactNumber = findViewById(R.id.editText_update_contactNumber);
        addButton = findViewById(R.id.addBook_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelperClass myDB = new DatabaseHelperClass(AddItemsActivity.this);
                myDB.addBook (title.getText().toString().trim(),
                    author.getText().toString().trim(),
                    Integer.valueOf(pages.getText().toString().trim()),
                    Integer.valueOf(amountOfDays.getText().toString().trim()),
                    Integer.valueOf(contactNumber.getText().toString().trim()));
            }
        });
    }
}