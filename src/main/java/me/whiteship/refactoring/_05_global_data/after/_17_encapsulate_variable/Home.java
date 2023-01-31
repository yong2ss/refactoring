package me.whiteship.refactoring._05_global_data.after._17_encapsulate_variable;

/**
 * 변수 캡슐화하기
 * - 변수를 변경하기보단 메소드를 변경하는 것이 더 낫다.
 * - 데이터가 사용되는 범위가 클수록 캡슐화하는 것이 중요해진다.
 *      - 함수를  사용해서 값을 변경하면, 보다 쉽게 검증 로직이나 변경에 따른 추가 작업이 편리하다.
 * - 불변 데이터의 경우에는 이런 리팩토링을 적용할 필요가 없다.
 */
public class Home {

    public static void main(String[] args) {
        System.out.println(Thermostats.getTargetTemperature());
        Thermostats.setTargetTemperature(68);
        Thermostats.setReadInFahrenheit(false);
    }
}
