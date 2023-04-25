package com.example.hesapmakinesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private EditText newNumber;
    private TextView operandDisplay;
    private String pendingOperation= "+";
    private Double operand1=null;
    private Double operand2=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.textResult);
        newNumber = (EditText) findViewById(R.id.etNewNumber);
        operandDisplay = (TextView) findViewById(R.id.text_operand);

        Button button0 = (Button) findViewById(R.id.button_0);
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);
        Button button4 = (Button) findViewById(R.id.button_4);
        Button button5 = (Button) findViewById(R.id.button_5);
        Button button6 = (Button) findViewById(R.id.button_6);
        Button button7 = (Button) findViewById(R.id.button_7);
        Button button8 = (Button) findViewById(R.id.button_8);
        Button button9 = (Button) findViewById(R.id.button_9);
        Button buttonDot = (Button) findViewById(R.id.button_dot);
        Button buttonEquals = (Button) findViewById(R.id.button_equal);
        Button buttonDivide = (Button) findViewById(R.id.button_div);
        Button buttonMultiply = (Button) findViewById(R.id.button_multi);
        Button buttonMinus = (Button) findViewById(R.id.button_minus);
        Button buttonPlus = (Button) findViewById(R.id.button_plus);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                newNumber.append(button.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String op = button.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double dValue = Double.valueOf(value);
                    performOperation(dValue, op);
                    //todo: işlemi yaptır
                } catch (NumberFormatException e) {
                    Log.d("------------", "sayı dönüşüm hatası.");
                    Log.d("Calculator", "Number Format Exception");
                    newNumber.setText("");
                }
                // todo: sonuç göster
            }
        };

        buttonEquals.setOnClickListener(operationListener);
        buttonDivide.setOnClickListener(operationListener);
        buttonMultiply.setOnClickListener(operationListener);
        buttonMinus.setOnClickListener(operationListener);
        buttonPlus.setOnClickListener(operationListener);
    }

    private void performOperation(Double value,String op){
        if(null==operand1){
            operand1=value;
        }else {
            operand2=value;
            if(pendingOperation.equals("=")){
                pendingOperation=op;
            }
            switch (pendingOperation){
                case "=":
                    operand1=operand2;
                    break;
                case "/":
                    if(operand2==0){
                        operand1=0.0;
                    }else{
                        operand1=operand1/operand2; // operand1 /=operand2
                    }
                     break;
                case "+":
                    operand1 += operand2;
                    break;
                case "*":
                    operand1 *=  operand2;
                    break;
                    case "-":
                operand1 -= operand2;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
        operandDisplay.setText(op);

    }

@Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("-------","onSaveInstanceState");
        outState.putString("PENDINGOPERATION","pendingOperation");
        if(operand1 != null){
            outState.putDouble("VALUE",operand1);
            Log.d("--------",operand1.toString());
            super.onSaveInstanceState(outState);
        }
}
 @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
     Log.d("--------","Restore");
     super.onRestoreInstanceState(savedInstanceState);
     pendingOperation= savedInstanceState.getString("PENDINGOPERATION");
     operand1= savedInstanceState.getDouble("VALUE");
     result.setText(String.valueOf(operand1));
     operandDisplay.setText(pendingOperation);

 }




    }
