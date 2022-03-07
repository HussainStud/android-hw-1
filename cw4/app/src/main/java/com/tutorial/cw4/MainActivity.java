package com.tutorial.cw4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtTitle = findViewById(R.id.txtTitle);
        LinearLayout linearLayout = findViewById(R.id.mainLayout);

        EditText edtNumber1 = findViewById(R.id.edtNumber1);
        EditText edtNumber2 = findViewById( R.id.edtNumber2);
        EditText edtNumber3 = findViewById( R.id.edtNumber3);
        EditText edtNumber4 = findViewById( R.id.edtNumber4);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        Button btnReset = findViewById(R.id.btnReset);
        TextView txtResult = findViewById(R.id.txtResult);

        EditText[] editTextArray = new EditText[4];
        editTextArray[0] = edtNumber1;
        editTextArray[1] = edtNumber2;
        editTextArray[2] = edtNumber3;
        editTextArray[3] = edtNumber4;


        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();

                startResultAnimation(txtResult);

                int quizNum = Integer.parseInt(edtNumber1.getText().toString());
                int homeworkNum = Integer.parseInt(edtNumber2.getText().toString());
                int midTermNum = Integer.parseInt(edtNumber3.getText().toString());
                int finalNum = Integer.parseInt(edtNumber4.getText().toString());

                double resultText = getGrade(quizNum, 15, homeworkNum, 25, midTermNum, 30, finalNum, 30);
                txtResult.setText(Double.toString(resultText));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();

                int i;
                for (i = 0; i < editTextArray.length; i++) {
                    editTextArray[i].getText().clear();
                }
            }
        });



    }

    private double getGrade(int firstNum, double firstPercent, int secondNum, double secondPercent, int thirdNum, double thirdPercent, int fourthNum, double fourthPercent)
    {
        double resultNumber;
        resultNumber = (firstNum * (firstPercent / 100)) + (secondNum * (secondPercent / 100)) + (thirdNum * (thirdPercent / 100)) + (fourthNum * (fourthPercent / 100));
       return resultNumber;
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    private void startResultAnimation(View txtResult) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_result_txt);
        txtResult.startAnimation(animation);
    }

}