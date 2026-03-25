package com.example.thuchanh4;

import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.thuchanh4.Examinee;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private Button add, show;
    private ArrayAdapter<Examinee> examineeAdapter;
    private ArrayList<Examinee> fullListExaminee = new ArrayList<>();
    private DbHelper dbHelper;
    private ActivityResultLauncher<Intent> examineePicker = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result-> {
                if (result.getResultCode() == MainActivity.RESULT_OK && result.getData() != null) {
                    Examinee examineeGet = (Examinee) result.getData().getSerializableExtra("ADD_EXAMINEE");
                    if (examineeGet != null) {
                        dbHelper.insert(examineeGet);
                        fullListExaminee.add(examineeGet);
                    }
                    examineeAdapter.notifyDataSetChanged();
                }
            }
    );

    private void Init() {
        list = findViewById(R.id.listViewExaminee);
        add = findViewById(R.id.buttonAdd);
        show = findViewById(R.id.buttonShow);
    }

    private void Listen() {
        //Add
        add.setOnClickListener(v -> {
            Intent addExaminee = new Intent(MainActivity.this, FormActivity.class);
            examineePicker.launch(addExaminee);
        });

        //List
        examineeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fullListExaminee);
        list.setAdapter(examineeAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        Listen();
    }
}