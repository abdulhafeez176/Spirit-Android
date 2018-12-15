package com.example.abdul.spirit.CreateTeam;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.abdul.spirit.R;
import com.example.abdul.spirit.Utils.Constants;
import com.example.abdul.spirit.Utils.SinglePlayerViewItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreateTeam extends AppCompatActivity {


    private AppCompatButton addMemberButton;
    private ListView addPlayersListView;
    private AppCompatEditText teamNameView;
    private AppCompatEditText teamContactView;
    private AppCompatEditText teamEmailView;
    private AppCompatEditText teamLocationView;
    private AppCompatEditText teamAddressView;
    private int teamMemberCount;

    private SinglePlayerViewItemAdapter addPlayersAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        addPlayersListView = findViewById(R.id.addPlayerslist);
        addMemberButton = findViewById(R.id.addMemberButton);
        teamNameView = findViewById(R.id.new_team_name);
        teamEmailView = findViewById(R.id.new_team_email);
        teamAddressView = findViewById(R.id.new_team_address);
        teamLocationView = findViewById(R.id.new_team_location);
        teamContactView = findViewById(R.id.new_team_contact);


        List<AddPlayer> addPlayersList = new ArrayList<>();
        addPlayersAdapter = new SinglePlayerViewItemAdapter(getApplicationContext(),addPlayersList);

        addPlayersListView.setAdapter(addPlayersAdapter);



        AddPlayer player = new AddPlayer(Constants.name,Constants.userName);
        addPlayersAdapter.add(player);
        Constants.teamMembers +=1;


        addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Constants.teamMembers < 15) {
                    Intent i = new Intent(getApplicationContext(),AddPlayerActivity.class);
                    startActivityForResult(i,0);

                }
                else {
                    Toast.makeText(getApplicationContext(),"You cannot add more than 15 players",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == Activity.RESULT_OK) {

                String name = data.getStringExtra("name");
                String username = data.getStringExtra("username");
                AddPlayer player = new AddPlayer(name,username);
                addPlayersAdapter.add(player);
                Constants.teamMembers +=1;

            } else if (resultCode == Activity.RESULT_CANCELED) {

            }
        }

    }

    @Override
    public void onBackPressed() {
        Constants.teamMembers = 0;
        super.onBackPressed();
    }
}
