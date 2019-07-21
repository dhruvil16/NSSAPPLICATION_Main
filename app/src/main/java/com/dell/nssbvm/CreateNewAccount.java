package com.dell.nssbvm;

import android.app.ProgressDialog;
import android.content.Intent;
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


public class CreateNewAccount extends AppCompatActivity {
    EditText UsernameTextBox, EmailTextBox, PasswordTextBox, PhoneTextbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        UsernameTextBox = findViewById(R.id.Signup_UserName_Id);
        EmailTextBox = findViewById(R.id.Signup_Email_Id);
        PasswordTextBox = findViewById(R.id.Signup_Password_Id);
        PhoneTextbox = findViewById(R.id.Signup_Contact_Id);


    }


    public Boolean validateInfo() {
        Boolean valid = true;
        String username, email, password, phone;
        username = UsernameTextBox.getText().toString().trim();
        email = EmailTextBox.getText().toString().trim();
        password = PasswordTextBox.getText().toString().trim();
        phone = PhoneTextbox.getText().toString().trim();

        if (username.isEmpty()) {
            valid = false;
            UsernameTextBox.setError("Enter Username");
        }
        if (email.isEmpty()) {
            valid = false;
            EmailTextBox.setError("Enter Email");
        }

        if (password.isEmpty()) {
            valid = false;
            PasswordTextBox.setError("Enter Password");
        }
        if (phone.isEmpty()) {
            valid = false;
            PhoneTextbox.setError("Enter PhoneNumber");
        }
        if (phone.length() != 10) {
            valid = false;
            PhoneTextbox.setError("Phone Number Must Be of Length 10");
        }
        return valid;
    }


    public void tryToSignUp(View view) {

        if(validateInfo())
        {


            final ProgressDialog dialog = new ProgressDialog(this);

            dialog.setMessage("Creating New Account");

            dialog.show();

            String username, email, password, phone;
            username = UsernameTextBox.getText().toString().trim();
            email = EmailTextBox.getText().toString().trim();
            password = PasswordTextBox.getText().toString().trim();
            phone = PhoneTextbox.getText().toString().trim();


            String url = "https://fast-peak-96163.herokuapp.com/signup?email="+email+"&password="+password+"&contact="+phone+"&name="+username;

            RequestQueue requestQueue = Volley.newRequestQueue(CreateNewAccount.this);

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {

                        JSONObject jsonObject = new JSONObject(response);

                        // email pass con name
                       String ans = jsonObject.getString("status");

                       dialog.dismiss();

                       if(ans.equals("Yes"))
                       {
                           Toast.makeText(CreateNewAccount.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(CreateNewAccount.this,Login_Activity.class);
                           startActivity(intent);
                       }
                       else
                       {
                           Toast.makeText(CreateNewAccount.this, " Invalid Email Address ", Toast.LENGTH_SHORT).show();
                       }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    // response.
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            requestQueue.add(request);

        }
    }
}