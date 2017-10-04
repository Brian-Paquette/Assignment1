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

public class EditProfileActivity extends AppCompatActivity implements OnClickListener {

    private Button checkButton;
    private EditText value1EditText;
    private EditText value2EditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        value2EditText = (EditText) findViewById(R.id.value2EditText);
        value1EditText = (EditText) findViewById(R.id.value1EditText);
        checkButton = (Button) findViewById(R.id.checkButton);

        checkButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        String text = "Your new value is: ";
        Toast toast;
        switch (v.getId()) {
            case R.id.checkButton:
                int newValue = (Integer.parseInt(value1EditText.getText().toString()))* (Integer.parseInt(value2EditText.getText().toString()));
                text = text + Integer.toString(newValue);

                toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                toast.show();
                break;
        }
    }
}
