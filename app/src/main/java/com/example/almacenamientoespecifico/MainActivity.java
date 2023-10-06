package com.example.almacenamientoespecifico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText txtFilename;
    private EditText txtFilecontent;
    private Button btnSave;
    private ListView lvFiles;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private String filename;
    private String filecontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFilename.findViewById(R.id.txt_filename);
        txtFilecontent.findViewById(R.id.filecontent);
        btnSave.findViewById(R.id.btn_save);
        lvFiles.findViewById(R.id.lv_files);
        updateList();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String[] dataOrigin={"1","2","3","4","5","6","7","8","9","10"};
                // ArrayAdapter adapter= new ArrayAdapter<String>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dataOrigin);
                // lvFiles.setAdapter(adapter);
                filename = txtFilename.getText().toString();
                filecontent = txtFilecontent.getText().toString();
                Toast.makeText(MainActivity.this, "Receta guardada \n Felicidades", Toast.LENGTH_SHORT).show();
                updateList();
                try {
                    fileOutputStream = openFileOutput(filename, MODE_PRIVATE);
                    fileOutputStream.write(filecontent.getBytes());
                } catch (FileNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void updateList() {
        // String[] dataOrigin={"1","2","3","4","5","6","7","8","9","10"};
        String[] dataOrigin=fileList(); //Este comando ayuda a jalar las listas
        ArrayAdapter adapter= new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dataOrigin);
        lvFiles.setAdapter(adapter);
    }
}