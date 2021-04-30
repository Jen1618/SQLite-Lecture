package com.example.sqlitelectureexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;
    private DatabaseHelper databaseHelper;
    private Context context;

    public UserAdapter(List<User> users, DatabaseHelper databaseHelper, Context context){
        this.users = users;
        this.context  = context;
        this.databaseHelper = databaseHelper;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.item_user, parent, false);
        ViewHolder viewHolder = new ViewHolder(userView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.textView_name.setText(user.getName());
        holder.textView_phone.setText(user.getPhone());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView_name;
        TextView textView_phone;
        Button button_delete;

        public ViewHolder(View item) {
            super(item);
            textView_name = item.findViewById(R.id.textView_name);
            textView_phone = item.findViewById(R.id.textView_phone);
            button_delete = item.findViewById(R.id.button_delete);
            button_delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // when button delete clicked, I want to call the deleteUser method on this user
            int selected = getAdapterPosition();
            User selectedUser = users.get(selected);
            databaseHelper.deleteUser(selectedUser);
            Toast.makeText(context, selectedUser.toString(), Toast.LENGTH_LONG).show();
            notifyDataSetChanged();
        }
    }
}
