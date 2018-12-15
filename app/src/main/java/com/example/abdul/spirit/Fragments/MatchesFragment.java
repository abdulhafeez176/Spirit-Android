package com.example.abdul.spirit.Fragments;

import android.content.Context;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdul.spirit.Utils.MyDividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.abdul.spirit.Modules.UpcomingMatch;
import com.example.abdul.spirit.R;
import com.example.abdul.spirit.Utils.Constants;
import com.example.abdul.spirit.Utils.Requesthandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MatchesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private List<UpcomingMatch> itemsList;
    private UpcomingMatchAdapter mAdapter;

    private String mParam1;
    private String mParam2;


    public MatchesFragment() {
        // Required empty public constructor
    }

    public static MatchesFragment newInstance(String param1, String param2) {
        MatchesFragment fragment = new MatchesFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        itemsList = new ArrayList<>();
        mAdapter = new UpcomingMatchAdapter(getActivity(), itemsList);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL,16));
        recyclerView.setAdapter(mAdapter);

        fetchUpcomingMatches();

        return view;
    }

    private void fetchUpcomingMatches() {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,Constants.GET_UPCOMING_MATCHES_URL,null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("TAG",response.toString());

                        try {

                            for(int i=0;i<response.length();i++){
                                JSONObject jresponse = response.getJSONObject(i);
                                UpcomingMatch match = new UpcomingMatch(jresponse.getString("team1"),jresponse.getString("team2"));
                                itemsList.add(match);
                                String nickname = jresponse.getString("team1");
                                Log.d("team1",jresponse.getString("team1"));
                                Log.d("team2",jresponse.getString("team2"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();

                        Toast.makeText(getActivity(), "Items Fetched", Toast.LENGTH_SHORT).show();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

        Requesthandler.getInstance(getContext()).addToRequestQueue(request);
    }

    class UpcomingMatchAdapter extends RecyclerView.Adapter<UpcomingMatchAdapter.MyViewHolder> {
        private Context context;
        private List<UpcomingMatch> matchList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView team1, team2;

            public MyViewHolder(View view) {
                super(view);
                team1 = (TextView) view.findViewById(R.id.team1);
                team2 = (TextView) view.findViewById(R.id.team2);

            }
        }


        public UpcomingMatchAdapter(Context context, List<UpcomingMatch> movieList) {
            this.context = context;
            this.matchList = movieList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.single_match_upcoming, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            final UpcomingMatch match = matchList.get(position);
            holder.team1.setText(match.getTeam1());
            holder.team2.setText(match.getTeam2());

        }

        @Override
        public int getItemCount() {
            return matchList.size();
        }
    }





}



