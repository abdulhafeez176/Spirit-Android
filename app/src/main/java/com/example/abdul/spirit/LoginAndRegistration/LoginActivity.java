package com.example.abdul.spirit.LoginAndRegistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.abdul.spirit.Modules.MainActivity;
import com.example.abdul.spirit.R;
import com.example.abdul.spirit.Utils.Constants;
import com.example.abdul.spirit.Utils.Requesthandler;
import com.example.abdul.spirit.Utils.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    // activity vars
    private AppCompatEditText login_username,login_password;
    private AppCompatButton login_button_login;
    private TextView login_button_reg;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //activity vars
        login_username = (AppCompatEditText)findViewById(R.id.login_username_edit);
        login_password = (AppCompatEditText)findViewById(R.id.login_pass_edit);
        login_button_login = (AppCompatButton)findViewById(R.id.login_button_login);
        login_button_reg = (TextView)findViewById(R.id.login_button_register);
        dialog = new ProgressDialog(this);

        login_button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            return;

        }

        login_button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegpage = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(gotoRegpage);

            }
        });

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
//            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            return;

        }




    }

    private void userLogin(){
        final String username = login_username.getText().toString().trim();
        final String password = login_password.getText().toString().trim();

        if (!username.isEmpty() && !password.isEmpty()){
            dialog.setMessage("Logging in");
            dialog.show();

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            dialog.dismiss();
                            try {
                                JSONObject obj = new JSONObject(response);
                                if(!obj.getBoolean("error")){
                                    SharedPrefManager.getInstance(getApplicationContext())
                                            .userLogin(
                                                    obj.getInt("id"),
                                                    obj.getString("username")
                                            );
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "Could not login",
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.dismiss();

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Could Not Login 1",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }

            };

            Requesthandler.getInstance(this).addToRequestQueue(stringRequest);
        }else {
            Toast.makeText(
                    getApplicationContext(),
                    "Enter credentials",
                    Toast.LENGTH_LONG
            ).show();

        }
        }



}
