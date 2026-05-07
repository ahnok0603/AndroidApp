package com.example.k234111emobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {

    Button buttoncal;
    TextView txtMC, txtMR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        addView();
        addEvent();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addEvent() {
        buttoncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void addView() {
    }

    public void processChooseValue(View view) {
        //get current view clicked
        Button btn= (Button) view;
        EditText edtFormular=findViewById(R.id.editFormular);
        String old_value=edtFormular.getText().toString();
        String new_value=btn.getText().toString();
        edtFormular.setText(old_value+new_value);
    }

    public void processBackspace(View view) {
        EditText edtFormular=findViewById(R.id.editFormular);
        String old_value=edtFormular.getText().toString();
        String new_value="";
        if(old_value.length()>1)
        {
            new_value=old_value.substring(0,old_value.length()-1);
        }

    }
}