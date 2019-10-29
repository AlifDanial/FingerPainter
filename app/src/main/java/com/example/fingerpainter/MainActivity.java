package com.example.fingerpainter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FingerPainterView myCanvas;
    public static int colorRequest = 0;
    public static int brushRequest = 1;
    public static int perst_color = 0;
    public static int perst_width = 20;
    public static String string_shape;
    public static Paint.Cap perst_shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCanvas = findViewById(R.id.fingerPainterView);    //initialize myCanvas to fingerPainterView id on main activity

        if(savedInstanceState != null){

            if(perst_color != 0){
            perst_color = savedInstanceState.getInt("p_color", Color.RED);
            myCanvas.setColour(perst_color);}

            if(perst_width != 20) {
                perst_width = savedInstanceState.getInt("p_brushWidth");
                myCanvas.setBrushWidth(perst_width);}

            if(string_shape != null){
            string_shape = savedInstanceState.getString("p_brushType");
            perst_shape = Paint.Cap.valueOf(string_shape);
            myCanvas.setBrush(perst_shape);}

        }

//        if(android.content.Intent.ACTION_VIEW != null){
//            Intent intent = new Intent();
//            intent.setAction(android.content.Intent.ACTION_VIEW);
//            Uri uri = Uri.parse("file://" + getAbsolutePath());
//            intent.setDataAndType(uri,"image/*");
//            myCanvas.load(intent.getData());
//        }
    }

    public void setColor(View view){    //method to open color activity and receive a result back
        startActivityForResult(new Intent(this, Colors.class), colorRequest );     //this method starts new colors activity and receives a result in another intent object in the onActivityResult() callback
    }

    public void setBrush(View view){    //method to open color activity, pass value of current brush shape and size and receive a result back
        Intent intent = new Intent(this, Brush.class);      //initialize new intent object to brush activity
        Paint.Cap currShape = myCanvas.getBrush();                        //get current brush shape with getBrush() method from fingerPainterView object myCanvas and assign to currShape
        int currWidth = myCanvas.getBrushWidth();                         //get current brush width with getBrushWidth() method from fingerPainterView object myCanvas and assign to currWidth
        intent.putExtra("currShape", currShape.toString());         //pass current brush type into intent object with currShape request code
        intent.putExtra("currWidth", currWidth);                    //pass current brush width through intent object with currWidth request code

        startActivityForResult(intent, brushRequest);   //starts brush activity and receives current shape type and width through onActivityResult method
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {   //method to receive intent objects from different activities according to requestCode and resultCode
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == colorRequest && resultCode== RESULT_OK){         //only receive color requests which are successful and are not null
            if (data != null){
                int color = data.getIntExtra("color", Color.BLACK);    //extract color integer hex value from intent with 'color' request code and assign default black color value
                myCanvas.setColour(color);                                   //assign color used on myCanvas object to previously extracted color or default value
                perst_color = color;
            }
        }
        if (requestCode == brushRequest && resultCode== RESULT_OK){         //only receive brush requests which are successful and are not null
            if (data != null){
                String shape = data.getExtras().getString("brushType");       //extract string passed through intent with 'brushes' request code
                Paint.Cap newShape = Paint.Cap.valueOf(shape);                    //assign shape string to Paint.Cap variable newShape
                int width = data.getIntExtra("brushSize", 20);  //extract width integer value from intent with 'newWidth' request code and assign default width value to 20
                myCanvas.setBrushWidth(width);                                   //assign width used on myCanvas object to previously extracted width or default value
                myCanvas.setBrush(newShape);                                     //assign shape type used on myCanvas object to previously assigned newShape
                perst_width = width;
                string_shape = shape;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("p_color",perst_color);
        outState.putInt("p_brushWidth",perst_width);
        outState.putString("p_brushType",string_shape);

    }



}
