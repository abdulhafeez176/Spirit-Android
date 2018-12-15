package com.example.abdul.spirit.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdul.spirit.CreateTeam.AddPlayer;
import com.example.abdul.spirit.R;


import java.util.List;

public class SinglePlayerViewItemAdapter extends ArrayAdapter<AddPlayer> {


    public static final String TAG = "SinglePlayerViewItemsAdapter";


    public SinglePlayerViewItemAdapter(@NonNull Context context, @NonNull List<AddPlayer> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        View addPlayerView = convertView;
        if (addPlayerView == null) {
            addPlayerView = LayoutInflater.from(getContext()).inflate(
                    R.layout.single_player_add_member_list_view, parent, false);
        }

        AddPlayer addPlayerItem = getItem(position);

        TextView playerUsernameView = addPlayerView.findViewById(R.id.player_user_name_view);
        playerUsernameView.setText(addPlayerItem.getUserName());
        TextView playerNameView = addPlayerView.findViewById(R.id.player_name_view);
        playerNameView.setText(addPlayerItem.getName());
        ImageView removePlayer = addPlayerView.findViewById(R.id.removePlayerButton);

        if (position == 0){
            removePlayer.setVisibility(View.GONE);

        }
        else {

            removePlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SinglePlayerViewItemAdapter.this.remove(getItem(position));
                    Constants.teamMembers -= 1;
                }
            });

        }

        return addPlayerView;

    }

}