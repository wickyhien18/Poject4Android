package com.example.thuchanh4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import com.example.thuchanh4.Examinee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormActivity extends AppCompatActivity{

    private EditText id, name,math, physic, chemical;
    private Toolbar toolbar;
    private FloatingActionButton add;

    private void Init() {
        id = findViewById(R.id.editTextId);
        name = findViewById(R.id.editTextName);
        math = findViewById(R.id.editTextMath);
        physic = findViewById(R.id.editTextPhysic);
        chemical = findViewById(R.id.editTextChemical);
        toolbar = findViewById(R.id.toolbarForm);
        add = findViewById(R.id.buttonAddExaminee);
    }

    private void Listen() {
        add.setOnClickListener(v -> {
            String Id = id.getText().toString().trim();
            String Name = name.getText().toString().trim();
            String Mathh = math.getText().toString().trim();
            String Physicc = physic.getText().toString().trim();
            String Chemicall = chemical.getText().toString().trim();

            if (Id.isEmpty() || Name.isEmpty() || Mathh.isEmpty() || Physicc.isEmpty() || Chemicall.isEmpty()) {
                Toast.makeText(this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                return;
            }

            float Math = Float.parseFloat(Mathh);
            float Physic = Float.parseFloat(Physicc);
            float Chemical = Float.parseFloat(Chemicall);
            if (Math < 0 || Math > 10 || Physic < 0 || Physic > 10 || Chemical > 10 || Chemical < 0) {
                Toast.makeText(this,"Điểm Toán, Lý, Hoá chỉ nằm trong khoảng 0 - 10",Toast.LENGTH_SHORT).show();
                return;
            }

            Examinee ex = new Examinee(Id,Name,Math,Physic,Chemical);
            Intent sendEx = new Intent();
            sendEx.putExtra("ADD_EXAMINEE",ex);
            setResult(RESULT_OK, sendEx);
            finish();
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Init();
        Listen();

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}