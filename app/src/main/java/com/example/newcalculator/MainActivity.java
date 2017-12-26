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

import static android.view.View.X;

//implements를 통해 lister를 달아주는 방법도 있다.

public class MainActivity extends AppCompatActivity {

    TextView topDisplay, inputDisplay;
    int index;
    Button targetButton;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    FrameLayout frameLayout;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        initView();


    }

    public void initView(){
        linearLayout = (LinearLayout) findViewById(R.id.stage2);
        targetButton = (Button) findViewById(R.id.targetButton);

        topDisplay = (TextView) findViewById(R.id.textView);
        inputDisplay = (TextView) findViewById(R.id.inputDisplay);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);


        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        btn0.setOnClickListener(onClickListener);
        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);
        btn3.setOnClickListener(onClickListener);
        btn4.setOnClickListener(onClickListener);
        btn5.setOnClickListener(onClickListener);
        btn6.setOnClickListener(onClickListener);
        btn7.setOnClickListener(onClickListener);
        btn8.setOnClickListener(onClickListener);
        btn9.setOnClickListener(onClickListener);
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

    public String calculating(String s){
        boolean flag = true;
        StringBuffer buffer = new StringBuffer(s);
        while(flag) {

            int lastIndex = buffer.lastIndexOf("(");
            int firstOccur = buffer.indexOf(")", lastIndex);

            if(lastIndex==-1){
                flag = false;

            }else{
                buffer.replace(lastIndex, firstOccur+1, getResult(s.substring(lastIndex+1, firstOccur)));
            }
        }

        return getResult(buffer.toString());
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

    public void foreBracePut(){
        if(index!=0 || topDisplay.getText().toString().equals("")){
            topDisplay.append("(");
        }else{
            topDisplay.append("*" + "(");
            inputDisplay.setText("");
        }
    }

    public void backBarcePut(){
        topDisplay.append(")");

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()) {
                case R.id.btn1:

                    fly(btn1, "1");

                    break;
                case R.id.btn2:
                    fly(btn2, "2");

                    break;
                case R.id.btn3:
                    fly(btn3, "3");

                    break;
                case R.id.btn4:
                   fly(btn4, "4");

                    break;
                case R.id.btn5:
                    fly(btn5, "5");

                    break;
                case R.id.btn6:
                    fly(btn6, "6");

                    break;
                case R.id.btn7:
                    fly(btn7, "7");

                    break;
                case R.id.btn8:
                    fly(btn8, "8");
                    break;
                case R.id.btn9:
                    fly(btn9, "9");
                    break;
                case R.id.btn0:
                    fly(btn0, "0");
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
                    inputDisplay.setText(calculating(topDisplay.getText().toString()));
                    break;

                case R.id.btnBr1:
                    foreBracePut();
                    break;

                case R.id.btnBr2:
                    backBarcePut();
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

        if(button instanceof Button){ // button 변수가 Button 클래스의 인스턴스인지를 체크

        }

        //dummy를 날리는 이유, 만약 실제 버튼을 날리면 실제 버튼이 담겨있는 레이아웃 때문에 버튼이 레이아웃을 벗어나지 못한다. 따라서 dummy를 최상위 레이아웃에 넣어서 하면 보이게 된다.

        Button dummy = new Button(this);
        dummy.setLayoutParams(new ActionBar.LayoutParams(button.getWidth(), button.getHeight()));
        dummy.setText(button.getText().toString());
        dummy.setBackground(button.getBackground());

        LinearLayout parent = (LinearLayout) button.getParent();


        int locarr[] = new int[2];
        button.getLocationInWindow(locarr);
        Toast.makeText(this, button.getX() + " " + parent.getX() + " " + button.getY() + " " + parent.getY(), Toast.LENGTH_SHORT).show();
        dummy.setX(button.getX()+parent.getX()); //button의 위치는 button의 parent layout에 대한 상대적인 값이고, layout의 위치는 FrameLayout에서의 상대적인 위치이기 때문에 이 두개를 더해줘야 한다.
        dummy.setY(button.getY()+parent.getY());
        frameLayout.addView(dummy);
        animstarter(dummy, s, frameLayout);
        //dummy.destroyDrawingCache();
    }

    public void animstarter(final Button button, String s, final FrameLayout layout){
        final String ss = s;
        int targetLocation[] = new int[2];
        int buttonLocation[] = new int[2];
        targetButton.getLocationInWindow(targetLocation);
        int distance[] = new int[2];
        button.getLocationInWindow(buttonLocation);

        //넘겨받는 button이 최상위 레이아웃인 FrameLayout에 있기 때문에 아래 distance 구하는 식은 사용하면 안된다. 부정확할 수 있다.
        distance[0] = targetLocation[0]-buttonLocation[0];
        distance[1] = targetLocation[1]-buttonLocation[1];
        ObjectAnimator animX = ObjectAnimator.ofFloat(button, "translationX", targetButton.getX());
        ObjectAnimator animY = ObjectAnimator.ofFloat(button, "translationY", targetButton.getY());
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
                layout.removeView(button);

            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

    }
}
