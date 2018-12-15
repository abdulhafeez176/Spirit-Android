package com.example.abdul.spirit.CreateTeam;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import com.example.abdul.spirit.R;
import com.example.abdul.spirit.Utils.SinglePlayerViewItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreateTeam extends AppCompatActivity {


    private Button addMemberButton;
    private ListView addPlayersListView;
    private SinglePlayerViewItemAdapter addPlayersAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        addPlayersListView = findViewById(R.id.addPlayerslist);
        addMemberButton = findViewById(R.id.addMemberButton);

        List<AddPlayer> addPlayersList = new ArrayList<>();
        addPlayersAdapter = new SinglePlayerViewItemAdapter(getApplicationContext(),addPlayersList);

        addPlayersListView.setAdapter(addPlayersAdapter);


        addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddPlayerActivity.class);

            }
        });


    }
}
