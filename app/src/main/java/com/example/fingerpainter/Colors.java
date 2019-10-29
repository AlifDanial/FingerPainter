package com.example.fingerpainter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class Colors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
    }

    public void setColor(View view){
        switch(view.getId()){
            case R.id.buttonGreen:
                setResult(RESULT_OK, new Intent().putExtra("color", Color.GREEN));
                break;
            case R.id.buttonRed:
                setResult(RESULT_OK, new Intent().putExtra("color", Color.RED));
                break;
            case R.id.buttonBlue:
                setResult(RESULT_OK, new Intent().putExtra("color", Color.BLUE));
                break;
            case R.id.buttonYellow:
                setResult(RESULT_OK, new Intent().putExtra("color", Color.YELLOW));
                break;
            case R.id.buttonPurple:
                setResult(RESULT_OK, new Intent().putExtra("color", Color.parseColor("#7B1FA2")));
                break;
            case R.id.buttonBlack:
                setResult(RESULT_OK, new Intent().putExtra("color", Color.BLACK));
                break;


        }
        finish();
    }
}
