package com.kaito.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

import kotlin.jvm.internal.Ref;

public class MainActivity extends AppCompatActivity {

    private static Double firstNum, secondNum, result;
    private String fullString, operator;
    private boolean isFinal = false, isFirstOperator=true;
    private MaterialButton num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    private TextView inputView, resultView;
    private MaterialButton AC, del, mod, mul, div, plu, sub, equ, dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Digit ger
        num0 = findViewById(R.id.btn0);
        num1 = findViewById(R.id.btn1);
        num2 = findViewById(R.id.btn2);
        num3 = findViewById(R.id.btn3);
        num4 = findViewById(R.id.btn4);
        num5 = findViewById(R.id.btn5);
        num6 = findViewById(R.id.btn6);
        num7 = findViewById(R.id.btn7);
        num8 = findViewById(R.id.btn8);
        num9 = findViewById(R.id.btn9);
        // View get
        inputView = findViewById(R.id.calculatorView);
        resultView = findViewById(R.id.resultView);
        // function get
        AC = findViewById(R.id.btnAC);
        del = findViewById(R.id.btnDel);
        mod = findViewById(R.id.btnMod);
        mul = findViewById(R.id.btnMul);
        div = findViewById(R.id.btnDiv);
        plu = findViewById(R.id.btnPlu);
        sub = findViewById(R.id.btnSub);
        equ = findViewById(R.id.btnEqu);
        dot = findViewById(R.id.btnDot);
        // create array of nutton digit
        ArrayList<MaterialButton> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);
        // array of operator
        ArrayList<MaterialButton> operators = new ArrayList<>();
        operators.add(mod);
        operators.add(mul);
        operators.add(div);
        operators.add(plu);
        operators.add(sub);

        // get number input
        for(MaterialButton num : nums){
            num.setOnClickListener(view -> {
                onDigitCkick(num);
            });
        }

        // get operator input
        for (MaterialButton oper : operators){
            oper.setOnClickListener(view -> {
                onOperatorClick(oper);
            });
        }

        // when pressing dot
        dot.setOnClickListener(view -> {
            onDotClick();
        });

        // pressing delete
        del.setOnClickListener(view -> {
            onDelClick();
        });

        // Pressing all clear
        AC.setOnClickListener(view -> {
            onACClick();
        });

        // when pressing equal button
        equ.setOnClickListener(view -> {
            onEqualClick();
        });





    }
    // method dot pressing
    public void onDotClick(){
        if(inputView.getText().toString().length()>0)
        {
            if(!inputView.getText().toString().contains("."))
            {
                inputView.setText(inputView.getText().toString() + ".");
            }
        }
    }

    // method delete pressing
    public void onDelClick(){
        if(inputView.getText().toString().length()>1 )
        {
            if(isFinal==false)
            {
                String newNum = inputView.getText().toString().substring(0, inputView.getText().toString().length()-1);
                inputView.setText(newNum);
            }
        }
        else{
            inputView.setText(null);
        }
    }
    // All clear button
    public void onACClick(){
        firstNum = 0D;
        secondNum = 0D;
        result = 0D;
        fullString = "";
        inputView.setText(null);
        resultView.setText(null);
        isFinal=false;
        isFirstOperator = true;
    }

    // equal button
    public void onEqualClick(){
        if(!inputView.getText().toString().equals(null))
        {
            isFinal = true;
            isFirstOperator = false;
            fullString += inputView.getText().toString();
            secondNum = Double.parseDouble(inputView.getText().toString());
            switch(operator){
                case "+":
                {
                    result = firstNum + secondNum;
                    break;
                }
                case "-":
                {
                    result = firstNum - secondNum;
                    break;
                }
                case "*":
                {
                    result = firstNum * secondNum;
                    break;
                }
                case "/":
                {
                    result = firstNum / secondNum;
                    break;
                }
                case "%":
                {
                    result = firstNum % secondNum;
                    break;
                }
            }
            // get decimal format
            DecimalFormat dec = new DecimalFormat("#.######");
            String newFormat = dec.format(result);
            inputView.setText(fullString);
            resultView.setText(newFormat);

        }
    }
    // Ditgit button
    public void onDigitCkick(MaterialButton num){
        if(inputView.getText().toString().equals(null)) {
            inputView.setText((num.getText().toString()));
        } else if (isFinal==true && isFirstOperator==false) {
            firstNum = 0D;
            secondNum = 0D;
            result = 0d;
            fullString = "";
            inputView.setText(num.getText().toString());
            isFinal=false;
            resultView.setText(null);
            //set first operator = true
            isFirstOperator=true;

        }  else {
            String nextNum = inputView.getText().toString() + num.getText().toString();
            inputView.setText(nextNum);
        }
    }

    // operator button click
    public void onOperatorClick(MaterialButton o){
        if(inputView.getText().toString().equals(null)==false && isFirstOperator==true)
        {
            operator = o.getText().toString();
            fullString = inputView.getText().toString() + o.getText().toString();
            resultView.setText(fullString);
            firstNum = Double.parseDouble(inputView.getText().toString());
            inputView.setText(null);
        }
        else if (isFirstOperator==false )
        {
            operator = o.getText().toString();
            firstNum = result;
            DecimalFormat dec = new DecimalFormat("#.######");
            String newFormat = dec.format(result);
            fullString = newFormat + o.getText().toString();
            result = 0d;
            secondNum = 0d;
            inputView.setText(null);
            resultView.setText(fullString);
            isFinal=false;
        }
    }

}

