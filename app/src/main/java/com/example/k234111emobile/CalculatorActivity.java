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
    TextView txtMC, txtMR, txtM, txtMMINUS, txtMPLUS, txtMS;
    View.OnClickListener click_m_listener;
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
            public void onClick(View view) {
                processFormular();
            }
        });
        click_m_listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //process later
                if(view.equals(txtM))
                {

                }
                else if(view.equals(txtMC))
                {

                }
                //không được if view ==txtMC vì nó đang so sánh ô nhớ trên RAM
            }
        };
        txtM.setOnClickListener(click_m_listener);
        txtMC.setOnClickListener(click_m_listener);
        txtMMINUS.setOnClickListener(click_m_listener);
        txtMR.setOnClickListener(click_m_listener);
        txtMPLUS.setOnClickListener(click_m_listener);
        txtMS.setOnClickListener(click_m_listener);
    }

    private void processFormular() {
        //Step 1: get formular:
        EditText edtFormular=findViewById(R.id.editFormular);
        String formular=edtFormular.getText().toString();

        //step 2: find api (lib) to calculate formular
        String result="";
        //result=....(formular)

        //step 3: show result
        edtFormular.setText(result);
    }

    private void addView() {
        buttoncal=findViewById(R.id.buttoncal);
        txtMC=findViewById(R.id.txtMC);
        txtMR=findViewById(R.id.txtMR);
        txtMPLUS=findViewById(R.id.txtMPLUS);
        txtMMINUS=findViewById(R.id.txtMMINUS);
        txtMS=findViewById(R.id.txtMS);
        txtM=findViewById(R.id.txtM);
    }

    public void processChooseValue(View view) {
        //get current view clicked
        Button btn= (Button) view;
        //get edtFormular
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
            //remove the last character:
            new_value=old_value.substring(0,old_value.length()-1);
        }
        edtFormular.setText(new_value);
    }
}