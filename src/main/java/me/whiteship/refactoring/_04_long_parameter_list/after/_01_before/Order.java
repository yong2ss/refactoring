package me.whiteship.refactoring._04_long_parameter_list.after._01_before;

/**
 * 긴 매개변수 목록
 * - 함수에 매개변수가 ㅁ낳을수록 함수의 역할을 이해하기 어렵다
 * - 어떤 매개변수를 다른 매개변수를 통해 알아낼 수 있다면, "매개변수를 질의 함수로 바꾸기"
 * - 기존 자료구조에서 세부적인 데이터를 가져와서 여러 매개변수로 넘기는 대신, "객체 통째로 넘기기"
 * - 일부 매개변수들이 대부분 같이 넘겨진다면, "매개변수 객체 만들기"
 * - 매개변수가 플래그로 사용된다면, "플래그 인수 제거하기"
 * - 일부 매개변수를 공통적으로 사용한다면 "여러 함수를 클래스로 묶기"로 클래스의 필드로 만들고 전달할 매개변수 목록을 줄인다
 */
public class Order {

    private int quantity;

    private double itemPrice;

    public Order(int quantity, double itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public double finalPrice() {
        double basePrice = this.quantity * this.itemPrice;
        int discountLevel = this.quantity > 100 ? 2 : 1;
        return this.discountedPrice(basePrice, discountLevel);
    }

    private double discountedPrice(double basePrice, int discountLevel) {
        return discountLevel == 2 ? basePrice * 0.9 : basePrice * 0.95;
    }
}
