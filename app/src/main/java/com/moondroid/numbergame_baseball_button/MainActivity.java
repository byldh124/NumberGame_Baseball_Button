package com.moondroid.numbergame_baseball_button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button[] numBtns = new Button[10];
    Button[] fncBtns = new Button[3];
    TextView brief;
    TextView[] inputNums = new TextView[3];
    int number_room = 0;
    int com_num1, com_num2, com_num3;

    int strike = 0;
    int ball = 0;
    int inning = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rnd = new Random();
        com_num1 = rnd.nextInt(9)+1;
        while(true){
            com_num2 = rnd.nextInt(10);
            com_num3 = rnd.nextInt(10);
            if(com_num1 != com_num2 && com_num1 != com_num3 && com_num2 != com_num3) break;
        }


        for (int i = 0 ; i < numBtns.length; i++){
        numBtns[i] = findViewById(R.id.btn_num0 + i);
        }

        fncBtns[0] = findViewById(R.id.btn_enter);
        fncBtns[1] = findViewById(R.id.btn_delete);
        fncBtns[2] = findViewById(R.id.btn_reset);

        brief = findViewById(R.id.text_Field);


        inputNums[0] = findViewById(R.id.text_input1);
        inputNums[1] = findViewById(R.id.text_input2);
        inputNums[2] = findViewById(R.id.text_input3);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ((Button)v).getText().toString();
                if (number_room == 1 && inputNums[0].getText().toString().equals(s)){
                    Toast.makeText(MainActivity.this,"같은 숫자 입력은 안됩니다.",Toast.LENGTH_SHORT).show();
                } else if(number_room ==2 && inputNums[0].getText().toString().equals(s) || number_room ==2 && inputNums[1].getText().toString().equals(s)){
                    Toast.makeText(MainActivity.this,"같은 숫자 입력은 안됩니다.",Toast.LENGTH_SHORT).show();
                } else{
                    inputNums[number_room].setText(s);
                    number_room++;
                    if (number_room == 3) number_room--;
                }
            }
        };

        for (int i = 0 ; i < numBtns.length ; i ++){
            numBtns[i].setOnClickListener(numberListener);
        }

        fncBtns[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {



                    int input_num1 = Integer.parseInt(inputNums[0].getText().toString());
                    int input_num2 = Integer.parseInt(inputNums[1].getText().toString());
                    int input_num3 = Integer.parseInt(inputNums[2].getText().toString());

                    if (input_num1 == com_num1) strike++;
                    else if(input_num1 == com_num2 || input_num1 == com_num3) ball++;
                    if (input_num2 == com_num2) strike++;
                    else if(input_num2 == com_num1 || input_num2 == com_num3) ball++;
                    if (input_num3 == com_num3) strike++;
                    else if(input_num3 == com_num1 || input_num3 == com_num2) ball++;

                    String s = brief.getText().toString();

                    if (s.contains("정답입니다.")) {
                        Toast.makeText(MainActivity.this, "리셋버튼을 눌러주세요.", Toast.LENGTH_SHORT).show();
                    }else if (inning <9){
                        if (strike != 3){
                            s +=  inning + "회 : " + input_num1 + input_num2 + input_num3 + " - " + strike +"strike " + ball +"ball\n";
                        } else {
                            s += inning + "회 : " + input_num1 + input_num2 + input_num3 + " - " + "정답입니다.\n";
                        }
                    } else if(inning == 9 && strike != 3){
                        s += inning + "회 : " + input_num1 + input_num2 + input_num3 + " - " + "실패.\n정답은 "+ com_num1 + com_num2 + com_num3 +"입니다.";
                    } else if(inning == 9 && strike == 3){
                        s += inning + "회 : " + input_num1 + input_num2 + input_num3 + " - " + "정답입니다.\n";
                    } else if(inning > 9){
                        Toast.makeText(MainActivity.this, "리셋버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
                    }


                    brief.setText(s);

                    number_room = 0;

                    for (int i = 0 ; i < inputNums.length ; i++){
                        inputNums[i].setText("");
                    }
                    strike = 0;
                    ball = 0;
                    inning++;

                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "숫자를 모두 입력해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        fncBtns[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number_room != 0 && inputNums[number_room].getText().toString().equals("")){
                    number_room--;
                    inputNums[number_room].setText("");
                } else {
                    inputNums[number_room].setText("");
                }
            }

        });

        fncBtns[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com_num1 = rnd.nextInt(9)+1;
                while(true){
                    com_num2 = rnd.nextInt(10);
                    com_num3 = rnd.nextInt(10);
                    if(com_num1 != com_num2 && com_num1 != com_num3 && com_num2 != com_num3) break;
                }
                inputNums[0].setText("");
                inputNums[1].setText("");
                inputNums[2].setText("");

                number_room = 0;
                inning = 1;

                brief.setText("");
            }
        });



    }
}