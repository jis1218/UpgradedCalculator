package com.example.newcalculator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//implements를 통해 lister를 달아주는 방법도 있다.

public class MainActivity extends AppCompatActivity {

    TextView topDisplay, inputDisplay;
    int index;
    Button targetButton;
    Button btn0, btn1;
    FrameLayout frameLayout;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        linearLayout = (LinearLayout) findViewById(R.id.stage2);
        targetButton = (Button) findViewById(R.id.targetButton);

        topDisplay = (TextView) findViewById(R.id.textView);
        inputDisplay = (TextView) findViewById(R.id.inputDisplay);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        btn0.setOnClickListener(onClickListener);
        btn1.setOnClickListener(onClickListener);
        findViewById(R.id.btn2).setOnClickListener(onClickListener);
        findViewById(R.id.btn3).setOnClickListener(onClickListener);
        findViewById(R.id.btn4).setOnClickListener(onClickListener);
        findViewById(R.id.btn5).setOnClickListener(onClickListener);
        findViewById(R.id.btn6).setOnClickListener(onClickListener);
        findViewById(R.id.btn7).setOnClickListener(onClickListener);
        findViewById(R.id.btn8).setOnClickListener(onClickListener);
        findViewById(R.id.btn9).setOnClickListener(onClickListener);
        findViewById(R.id.btnPlus).setOnClickListener(onClickListener);
        findViewById(R.id.btnMinus).setOnClickListener(onClickListener);
        findViewById(R.id.btnTime).setOnClickListener(onClickListener);
        findViewById(R.id.btnDivide).setOnClickListener(onClickListener);
        findViewById(R.id.btnC).setOnClickListener(onClickListener);
        findViewById(R.id.btnEqual).setOnClickListener(onClickListener);
        findViewById(R.id.btnBr1).setOnClickListener(onClickListener);
        findViewById(R.id.btnBr2).setOnClickListener(onClickListener);
        findViewById(R.id.btnDot).setOnClickListener(onClickListener);
        findViewById(R.id.btnDlt).setOnClickListener(onClickListener);
    }

    public String getResult(String result){
        ArrayList<String> list = new ArrayList<>();
        int i =1;
        String strArray[] = result.split("(?<=[*/()+-])|(?=[*/+()-])");
        for(String put : strArray){
            list.add(put);
        }
        while(i<list.size()-1){
            if(list.get(i).equals("*")){
                list.set(i-1, String.valueOf(Double.parseDouble(list.get(i-1)) * Double.parseDouble(list.get(i+1))));
                list.remove(i);
                list.remove(i);
                i=0;
            }

            if(list.get(i).equals("/")){
                list.set(i-1, String.valueOf(Double.parseDouble(list.get(i-1)) / Double.parseDouble(list.get(i+1))));
                list.remove(i);
                list.remove(i);
                i=0;
            }

            i++;
        }
        i=0;
        while(i<list.size()-1){
            if(list.get(i).equals("+")){
                list.set(i-1, String.valueOf(Double.parseDouble(list.get(i-1)) + Double.parseDouble(list.get(i+1))));
                list.remove(i);
                list.remove(i);
                i=0;
            }
            if(list.get(i).equals("-")){
                list.set(i-1, String.valueOf(Double.parseDouble(list.get(i-1)) - Double.parseDouble(list.get(i+1))));
                list.remove(i);
                list.remove(i);
                i=0;
            }
            i++;
        }
        return list.get(0).toString();
    }

    public void addText(String text){
        if(inputDisplay.getText().toString().equals("")){
            inputDisplay.setText(text);
            topDisplay.append(text);
        }else{
            inputDisplay.append(text);
            topDisplay.append(text);
        }
        index = 0;
    }

    public String deleteText(String text){
        return text.substring(0, text.length()-1);
    }

    public void buhoPut(String buho){
        if(index>=1 && index<=4){
            topDisplay.setText(deleteText(topDisplay.getText().toString()));
            topDisplay.append(buho);
        }else{
            topDisplay.append(buho);
        }
        inputDisplay.setText("");
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()) {
                case R.id.btn1:
                    //addText("1");
                    fly(btn1, "1");
                    break;
                case R.id.btn2:
                    addText("2");
                    break;
                case R.id.btn3:
                    addText("3");
                    break;
                case R.id.btn4:
                    addText("4");
                    break;
                case R.id.btn5:
                    addText("5");
                    break;
                case R.id.btn6:
                    addText("6");
                    break;
                case R.id.btn7:
                    addText("7");
                    break;
                case R.id.btn8:
                    addText("8");
                    break;
                case R.id.btn9:
                    addText("9");
                    break;
                case R.id.btn0:
                    addText("0");
                    break;
            }
            switch(view.getId()){
                case R.id.btnC:
                    inputDisplay.setText("");
                    topDisplay.setText("");

                    break;
                case R.id.btnPlus:
                    buhoPut("+");
                    index = 1;
                    break;
                case R.id.btnMinus:
                    buhoPut("-");
                    index = 2;
                    break;
                case R.id.btnTime:
                    buhoPut("*");
                    index = 3;
                    break;
                case R.id.btnDivide:
                    buhoPut("/");
                    index = 4;
                    break;
                case R.id.btnEqual:
                    inputDisplay.setText(getResult(topDisplay.getText().toString()));
                    break;

                case R.id.btnBr1:
                    break;

                case R.id.btnBr2:
                    break;

                case R.id.btnDlt:
                    topDisplay.setText(deleteText(topDisplay.getText().toString()));
                    inputDisplay.setText(deleteText(inputDisplay.getText().toString()));
                    break;

                case R.id.btnDot:
                    addText(".");
                    break;
            }
        }
    };

    public void fly(Button button, String s){
        Button newButton = button;
        Button dummy = new Button(this);

        dummy.setText(newButton.getText().toString());
        dummy.setBackground(newButton.getBackground());
        dummy.setHeight(newButton.getHeight());
        dummy.setWidth(newButton.getWidth());

        int locarr[] = new int[2];
        button.getLocationInWindow(locarr);
        dummy.setX(locarr[0]);
        dummy.setY(locarr[1]);

        Toast.makeText(this, button.getX() + " " +dummy.getX() + " " + dummy.getY(), Toast.LENGTH_SHORT).show();
        frameLayout.addView(dummy);
        animstarter(dummy, s);
    }

    public void animstarter(Button button, String s){
        final String ss = s;
        int targetLocation[] = new int[2];
        int buttonLocation[] = new int[2];
        targetButton.getLocationInWindow(targetLocation);
        int distance[] = new int[2];
        button.getLocationInWindow(buttonLocation);

        distance[0] = targetLocation[0]-buttonLocation[0];
        distance[1] = targetLocation[1]-buttonLocation[1];
        ObjectAnimator animX = ObjectAnimator.ofFloat(button, "translationX", distance[0]);
        ObjectAnimator animY = ObjectAnimator.ofFloat(button, "translationY", distance[1]);
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP ) {
//            button.setTranslationZ(0);
//            Toast.makeText(getApplicationContext(), "확인", Toast.LENGTH_SHORT).show();
//        }

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(animX, animY);
        set.start();

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                addText(ss);
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        animX = ObjectAnimator.ofFloat(button, "translationX", 0);
        animY = ObjectAnimator.ofFloat(button, "translationY", 0);
        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(animX, animY);
        set2.setStartDelay(1000);
        set2.start();

    }
}
