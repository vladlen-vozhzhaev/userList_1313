package com.example.userlist_1313;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UserInfoFragment extends Fragment {
    TextView fioTextView;
    TextView phoneTextView;
    Button editUserBtn;
    Button deleteUserBtn;
    Users users;
    User user;
    int userPosition;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_info_user, viewGroup, false);
        fioTextView = view.findViewById(R.id.fioTextView);
        phoneTextView = view.findViewById(R.id.phoneTextView);
        editUserBtn = view.findViewById(R.id.editUserBtn);
        deleteUserBtn = view.findViewById(R.id.deleteUserBtn);
        String fio = user.getUserLastName()+" "+user.getUserName();
        fioTextView.setText(fio);
        phoneTextView.setText(user.getPhone());
        return view;
    }
}
