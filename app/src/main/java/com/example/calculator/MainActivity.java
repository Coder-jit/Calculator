package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView result,solution;

    MaterialButton button_c,button_openbraket,button_closebraket,button_divide , button_7,button_8,button_9,button_multiplie,button_4,button_5,button_6,button_plus,button_1,button_2,button_3,button_minus,button_AC,button_zero,button_Dot,button_equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(button_c,R.id.button_c);
        assignId(button_openbraket,R.id.button_openbraket);
        assignId(button_closebraket,R.id.button_closebraket);
        assignId(button_divide,R.id.button_divide);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_multiplie,R.id.button_multiplie);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_plus,R.id.button_plus);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_minus,R.id.button_minus);
        assignId(button_AC,R.id.button_AC);
        assignId(button_zero,R.id.button_zero);
        assignId(button_Dot,R.id.button_Dot);
        assignId(button_equal,R.id.button_equal);

    }

    Void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
        return null;
    }
    @Override
    public void onClick(View v) {
        MaterialButton button =(MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataTocalculate = solution.getText().toString();

        if (buttonText.equals("Ac")){
            solution.setText("");
            solution.setText("0");
            return;
        }

        if (buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }

        if (buttonText.equals("c")){
            dataTocalculate = dataTocalculate.substring(0,dataTocalculate.length()-1);
        }
        else {
            dataTocalculate = dataTocalculate+buttonText;
        }

        solution.setText(dataTocalculate);

        String finalresult = getresult(dataTocalculate);
        if (!finalresult.equals("err")){

            result.setText(finalresult);
        }

    }
    String getresult(String data){

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
           String finalresult = context.evaluateString(scriptable,data, "javascript",1,null).toString();
           if (finalresult.endsWith(".0")){

               finalresult = finalresult.replace(".0","");
           }
           return finalresult;
        } catch (Exception e){
            return "err";
        }
    }
}