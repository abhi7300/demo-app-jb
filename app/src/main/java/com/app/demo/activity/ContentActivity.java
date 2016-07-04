package com.app.demo.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.demo.R;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener{

    TextView goToLoginText;
    TextView goToSQLText;
    TextView goToDrawerText;
    TextView goToWebviewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Typeface georgiaTypeface = Typeface.createFromAsset(getAssets(),"fonts/Georgia.ttf");

        goToLoginText = (TextView)findViewById(R.id.goToLoginTextID);
        goToLoginText.setTypeface(georgiaTypeface);
        goToLoginText.setOnClickListener(this);

        goToSQLText = (TextView)findViewById(R.id.goToSQLTextID);
        goToSQLText.setTypeface(georgiaTypeface);
        goToSQLText.setOnClickListener(this);

        goToDrawerText = (TextView)findViewById(R.id.goToDrawer);
        goToDrawerText.setTypeface(georgiaTypeface);
        goToDrawerText.setOnClickListener(this);

        goToWebviewText = (TextView)findViewById(R.id.goToWebview);
        goToWebviewText.setTypeface(georgiaTypeface);
        goToWebviewText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.goToLoginTextID:
                startLoginActivity();
                break;
            case R.id.goToSQLTextID:
                Toast.makeText(ContentActivity.this, "SQL Feature under development!", Toast.LENGTH_LONG).show();
                break;
            case R.id.goToDrawer:
                startDrawerActivity();
                break;
            case R.id.goToWebview:
                startWebViewActivity();
                break;
        }
    }

    private void startWebViewActivity() {

        Intent i = new Intent(ContentActivity.this, WebviewActivity.class);
        startActivity(i);
        // close this activity
        finish();

    }

    private void startLoginActivity() {
        Intent i = new Intent(ContentActivity.this, LoginActivity.class);
        startActivity(i);
        // close this activity
        finish();
    }

    private void startDrawerActivity() {
        Intent i = new Intent(ContentActivity.this, DrawerDashboardActivity.class);
        startActivity(i);
        // close this activity
        finish();
    }
}
