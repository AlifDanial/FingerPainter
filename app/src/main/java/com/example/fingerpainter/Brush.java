package com.example.fingerpainter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class Brush extends AppCompatActivity {

    private SeekBar width;
    private int newWidth;
    private RadioGroup shapes;
    private Paint.Cap newShape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush);
        SeekBar seekBar = findViewById(R.id.seekBar);

        shapes = findViewById(R.id.brush_group);
        width = findViewById(R.id.seekBar);

        Bundle e = getIntent().getExtras();
        if(e == null){
            return;
        }

        Paint.Cap cShape = Paint.Cap.valueOf(e.getString("shapes"));
        int cwidth = e.getInt("cwidth");

        switch(cShape){
            case ROUND: shapes.check(R.id.circlebutton); break;
            case SQUARE: shapes.check(R.id.squarebutton); break;
        }

        width.setProgress(cwidth);
    }


    public void saveChanges(View view){
        newWidth = width.getProgress();
        switch(shapes.getCheckedRadioButtonId()){
            case R.id.circlebutton:
                newShape = Paint.Cap.ROUND;
                break;
            case R.id.squarebutton:
                newShape = Paint.Cap.SQUARE;
                break;
        }
        finish();
    }

    @Override
    public void finish(){
        Intent data = new Intent();
        data.putExtra("brushes", newShape.toString());
        data.putExtra("newWidth", newWidth);

        setResult(RESULT_OK, data);
        super.finish();

    }
}
