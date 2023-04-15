package com.example.ex072;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.IntBuffer;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rnd;
    EditText etA;
    EditText etB;
    EditText etC;
    TextView solutionsTxt;
    Intent si;
    int solutionsAmount;
    double x1, x2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rnd = new Random();

        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);
        etC = findViewById(R.id.etC);

        solutionsTxt = findViewById(R.id.solutionsTxt);
    }

    public void rndNumbers(View view) {
        etA.setText("" + (rnd.nextInt(99) + 1));
        etB.setText("" + rnd.nextInt(100));
        etC.setText("" + rnd.nextInt(100));
    }

    public void solveEq(View view) {
        if((etA.getText().toString().equals("")) || (etB.getText().toString().equals("")) ||
                (etB.getText().toString().equals("")) || (etA.getText().toString().equals("-")) ||
                (etB.getText().toString().equals("-")) || (etC.getText().toString().equals("-")) ||
                (etA.getText().toString().equals("0"))){
            Toast.makeText(this, "Illegal coefficient", Toast.LENGTH_LONG).show();
        }
        else{
            si = new Intent(this, SolutionActivity.class);
            si.putExtra("a", Double.parseDouble(etA.getText().toString()));
            si.putExtra("b", Double.parseDouble(etB.getText().toString()));
            si.putExtra("c", Double.parseDouble(etC.getText().toString()));
            startActivityForResult(si, 1);
        }
    }

    @Override
    protected void onActivityResult(int source, int good, @Nullable Intent data_back) {
        super.onActivityResult(source, good, data_back);

        if (data_back != null) {
            solutionsAmount = data_back.getIntExtra("solutions", 0);
            x1 = data_back.getDoubleExtra("x1", 0);
            x2 = data_back.getDoubleExtra("x2", 0);

            switch (solutionsAmount){
                case 0:
                    solutionsTxt.setText("No real solutions were found");
                    break;
                case 1:
                    solutionsTxt.setText("x = " + x1);
                    break;
                case 2:
                    solutionsTxt.setText("x1 = " + x1 + "\n\nx2 = " + x2);
                    break;
            }
        }

    }
}