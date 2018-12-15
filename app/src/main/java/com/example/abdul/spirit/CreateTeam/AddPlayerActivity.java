package com.example.abdul.spirit.CreateTeam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.abdul.spirit.R;

public class AddPlayerActivity extends AppCompatActivity {

    private Button addPlayerButton;
    private TextView playerUsernameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        playerUsernameView = findViewById(R.id.add_player_username_view);
        addPlayerButton = findViewById(R.id.add_player_button);

        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = playerUsernameView.getText().toString().trim();
                Intent i = new Intent();
                i.putExtra("username",userName);
                i.putExtra("Name","ToBeAddedLater");
                setResult(RESULT_OK,i);
                finish();

            }
        });

    }





}
