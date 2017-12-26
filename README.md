# 사칙연산 규칙대로 계산해주는 계산기 + 버튼을 눌렀을 때 버튼이 날아가는 애니메이션
##### 괄호 있는 계산과 버튼이 날라가는 로직 설명은 추후 추가되어야 함

### 기본 로직은 다음과 같다.

##### 1. 숫자와 부호를 화면에 띄운다.
##### 2. '='를 누르면 괄호 안에 있는 식을 먼저 계산해준다.
```java
public String calculating(String s){
    boolean flag = true;
    StringBuffer buffer = new StringBuffer(s);
    while(flag) { //괄호가 없을 때까지 계산

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
```
##### 3. 계산하는 방법은 넘어온 String 값을 잘게 쪼개어 ArrayList에 넣는다. 쪼개는 방법은 다음과 같이 정규식을 써서 한다.

```java
String strArray[] = result.split("(?<=[*/()+-])|(?=[*/+()-])");
for(String put : strArray){
            list.add(put);
        }
```

##### 4. 곱하기와 나누기 먼저 계산하고 그 다음에 더하기 빼기를 계산한다.
```java
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
```

<br>


## 배운 것

##### 1. 정규식
##### > 정규식이란 텍스트 데이터 중에서 원하는 조건(패턴, pattern)과 일치하는 문자열을 찾아내기 위해 사용하는 것으로 미리 정의된 기호와 문자를 이용해서 작성한 문자열을 의미, java.util.regex 패키지에 있는 Match 클래스와 Pattern 클래스를 사용하여 문자열을 정규표현식으로 검증할 수 있다. 해당 클래스 사용법은 자바 클래스 카테고리에 가면 있다. [자바 Pattern Class 링크](http://docs.oracle.com/javase/8/docs/api/index.html)
[[출처] 자바 정규식 표현|작성자 해처리](http://blog.naver.com/phcs1219/140092773115) <-좋은 링크임

[출처] [Java/자바] 정규식(Regular Expression) - Pattern 그리고 Match|작성자 대부류

##### 2. ArrayList 응용
##### > ArrayList의 get, set, remove 함수를 이용하여 곱하기와 나누기가 있는 배열을 찾은 다음 계산, 그리고 계산된 값은 set을 통해 값을 바꿔주고 나머지 필요없는 배열은 삭제해주는 구조
```java
while(i<list.size()-1){
            if(list.get(i).equals("*")){
                list.set(i-1, String.valueOf(Double.parseDouble(list.get(i-1)) * Double.parseDouble(list.get(i+1))));
                list.remove(i);
                list.remove(i);
                i=0;
            }
          }
```

##### 3. dummy를 이용한 애니메이션 구현
##### > 숫자 버튼을 누를 때 마다 버튼이 숫자를 띄워주는 화면으로 날아가는 애니메이션을 구현함, 처음에는 실제 버튼을 ObjectAnimator를 통해 구현하고자 하였으나 버튼이 속해있는 LinearLayout을 벗어나지 못하는 현상이 발생함, 버튼의 애니메이션은 그 버튼의 parent view 안에서만 가능한 것 같음, 따라서 dummy를 이용하였는데 코드는 다음과 같다.

```java
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
  ...
   set.addListener(new Animator.AnimatorListener() {
     ...
     @Override
            public void onAnimationEnd(Animator animator) {
                addText(ss);
                layout.removeView(button);

            }
            ...
          }
    }
```

##### 최상위 layout인 FrameLayout에 dummy 버튼 객체를 생성해준다. 이 dummy 버튼 객체를 눌리는 숫자 버튼의 속성과 똑같게 만들어 준뒤 좌표를 정하여 날리면 된다. 그리고 사용 후에는 AnimationListner를 통해 최상위 FrameLayout에 있는 dummy view를 제거해주면 된다.

##### 4. ObjectAnimator를 쓸 때의 주의점
##### > ObjectAnimator 쓸 때 ObjectAnimator.ofFloat 함수의 파라미터 중 float 값을 넣는 곳이 있다. 어느 위치로 보낼 것인지를 넣는 곳인데 내가 날리고자 하는 뷰가 어느 위치에 있느냐에 따라 달라진다. 뷰그룹에 맨 왼쪽 상단이 (0,0)이기 때문에 그것을 잘 고려해서 넣어야 한다.
```java
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
```
