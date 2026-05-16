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
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btnMinus, btnDivide, btnMultiply, btnPlus, btnDot, btnPositiveNegative;
    Button btnC, btnCE, btnPercent, btnInverse, btnSquare, btnSqrt;
    String calculate;
    String math = "";
    boolean checkDot=false, checkcal=false;
    double nu1,nu2,result;
    double memory;
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
        // Nút toán tử
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processMath("-");
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processMath("+");
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processMath("/");
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processMath("*");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDot();
            }
        });
        btnPositiveNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPositiveNegative();
            }
        });
        click_m_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //process later
                if (view.equals(txtM)) {
                    setResultToScreen(String.valueOf(memory));
                } else if (view.equals(txtMC)) {
                    memory = 0;
                } else if (view.equals(txtMR)) {
                    setResultToScreen(String.valueOf(memory));
                } else if (view.equals(txtMPLUS)) {
                    memory += getCurrentNumber();
                } else if (view.equals(txtMMINUS)) {
                    memory -= getCurrentNumber();
                } else if (view.equals(txtMS)) {
                    memory = getCurrentNumber();
                 //không được if view ==txtMC vì nó đang so sánh ô nhớ trên RAM
                }
            }
        };
        txtM.setOnClickListener(click_m_listener);
        txtMC.setOnClickListener(click_m_listener);
        txtMMINUS.setOnClickListener(click_m_listener);
        txtMR.setOnClickListener(click_m_listener);
        txtMPLUS.setOnClickListener(click_m_listener);
        txtMS.setOnClickListener(click_m_listener);
    }

    private double getCurrentNumber() {
        TextView edtFormular = findViewById(R.id.editFormular);
        String s = edtFormular.getText().toString();
        if (s.isEmpty()) return 0;
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return 0;
        }
    }

    private void setResultToScreen(String s) {
        TextView edtFormular = findViewById(R.id.editFormular);
        if (s.endsWith(".0")) {
            s = s.substring(0, s.length() - 2);
        }
        edtFormular.setText(s);
    }

    private void processPositiveNegative() {
        double val = getCurrentNumber();
        if (val != 0) {
            val = -val;
            setResultToScreen(String.valueOf(val));
        }
    }

    private void processDot() {
        TextView edtFormular = findViewById(R.id.editFormular);
        if (checkcal) {
            edtFormular.setText("0.");
            checkcal = false;
        } else {
            String s = edtFormular.getText().toString();
            if (!s.contains(".")) {
                edtFormular.setText(s.isEmpty() ? "0." : s + ".");
            }
        }
    }

    private void processMath(String s) {
        if (math != null && !math.isEmpty() && !checkcal) {
            processFormular();
        }
        nu1 = getCurrentNumber();
        math = s;
        checkcal = true;
    }
    // Bước 1: nhấn toán tử → lưu số đang hiển thị + phép toán

    // Bước 2: nhấn "=" → tính kết quả
    private void processFormular() {
        //Step 1: get formular:
        TextView edtFormular=findViewById(R.id.editFormular);
        String formular=edtFormular.getText().toString();

        //step 2: find api (lib) to calculate formular
        formular = formular.replace("×", "*");
        formular = formular.replace("÷", "/");
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
        
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        
        btnPlus=findViewById(R.id.btnPlus);
        btnMinus=findViewById(R.id.btnMinus);
        btnMultiply=findViewById(R.id.btnMultiply);
        btnDivide=findViewById(R.id.btnDivide);
        btnDot=findViewById(R.id.btnDot);
        btnPositiveNegative=findViewById(R.id.btnPositiveNegative);
        
        btnC = findViewById(R.id.btnC);
        btnCE = findViewById(R.id.btnCE);
        btnPercent = findViewById(R.id.btnPercent);
        btnInverse = findViewById(R.id.btnInverse);
        btnSquare = findViewById(R.id.btnSquare);
        btnSqrt = findViewById(R.id.btnSqrt);
    }

    public void processChooseValue(View view) {
        //get current view clicked
        Button btn= (Button) view;
        //get edtFormular
        TextView edtFormular=findViewById(R.id.editFormular);
        String old_value=edtFormular.getText().toString();
        if (checkcal) {
            old_value = "";
            checkcal = false;
        }
        String new_value=btn.getText().toString();
        edtFormular.setText(old_value+new_value);
    }

    public void processBackspace(View view) {
        TextView edtFormular=findViewById(R.id.editFormular);
        String old_value=edtFormular.getText().toString();
        String new_value="0";
        if(old_value.length()>1)
        {
            new_value=old_value.substring(0,old_value.length()-1);
        }
        edtFormular.setText(new_value);
    }
}
