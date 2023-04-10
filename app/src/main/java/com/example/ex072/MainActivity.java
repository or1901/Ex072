package com.example.ex072;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rnd;
    EditText etA;
    EditText etB;
    EditText etC;
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rnd = new Random();

        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);
        etC = findViewById(R.id.etC);

    }

    public void rndNumbers(View view) {
        etA.setText("" + rnd.nextInt(100));
        etB.setText("" + rnd.nextInt(100));
        etC.setText("" + rnd.nextInt(100));
    }

    public void solveEq(View view) {
        if((etA.getText().toString().equals("")) || (etB.getText().toString().equals("")) ||
                (etB.getText().toString().equals(""))){
            Toast.makeText(this, "You can't leave an empty coefficient", Toast.LENGTH_LONG).show();
        }
        else{
            si = new Intent(this, SolutionActivity.class);
            si.putExtra("a", Double.parseDouble(etA.getText().toString()));
            si.putExtra("b", Double.parseDouble(etB.getText().toString()));
            si.putExtra("c", Double.parseDouble(etC.getText().toString()));
            startActivityForResult(si, 1);
        }
    }
}