package com.example.fingerpainter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class Brush extends AppCompatActivity {

    private SeekBar currwidth;      //declare seekbar width value variable
    private int newWidth;           //declare newWidth variable
    private RadioGroup currshape;   //declare shape radioGroup variable
    private Paint.Cap newShape;     //declare newShape Paint.Cap variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //on starting of new brush activity, the type of shape and width size are received from main activity to retain it's current shape and width
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush);

        currshape = findViewById(R.id.brush_group);             //shape is assigned to a Radio Group variable with id brush_group
        currwidth = findViewById(R.id.seekBar);                 //width is assigned to a seekBar variable with id seekBar

        Bundle e = getIntent().getExtras();                     //e bundle object is used to extract any intent value objects passed to brush activity
        if(e == null){                                          //if any null values are found, exit the method
            return;
        }

        Paint.Cap currShape = Paint.Cap.valueOf(e.getString("currShape")); //any e with request code of currShape is assigned to currShape Paint.Cap variable
        int currWidth = e.getInt("currWidth");                              //any  e with request code of currWidth is assigned to currWidth int variable

        switch(currShape){                                                  //switch to check the radio button with 'shape' id of currShape value which are either ROUND or SQUARE
            case ROUND: currshape.check(R.id.circlebutton); break;
            case SQUARE: currshape.check(R.id.squarebutton); break;
        }

        currwidth.setProgress(currWidth);                                   //method to set the current position on the seekbar with 'width' id with the value of currWidth
    }


    public void saveChanges(View view){                                 //method to save new changes made on the brush activity
        switch(currshape.getCheckedRadioButtonId()) {                   //switch to update the shape type checked and assign to newShape variable
            case R.id.circlebutton:
                newShape = Paint.Cap.ROUND;
                break;
            case R.id.squarebutton:
                newShape = Paint.Cap.SQUARE;
                break;
        }
        newWidth = currwidth.getProgress();                          //update the current progress of seekbar and assign to newWidth variable
        finish();                                                    //call finish method on class
    }

    @Override
    public void finish(){                                           //overrided finish method to pass new shape and width values to main activity
        Intent data = new Intent();                                 //new intent 'data' to pass to onActivityResult method in main activity
        data.putExtra("brushType", newShape.toString());     //pass newShape in string format to main activity with brushType request code
        data.putExtra("brushSize", newWidth);                //pass newWidth to main activity with brushSize request code

        setResult(RESULT_OK, data);                                 // set result code to RESULT_OK for intent 'data'
        super.finish();                                             // call finish() method on parent class

    }
}
