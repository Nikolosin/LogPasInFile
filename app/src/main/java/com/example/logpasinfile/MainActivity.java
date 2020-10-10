package com.example.logpasinfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editLogin;
    private EditText editPassword;
    private Button btnLogin;
    private Button btnReg;
    private final String nameFileLog = "Login";
    private final String nameFilePas = "Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editLogin.getText().length() == 0 | editPassword.getText().length() == 0) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    SaveData(nameFileLog, editLogin);
                    SaveData(nameFilePas, editPassword);
                    Toast toast = Toast.makeText(MainActivity.this, R.string.ok, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataLog = LoadData(nameFileLog);
                String dataPas = LoadData(nameFilePas);
                if (editLogin.getText().length() == 0 | editPassword.getText().length() == 0) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (editLogin.getText().toString().equals(dataLog) & editPassword.getText().toString().equals(dataPas)) {
                        Toast toast = Toast.makeText(MainActivity.this, R.string.checkOk, Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, R.string.checkNo, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }

    private void InitView() {
        editLogin = findViewById(R.id.editLog);
        editPassword = findViewById(R.id.editPas);
        btnLogin = findViewById(R.id.btnLog);
        btnReg = findViewById(R.id.btnReg);
    }

    private void SaveData(String nameFile, EditText editText) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(nameFile, MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bw = new BufferedWriter(outputStreamWriter);
        try {
            bw.write(editText.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String LoadData(String nameFile) {
        String data = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(nameFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        try {
            data = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}