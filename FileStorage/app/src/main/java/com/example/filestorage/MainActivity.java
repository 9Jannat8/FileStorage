package com.example.filestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.saveButtonId);
        editText = findViewById(R.id.editTextId);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (text != null)
                {
                    writeToFile(text);
                }

                else {
                    Toast.makeText(getApplicationContext(), "Please enter some data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        readFromFile();
    }

    private void writeToFile(String text) {

        try {
            FileOutputStream fileOutputStream = openFileOutput("diary.txt", Context.MODE_PRIVATE);//a line likhe alt+enter caplei try - catch pawa jabe
            fileOutputStream.write(text.getBytes());//alt+enter caple catch clause pawa jabe
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "data is saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile()
    {
        try {
            FileInputStream fileInputStream = openFileInput("diary.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line; //line by line string read korar jonno
            StringBuffer stringBuffer = new StringBuffer();//sob ta text k akotro korar jonno

            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line + "\n");
            }

            editText.setText(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}