package com.example.userlist_1313;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    Button addUserBtn;
    Users users;
    //ArrayList<String> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = new Users(MainActivity.this);
        recyclerView = findViewById(R.id.recyclerView);
        addUserBtn = findViewById(R.id.addUserBtn);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormUserActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }
    @Override
    public void onResume(){
        super.onResume();
        userAdapter = new UserAdapter(users.getUserList());
        recyclerView.setAdapter(userAdapter);
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemTextView;
        String userName;
        int userPosition; // Для поиска пользователя в списке
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemView.setOnClickListener(this);
        }
        public void bind(String userName, int position){
            this.userName = userName;
            userPosition = position;
            itemTextView.setText(userName);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, InfoUserActivity.class);
            intent.putExtra("userPosition", userPosition);
            startActivity(intent);
        }
    }

    public class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        ArrayList<User> userList = new ArrayList<>();
        public UserAdapter(ArrayList<User> userList) {
            this.userList = userList;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new UserHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            User user = userList.get(position);
            String userName = user.getUserName()+" "+user.getUserLastName();
            userHolder.bind(userName, position);
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }
}