package com.example.sqlitelectureexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button_viewAll, button_add;
    private RecyclerView recyclerView_contact;
    private EditText editText_name, editText_phone;
    private DatabaseHelper databaseHelper;
    private List<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_viewAll = findViewById(R.id.button_viewall);
        button_add = findViewById(R.id.button_add);
        recyclerView_contact = findViewById(R.id.recyclerView_contact);
        editText_name = findViewById(R.id.editText_name);
        editText_phone = findViewById(R.id.editText_phone);

        // file extension for database - ends with .db
        databaseHelper = new DatabaseHelper(MainActivity.this, "users.db", null, 1);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // grab the edit text
                // add the user to the database
                String name = editText_name.getText().toString();
                String phone = editText_phone.getText().toString();
                if(name.isEmpty() || phone.isEmpty()){
                    Toast.makeText(MainActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    long rowID = databaseHelper.addUser(new User(name, phone));
                    Toast.makeText(MainActivity.this, "RowID: " + rowID, + Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users = databaseHelper.getAllUsers();
                Toast.makeText(MainActivity.this, users.toString(), Toast.LENGTH_LONG).show();

                UserAdapter adapter = new UserAdapter(users, databaseHelper, MainActivity.this);
                recyclerView_contact.setAdapter(adapter);
                recyclerView_contact.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL);
                recyclerView_contact.addItemDecoration(itemDecoration);

            }
        });
    }
}