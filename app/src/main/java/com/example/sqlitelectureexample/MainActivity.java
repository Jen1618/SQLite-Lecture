package com.example.sqlitelectureexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button_viewAll, button_add;
    private RecyclerView recyclerView_contact;
    private EditText editText_name, editText_phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_viewAll = findViewById(R.id.button_viewall);
        button_add = findViewById(R.id.button_add);
        recyclerView_contact = findViewById(R.id.recyclerView_contact);
        editText_name = findViewById(R.id.editText_name);
        editText_phone = findViewById(R.id.editText_phone);
    }
}