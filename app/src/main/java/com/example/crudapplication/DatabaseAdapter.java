package com.example.crudapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList book_id, book_title, book_author, book_pages, book_days, contact_number;



    DatabaseAdapter( Activity activity, Context context, ArrayList book_id, ArrayList book_title,
                    ArrayList book_author, ArrayList book_pages, ArrayList book_days,
                    ArrayList contact_number){
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_pages = book_pages;
        this.book_author = book_author;
        this.book_days = book_days;
        this.contact_number = contact_number;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_layout_myrow, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.book_id_text.setText(String.valueOf(book_id.get(position)));
        holder.book_title.setText(String.valueOf(book_title.get(position)));
        holder.book_author.setText(String.valueOf(book_author.get(position)));
        holder.book_pages.setText(String.valueOf(book_pages.get(position)));
        holder.book_days.setText(String.valueOf(book_days.get(position)));
        holder.contact_number.setText(String.valueOf(contact_number.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(book_id.get(holder.getAdapterPosition())));
                intent.putExtra("title", String.valueOf(book_title.get(holder.getAdapterPosition())));
                intent.putExtra("author", String.valueOf(book_author.get(holder.getAdapterPosition())));
                intent.putExtra("pages", String.valueOf(book_pages.get(holder.getAdapterPosition())));
                intent.putExtra("days", String.valueOf(book_days.get(holder.getAdapterPosition())));
                intent.putExtra("contact", String.valueOf(contact_number.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {

        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_text, book_title, book_author, book_pages, book_days, contact_number;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            book_id_text = itemView.findViewById(R.id.book_id_text);
            book_title = itemView.findViewById(R.id.book_title_text);
            book_author = itemView.findViewById(R.id.book_author_text);
            book_pages = itemView.findViewById(R.id.book_pages_text);
            book_days = itemView.findViewById(R.id.book_days_text);
            contact_number = itemView.findViewById(R.id.contact_number_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
