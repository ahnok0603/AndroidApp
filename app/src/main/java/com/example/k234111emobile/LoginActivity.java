package com.example.k234111emobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText edit_name;
    EditText edit_psw;
    TextView txtMessage;
    CheckBox chkSaveLogin;
    String name_share_pref="LoginInfo";
    RadioButton radAdmin, radEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        addView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addView() {
        edit_name=findViewById(R.id.edit_name);
        edit_psw=findViewById(R.id.edit_psw);
        txtMessage=findViewById(R.id.txtMessage);
        chkSaveLogin=findViewById(R.id.chkSaveLogin);
        radAdmin=findViewById(R.id.radAdmin);
        radEmployee=findViewById(R.id.radEmployee);
    }

    public void loginsystem(View view) {
        String username=edit_name.getText().toString();
        String password=edit_psw.getText().toString();
        if(username.equalsIgnoreCase("admin") && password.equals("123")) {
            //thêm
            boolean saved = chkSaveLogin.isChecked();
            SharedPreferences preference = getSharedPreferences(name_share_pref, MODE_PRIVATE);
            SharedPreferences.Editor editor = preference.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putBoolean("saved", saved);
            editor.commit();

            txtMessage.setText(R.string.str_login_successful);
            if (radAdmin.isChecked()) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(LoginActivity.this, EmployeeManagementActivity.class);
                startActivity(intent);
            }
        } else {
            txtMessage.setText(R.string.str_login_failed);
        }
    }

    public void exitsystem(View view) {
        //finish();
        AlertDialog.Builder builder =new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(R.string.str_confirm_exit);
        builder.setMessage(R.string.str_exit_message);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton(R.string.str_yes_exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        //khi bấm cancel
        builder.setNegativeButton(R.string.str_no_exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    //Thêm
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preference=getSharedPreferences(name_share_pref,MODE_PRIVATE);
        String username=preference.getString("username","");
        String password=preference.getString("password","");
        boolean saved=preference.getBoolean("saved",false); //chưa đọc,rỗng là false
        if(saved)
        {
            edit_name.setText(username);
            edit_psw.setText(password);
        }
            chkSaveLogin.setChecked(saved);
    }
    //
}