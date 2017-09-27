package bpaquette6801.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Context;
import java.text.NumberFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;

public class ForgotPassActivity extends AppCompatActivity implements OnClickListener {
    private Button confirmButton;
    private EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        confirmButton = (Button) findViewById(R.id.confirmButton);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmButton:
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                String text = "null";
                Toast toast;
                String passwordString = passwordEditText.getText().toString();
                if(passwordEditText.getText().toString().equals("") || passwordEditText.getText().equals(null)){
                    text = "Login Failed - Enter something for goodness sake";
                    toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                    toast.show();
                }
                else{
                    text = "SUCCESS";
                    toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                    toast.show();

                    Bundle userPass = getIntent().getExtras();
                    Intent intent = new Intent(this, HomeActivity.class);
                    userPass.putString("pass",passwordString);
                    intent.putExtras(userPass);
                    startActivity(intent);
                }
                break;
        }
    }
}
