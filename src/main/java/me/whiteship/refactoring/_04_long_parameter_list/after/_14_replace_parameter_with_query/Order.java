package me.whiteship.refactoring._04_long_parameter_list.after._14_replace_parameter_with_query;

/**
 * 매개변수를 질의 함수로 바꾸기
 * - 함수의 매개변수 목록은 함수의 다양성을 대변하며, 짧을수록 이해하기 좋다
 * - 어떤 한 매개변수를 다른 매개변수를 통해 알아낼수 있다면 "중복 매개변수"일 수 있다
 * - 매개변수에 값을 전달하는 것은 "함수를 호출하는 쪽"의 책임이며 호출하는 쪽의 책임을 줄이고 내부에서 되도록 처리하자.
 * - "임시 변수를 질의 함수로 바꾸기" + "함수 선언 변경하기"
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
        return this.discountedPrice(basePrice);
    }

    private int discountLevel() {
        return this.quantity > 100 ? 2 : 1;
    }

    private double discountedPrice(double basePrice) {
        return discountLevel() == 2 ? basePrice * 0.90 : basePrice * 0.95;
    }
}
