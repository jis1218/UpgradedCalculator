# 사칙연산 규칙대로 계산해주는 계산기
##### 부호 있는 계산과 버튼이 날라가는 로직 설명은 추후 추가되어야 함

### 기본 로직은 다음과 같다.

##### 1. 숫자와 부호를 화면에 띄운다.
##### 2. '='를 누르면 화면의 String 값을 잘게 쪼개어 ArrayList에 넣는다. 쪼개는 방법은 다음과 같이 정규식을 써서 한다.

```java
String strArray[] = result.split("(?<=[*/()+-])|(?=[*/+()-])");
for(String put : strArray){
            list.add(put);
        }
        ```
##### 3. 곱하기와 나누기 먼저 계산하고 그 다음에 더하기 빼기를 계산한다.
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
