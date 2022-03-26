package com.example.crudapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText updateTitle, updateAuthor, updatePages, updateAmountOfDays, updateContactNumber;
    Button updateButton, deleteButton;

    String id, title, author, pages, days, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateTitle = findViewById(R.id.editText_update_title);
        updateAuthor = findViewById(R.id.editText_update_author);
        updatePages = findViewById(R.id.editText_update_pages);
        updateAmountOfDays = findViewById(R.id.editText_update_borrowed);
        updateContactNumber = findViewById(R.id.editText_update_contactNumber);
        updateButton = findViewById(R.id.addBook_button);
        deleteButton = findViewById(R.id.deleteBook_button);

        // call this method first
        getAndSetIntentData();

        // set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        updateButton.setOnClickListener(view -> {
            // and only then call this method
            DatabaseHelperClass myDB = new DatabaseHelperClass(UpdateActivity.this);
            title = updateTitle.getText().toString().trim();
            author = updateAuthor.getText().toString().trim();
            pages = updatePages.getText().toString().trim();
            days = updateAmountOfDays.getText().toString().trim();
            contact = updateContactNumber.getText().toString().trim();
            myDB.updateData(id, title, author, pages, days, contact);
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages") &&
                getIntent().hasExtra("days") && getIntent().hasExtra("contact")){
            //Getting Data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            days = getIntent().getStringExtra("days");
            contact = getIntent().getStringExtra("contact");

            //Setting intent data
            updateTitle.setText(title);
            updateAuthor.setText(author);
            updatePages.setText(pages);
            updateAmountOfDays.setText(days);
            updateContactNumber.setText(contact);

        }else{
            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelperClass myDB = new DatabaseHelperClass(UpdateActivity.this);
                myDB.deleteDataRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}