package com.example.abdul.spirit.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdul.spirit.CreateTeam.CreateTeam;
import com.example.abdul.spirit.LoginAndRegistration.LoginActivity;
import com.example.abdul.spirit.LoginAndRegistration.RegisterActivity;
import com.example.abdul.spirit.Modules.MainActivity;
import com.example.abdul.spirit.R;
import com.example.abdul.spirit.Utils.Constants;
import com.example.abdul.spirit.Utils.SharedPrefManager;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AppCompatButton btn;
    private TextView profile_name_user,profile_user_age,profile_user_role,profile_user_username,profile_user_contact;
    private Button registerButton;

    private String name_of_user,role_of_user,username_of_user,contact_of_user,age_of_user;

    private int isOwner;



    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if (SharedPrefManager.getInstance(getContext()).isLoggedIn()){
            name_of_user = SharedPrefManager.getInstance(getContext()).getNameOfUser();
            age_of_user = String.valueOf(SharedPrefManager.getInstance(getContext()).getageOfUser());
            role_of_user = SharedPrefManager.getInstance(getContext()).getroleOfUser();
            username_of_user = SharedPrefManager.getInstance(getContext()).getUsername();
            contact_of_user = SharedPrefManager.getInstance(getContext()).getUserContact();
            isOwner = SharedPrefManager.getInstance(getContext()).userHasTeam();

            Constants.name = name_of_user;
            Constants.userName = username_of_user;



        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);
        profile_name_user = view.findViewById(R.id.name_user_view);
        profile_user_age = view.findViewById(R.id.profile_user_age);
        profile_user_role = view.findViewById(R.id.profile_user_role);
        profile_user_contact = view.findViewById(R.id.profile_user_contact);
        profile_user_username = view.findViewById(R.id.profile_user_username);
        btn = (AppCompatButton)view.findViewById(R.id.profile_register_team);
        registerButton = view.findViewById(R.id.profile_register_team);


        profile_name_user.setText(name_of_user);
        profile_user_age.setText(age_of_user);
        profile_user_role.setText(role_of_user);
        profile_user_username.setText(username_of_user);
        profile_user_contact.setText(contact_of_user);

        if (isOwner == 0){
            btn.setText("Register New Team");
        }else if(isOwner == 1){
            btn.setText("Manage Team");

        }
        else {
            btn.setText("Shit Happened");

        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),CreateTeam.class);
                startActivity(i);
            }
        });

        return view;

    }

    @Override
    public void onClick(View v) {
        SharedPrefManager.getInstance(getContext()).logout();
        Intent gotoLogPage = new Intent(getContext(),LoginActivity.class);
        gotoLogPage.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(gotoLogPage);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_toolbar_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_btn:
                SharedPrefManager.getInstance(getContext()).logout();
                Intent gotoLoginPage = new Intent(getActivity(),LoginActivity.class);
                gotoLoginPage.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(gotoLoginPage);
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO: Rename method, update argument and hook method into UI event
}
