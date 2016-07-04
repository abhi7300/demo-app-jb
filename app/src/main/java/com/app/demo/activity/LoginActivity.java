package com.app.demo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.demo.Constant.Constant;
import com.app.demo.R;
import com.app.demo.util.Util;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private EditText password;
    private EditText userId;
    private Util util;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.userId = ((EditText)findViewById(R.id.username));
        this.password = ((EditText)findViewById(R.id.password));
        this.loginButton = ((Button)findViewById(R.id.login_button));
        this.loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.login_button:
                callLoginValidation();
                break;
            case R.id.link_signup:
                Toast.makeText(LoginActivity.this, "Feature under development!", Toast.LENGTH_LONG).show();
                break;
        }
    }


    private void callLoginValidation()
    {
        if(userId.length() > 0)
        {
            if(password.length() > 0)
            {
                loginButton.setEnabled(false);

                if(true)//if(util.isConnectionPossible())
                {

                    progress=new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
                    progress.setMessage("Authenticating");
                    progress.setIndeterminate(true);
                    progress.show();

                    String id = userId.getText().toString();
                    String pwd = password.getText().toString();
                    new HttpRequestTask().execute(id, pwd);

                }
                else
                {
                    util.showDialog(R.string.no_internet);
                }

            }
            else
            {
                password.requestFocus();
                Toast.makeText(LoginActivity.this, "Password can not be blank!", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            userId.requestFocus();
            Toast.makeText(LoginActivity.this, "User id can not be blank!", Toast.LENGTH_LONG).show();
        }



    }


    private class HttpRequestTask extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... params) {

            String result = null;
            try {

                final String url = Constant.WSDL_BASE_URL+ Constant.AUTHENTICATE_URL;

                //result ="true";
                RestTemplate rt = new RestTemplate();
                rt.getMessageConverters().add(new FormHttpMessageConverter());
                rt.getMessageConverters().add(new StringHttpMessageConverter());

                //String result = rt.postForObject(url, null, String.class, "oicportal.support", "oicportal.support");
                result = rt.postForObject(url, null, String.class,params[0], params[1]);



                return result;
            } catch (Exception e) {
                Log.e("LoginActivity", e.getMessage(), e);
            }

            return result;
        }

        @Override
        protected void onPostExecute(String response) {


            if(response.equalsIgnoreCase("true")){
                loginButton.setEnabled(true);
                progress.dismiss();
                Intent dashboardIntent = new Intent(getApplicationContext(), CustomerDashboardActivity.class);
                startActivity(dashboardIntent);
            }else{
                loginButton.setEnabled(true);
                progress.dismiss();
                Toast.makeText(LoginActivity.this, "Incorrect credentials! Please try again.", Toast.LENGTH_LONG).show();
            }



        }

    }

}
