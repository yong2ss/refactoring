package me.whiteship.refactoring._05_global_data.after._01_before;

/**
 * 전역 데이터
 * - 전역 데이터 (ex. 자바의 public static 변수)
 * - 전역 데이터는 아무곳에서나 변경될 수 있다는 문제
 * - 어떤 코드로 인해 값이 바뀐 것인지 파악이 어렵다.
 * - 클래스 변수(필드)도 비슷한 문제를 겪을 수 있다.   ==> 보통 그래서 보통 상수만 썼다
 * - "변수 캡슐화하기"를 적용해서 접근을 제어하거나, 어디서 사용하는지 파악하기 쉽게 만들 수 있다.
 * - 파라켈수스의 격언 "약과 독의 차이를 결정하는 것은 사용량일뿐"
 */
public class Home {

    public static void main(String[] args) {
        System.out.println(Thermostats.targetTemperature);
        Thermostats.targetTemperature = -1111600;
        Thermostats.fahrenheit = false;
    }
}
