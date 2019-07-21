package com.dell.nssbvm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/*
        "AlreadyLogin"  =>    key for finding whether you are alredy login or not

        same email  = not already login
        true = already login

*/

public class Login_Activity extends AppCompatActivity {

    String email, password;
    EditText UsernameTextBox, PasswordTextBox;
    SharedPreferences msharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        UsernameTextBox = findViewById(R.id.UserName_Id);
        PasswordTextBox = findViewById(R.id.Password_Id);

        msharedPreferences = getSharedPreferences("CheckForAlredyLogin", MODE_PRIVATE);

        // already Login then no need to go into login activity

        if (msharedPreferences.getBoolean("AlreadyLogin", false) != false) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    public void loginClicked(View view) {

        email = UsernameTextBox.getText().toString();
        password = PasswordTextBox.getText().toString();

        // getting data from server to verify valid user
        if(validityCheck())
            getDataFromApi();

    }

    public boolean validityCheck()
    {
        boolean valid = true;
        if(email.isEmpty())
        {
            valid = false;
            UsernameTextBox.setError("Email Must Not Be Empty");
        }
        if(password.isEmpty())
        {
            valid = false;
            PasswordTextBox.setError("Password Must Not Be Empty");
        }
        return valid;
    }

    public void createNewAccountClicked(View view) {
        // create new account
        Intent intent = new Intent(this, CreateNewAccount.class);
        startActivity(intent);
    }

    public void getDataFromApi() {
     //   String t_email = email.replace('@','_');
    //    String t_email_2 = email.replace();

      //  String url = "https://fast-peak-96163.herokuapp.com/login/";
               String url = "https://fast-peak-96163.herokuapp.com/login?email=" + email + "&password=" + password;

        final ProgressDialog dialog = new ProgressDialog(this);

        dialog.setMessage("Checking Login Credential");

        dialog.show();

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {

                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    dialog.dismiss();

                    if (status.equals("Yes")) {

                        SharedPreferences.Editor editor = msharedPreferences.edit();

                        // saved login until it is explicitly logout   // https://fast-peak-96163.herokuapp.com/login?email=abc@gmail.com&password=abc123
                        editor.putBoolean("AlreadyLogin", true);

                        editor.apply();

                        Intent intent = new Intent(Login_Activity.this, MainActivity.class);

                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);

                    }
                    if(status.equals("No")){
                        Toast.makeText(Login_Activity.this, "Wrong Email Or Password Try Again", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

      //  stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);


    }
}

/*
    https://735ad2a6-a-62cb3a1a-s-sites.googlegroups.com/site/anycarknowledge/Home/bugatti-veyron.jpg?attachauth=ANoY7cqK6Jty8QPrtiDT2DFGFY6SXwi6R7gm0YZ4Wm80R-1Lxb-wUL-7P35a_z8n9z6176y0bs_BwrKMYX8CRZZmvBVOrrastU4-sHxywpWOjS7wEjmfg3HC_m9GK8muz6AHnHoaIwfW3Cf0TncfZPrqmzXw4TdS57HreFu6iLrS3hHjLP2DXA7sY5tEQnCOxxqgL1KGio8dlPfXb7LvlzNcvHr4BZ7zDmXZmds8i9C4_yHfEWG6YXQ%3D&attredirects=0
*/