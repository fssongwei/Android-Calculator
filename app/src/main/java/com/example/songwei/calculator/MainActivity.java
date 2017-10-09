package com.example.songwei.calculator;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,add,minus,multiply,division,equal,dot,percentage,delete,clear;
    EditText Result;
    String TextDisplay = "";

    //Some Status
    private boolean Computed = false;
    //if the first parameter starts with "-" and the operator is not "-", then the status1 set true
    private boolean status1 = false;
    //if the first parameter starts with "-" and the operator is also "-", then the status2 set true
    private boolean status2 = false;
    //if the first parameter starts without "-", then the status3 set true
    private boolean status3 = false;


    private void setComponents() {

        btn_0 = (Button) findViewById(R.id.num_zero);
        btn_1 = (Button) findViewById(R.id.num_one);
        btn_2 = (Button) findViewById(R.id.num_two);
        btn_3 = (Button) findViewById(R.id.num_three);
        btn_4 = (Button) findViewById(R.id.num_four);
        btn_5 = (Button) findViewById(R.id.num_five);
        btn_6 = (Button) findViewById(R.id.num_six);
        btn_7 = (Button) findViewById(R.id.num_seven);
        btn_8 = (Button) findViewById(R.id.num_eight);
        btn_9 = (Button) findViewById(R.id.num_nine);

        add = (Button) findViewById(R.id.add);
        minus = (Button) findViewById(R.id.minus);
        multiply = (Button) findViewById(R.id.multiply);
        division = (Button) findViewById(R.id.division);
        equal = (Button) findViewById(R.id.equal);

        dot = (Button) findViewById(R.id.dot);
        percentage = (Button) findViewById(R.id.percentage);
        delete = (Button) findViewById(R.id.delete);
        clear = (Button) findViewById(R.id.clear);

        Result = (EditText) findViewById(R.id.result_text);
        TextDisplay = Result.getText().toString();

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        add.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        division.setOnClickListener(this);
        equal.setOnClickListener(this);
        dot.setOnClickListener(this);
        percentage.setOnClickListener(this);
        delete.setOnClickListener(this);
        clear.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        MediaPlayer sound = MediaPlayer.create(this, R.raw.buttonsound);
        sound.start();

        switch (v.getId()){

            case R.id.num_zero:
                TextDisplay = LimitDigits(TextDisplay,"0");
                break;
            case R.id.num_one:
                TextDisplay = LimitDigits(TextDisplay,"1");
                break;
            case R.id.num_two:
                TextDisplay = LimitDigits(TextDisplay,"2");
                break;
            case R.id.num_three:
                TextDisplay = LimitDigits(TextDisplay,"3");
                break;
            case R.id.num_four:
                TextDisplay = LimitDigits(TextDisplay,"4");
                break;
            case R.id.num_five:
                TextDisplay = LimitDigits(TextDisplay,"5");
                break;
            case R.id.num_six:
                TextDisplay = LimitDigits(TextDisplay,"6");
                break;
            case R.id.num_seven:
                TextDisplay = LimitDigits(TextDisplay,"7");
                break;
            case R.id.num_eight:
                TextDisplay = LimitDigits(TextDisplay,"8");
                break;
            case R.id.num_nine:
                TextDisplay = LimitDigits(TextDisplay,"9");
                break;


            case R.id.add:
                setDisplay("+");
                break;
            case R.id.minus:
                setDisplay("-");
                break;
            case R.id.multiply:
                setDisplay("×");
                break;
            case R.id.division:
                setDisplay("÷");
                break;
            case R.id.equal:
                TextDisplay = getResult();
                Computed = true;
                break;

            case R.id.dot:
                if (!Computed){

                    if (TextDisplay.contains("+") || TextDisplay.contains("-") ||
                            TextDisplay.contains("×") || TextDisplay.contains("÷") ){

                        String parameter1 = null;
                        String parameter2 = null;

                        if (TextDisplay.contains("+")) {
                            parameter1 = TextDisplay.substring(0, TextDisplay.indexOf("+"));
                            parameter2 = TextDisplay.substring(TextDisplay.indexOf("+") + 1);
                        } else if (TextDisplay.contains("-")) {
                            parameter1 = TextDisplay.substring(0, TextDisplay.indexOf("-"));
                            parameter2 = TextDisplay.substring(TextDisplay.indexOf("-") + 1);
                        } else if (TextDisplay.contains("×")) {
                            parameter1 = TextDisplay.substring(0, TextDisplay.indexOf("×"));
                            parameter2 = TextDisplay.substring(TextDisplay.indexOf("×") + 1);
                        } else if (TextDisplay.contains("÷")) {
                            parameter1 = TextDisplay.substring(0, TextDisplay.indexOf("÷"));
                            parameter2 = TextDisplay.substring(TextDisplay.indexOf("÷") + 1);
                        }

                        boolean isContainedDot = parameter2.contains(".");
                        if (parameter2.length() >= 9){

                        } else if (!isContainedDot){
                            if (parameter2.equals("")){
                                TextDisplay += "0.";
                            } else {
                                TextDisplay += ".";
                            }
                        } else {
                            return;
                        }
                    } else {
                        boolean isContainedDot = TextDisplay.contains(".");
                        if (TextDisplay.length() >= 9){

                        } else if (!isContainedDot){
                            TextDisplay += ".";
                        } else {
                            return;
                        }
                    }
                    Computed = false;

                } else {
                    TextDisplay = "0.";
                    Computed = false;
                }


                break;
            case R.id.percentage:
                if (TextDisplay.equals("error")){
                    MediaPlayer error = MediaPlayer.create(this, R.raw.error);
                    error.start();
                } else {

                    getStatus();

                    if (status1 || status2 || status3) {

                    } else {
                        if (TextDisplay.equals("0")) {
                            return;
                        } else {
                            double temp = Double.parseDouble(TextDisplay);
                            temp = temp / 100;
                            TextDisplay = String.valueOf(temp);
                        }
                    }
                }
                break;
            case R.id.delete:
                if (TextDisplay.equals("error")){
                    TextDisplay = "0";
                } else if (TextDisplay.length() > 0){
                    if (TextDisplay.length() == 1) {
                        TextDisplay = "0";
                    } else {
                        TextDisplay = TextDisplay.substring(0,TextDisplay.length()-1);
                    }
                }
                break;
            case R.id.clear:
                TextDisplay = "0";
                break;
        }

        Result.setText(TextDisplay);
    }




    private String LimitDigits(String TextDisplay, String s) {

        if (!Computed){

            if (TextDisplay.contains("+") || TextDisplay.contains("-") ||
                    TextDisplay.contains("×") || TextDisplay.contains("÷")){

                String parameter2 = null;

                if (TextDisplay.contains("+")){
                    parameter2 = TextDisplay.substring(TextDisplay.indexOf("+")+1);
                }
                if (TextDisplay.contains("-")){
                    parameter2 = TextDisplay.substring(TextDisplay.indexOf("-")+1);
                }
                if (TextDisplay.contains("×")){
                    parameter2 = TextDisplay.substring(TextDisplay.indexOf("×")+1);
                }
                if (TextDisplay.contains("÷")){
                    parameter2 = TextDisplay.substring(TextDisplay.indexOf("÷")+1);
                }

                if (TextDisplay.substring(TextDisplay.length()-1).equals("+") ||
                        TextDisplay.substring(TextDisplay.length()-1).equals("-") ||
                        TextDisplay.substring(TextDisplay.length()-1).equals("×") ||
                        TextDisplay.substring(TextDisplay.length()-1).equals("÷")){
                    TextDisplay += s;
                } else {
                    if (parameter2.contains(".")){
                        if (parameter2.length() >= 10){
                        } else {
                            TextDisplay += s;
                        }
                    } else {
                        if (parameter2.length() >= 9){

                        } else {
                            TextDisplay += s;
                        }
                    }
                }
            } else {

                if (TextDisplay.contains(".")){
                    if (TextDisplay.length() >= 10){

                    } else {
                        TextDisplay += s;
                    }
                } else {
                    if (TextDisplay.length() >= 9){

                    } else {
                        TextDisplay += s;
                    }
                }
            }

            if (TextDisplay.contains("e")){
                TextDisplay = "0";
            }

            if (TextDisplay.equals("0")){
                TextDisplay = "";
            }

            Computed = false;

        } else {

            TextDisplay = s;
            Computed = false;

        }


        return TextDisplay;
    }

    public static String formatDotString(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }

    private boolean checkCalculation() {

        getStatus();

        String SecParameter = null;

        if ( status1 || status3 || status2) {

            if (TextDisplay.contains("+")) {

                SecParameter = TextDisplay.substring(TextDisplay.indexOf("+") + 1);

                if (SecParameter.equals("")) {
                    return false;
                } else {
                    return true;
                }
            } else if (TextDisplay.contains("×")) {

                SecParameter = TextDisplay.substring(TextDisplay.indexOf("×") + 1);

                if (SecParameter.equals("")) {
                    return false;
                } else {
                    return true;
                }

            } else if (TextDisplay.contains("÷")) {

                SecParameter = TextDisplay.substring(TextDisplay.indexOf("÷") + 1);

                if (SecParameter.equals("")) {
                    return false;
                } else {
                    return true;
                }

            } else if (TextDisplay.contains("-")) {

                SecParameter = TextDisplay.substring(TextDisplay.lastIndexOf("-") + 1);

                if (SecParameter.equals("")) {
                    return false;
                } else {
                    return true;
                }

            }
        }
        return false;
    }

    private void getStatus() {

        status1 = TextDisplay.startsWith("-") && ( TextDisplay.contains("+") ||
                TextDisplay.contains("×") || TextDisplay.contains("÷") );

        status2 = TextDisplay.startsWith("-") && ( TextDisplay.lastIndexOf("-") != 0 );

        status3 = !TextDisplay.startsWith("-") && ( TextDisplay.contains("+") ||
                TextDisplay.contains("-") || TextDisplay.contains("×") || TextDisplay.contains("÷"));
    }

    private String getResult() {

        String tempDisplay = null;

        String parameter1 = null;
        String parameter2 = null;

        double arg1 = 0;
        double arg2 = 0;
        double result = 0;

        getStatus();


        if ( status1 || status3 || status2) {

            if (TextDisplay.contains("+")) {

                parameter1 = TextDisplay.substring(0, TextDisplay.indexOf("+"));
                parameter2 = TextDisplay.substring(TextDisplay.indexOf("+") + 1);

                if (parameter2.equals("")){
                    tempDisplay = TextDisplay;
                } else {

                    arg1 = Double.parseDouble(parameter1);
                    arg2 = Double.parseDouble(parameter2);
                    result = arg1 + arg2;
                    tempDisplay = String.format("%f", result);
                    tempDisplay = formatDotString(tempDisplay);
                }


            } else if (TextDisplay.contains("×")) {

                parameter1 = TextDisplay.substring(0, TextDisplay.indexOf("×"));
                parameter2 = TextDisplay.substring(TextDisplay.indexOf("×") + 1);

                if (parameter2.equals("")){
                    tempDisplay = TextDisplay;
                } else {
                    arg1 = Double.parseDouble(parameter1);
                    arg2 = Double.parseDouble(parameter2);
                    result = arg1 * arg2;
                    tempDisplay = String.format("%f", result);
                    tempDisplay = formatDotString(tempDisplay);
                }

            } else if (TextDisplay.contains("÷")) {

                parameter1 = TextDisplay.substring(0, TextDisplay.indexOf("÷"));
                parameter2 = TextDisplay.substring(TextDisplay.indexOf("÷") + 1);

                if (parameter2.equals("0")){
                    tempDisplay = "error";
                    MediaPlayer error = MediaPlayer.create(this, R.raw.error);
                    error.start();
                } else if (parameter2.equals("")){
                    tempDisplay = TextDisplay;
                } else {
                    arg1 = Double.parseDouble(parameter1);
                    arg2 = Double.parseDouble(parameter2);
                    result = arg1 / arg2;
                    tempDisplay = String.format("%f", result);
                    tempDisplay = formatDotString(tempDisplay);
                }

            } else if (TextDisplay.contains("-")) {

                parameter1 = TextDisplay.substring(0, TextDisplay.lastIndexOf("-"));
                parameter2 = TextDisplay.substring(TextDisplay.lastIndexOf("-") + 1);

                if (parameter2.equals("")){
                    tempDisplay = TextDisplay;
                } else {
                    arg1 = Double.parseDouble(parameter1);
                    arg2 = Double.parseDouble(parameter2);
                    result = arg1 - arg2;
                    tempDisplay = String.format("%f", result);
                    tempDisplay = formatDotString(tempDisplay);
                }

            }

            if (tempDisplay.length() >= 10) {
                tempDisplay = String.format("%e", Double.parseDouble(tempDisplay));
            } else if (tempDisplay.contains(".")) {
                if (tempDisplay.substring(0, tempDisplay.indexOf(".")).length() >= 10) {
                    tempDisplay = String.format("%e", Double.parseDouble(tempDisplay));
                }
            }
        } else {
            tempDisplay = TextDisplay;
        }

        return tempDisplay;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setComponents();
    }

    public void setDisplay(String x){
        if (!TextDisplay.contains("e")) {
            if (checkCalculation()) {
                TextDisplay = getResult();
                if (TextDisplay.equals("error")){

                } else {
                    TextDisplay += x;
                }

            } else {

                if (Computed) {
                    Computed = false;
                }

                if (!(TextDisplay.substring(TextDisplay.length() - 1)).equals(x)) {
                    TextDisplay += x;
                } else if ((TextDisplay.substring(TextDisplay.length() - 1)).equals("+")) {
                    TextDisplay = TextDisplay.replace("+", x);
                } else if ((TextDisplay.substring(TextDisplay.length() - 1)).equals("-")) {
                    TextDisplay = TextDisplay.replace("-", x);
                } else if ((TextDisplay.substring(TextDisplay.length() - 1)).equals("÷")) {
                    TextDisplay = TextDisplay.replace("÷", x);
                } else if (!(TextDisplay.substring(TextDisplay.length() - 1)).equals("×")) {
                    TextDisplay = TextDisplay.replace("×", x);
                }
            }
        } else {
            TextDisplay = "0";
        }
    }
}