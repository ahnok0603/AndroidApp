package com.example.k234111emobile;

import static android.graphics.Insets.add;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class EmployeeManagementActivity extends AppCompatActivity {

    ListView lvEmployee;
    ArrayList<String> listEmployee;
    ArrayAdapter<String> adapterEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_management);
        addView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addView() {
        lvEmployee=findViewById(R.id.lvEmployee);
        listEmployee=new ArrayList<>();
        listEmployee.add("e1-A-8928392");
        listEmployee.add("e2-B-8928392");
        listEmployee.add("e3-C-8928393");
        adapterEmployee=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listEmployee
        );
        lvEmployee.setAdapter(adapterEmployee);
    }

    public void closeEmployeeActivity(View view) {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCanceledOnTouchOutside(false);
        //thử lấy images ra:
        ImageView imgSave=dialog.findViewById(R.id.imgYes);
        ImageView imgCancel=dialog.findViewById(R.id.imgCancel);
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}