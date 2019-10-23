package com.example.fingerpainter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FingerPainterView myCanvas;
    public static int requestcolor = 0;
    public static int requestbrush = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCanvas = findViewById(R.id.fingerPainterView);
    }

    public void setColor(View view){
        startActivityForResult(new Intent(this, Colors.class), requestcolor );
    }

    public void setBrush(View view){
        Intent intent = new Intent(this, Brush.class);
        Paint.Cap cShape = myCanvas.getBrush();
        int cwidth = myCanvas.getBrushWidth();
        intent.putExtra("shapes", cShape.toString());
        intent.putExtra("cwidth", cwidth);

        startActivityForResult(intent, requestbrush);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestcolor && resultCode== RESULT_OK){
            if (data != null){
                int color = data.getIntExtra("colors", Color.BLACK);
                myCanvas.setColour(color);
            }
        }
        if (requestCode == requestbrush && resultCode== RESULT_OK){
            if (data != null){
                String shape = data.getExtras().getString("brushes");
                Paint.Cap newShape = Paint.Cap.valueOf(shape);
                int width = data.getIntExtra("newWidth", 20);
                myCanvas.setBrushWidth(width);
                myCanvas.setBrush(newShape);

            }
        }
    }
}
