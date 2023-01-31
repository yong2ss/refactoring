package me.whiteship.refactoring._04_long_parameter_list.after._15_remove_flag_argument;

import java.time.LocalDate;

/**
 * 플래그 인수 제거하기
 * - 플래그는 보통 함수에 매개변수로 전달해서, 함수 내부 로직을 분기
 * - 플래그를 사용한 함수는 차이를 파악하기 어렵다
 * - 조건문 분해하기를 사용
 */
public class Order {

    private LocalDate placedOn;
    private String deliveryState;

    public Order(LocalDate placedOn, String deliveryState) {
        this.placedOn = placedOn;
        this.deliveryState = deliveryState;
    }

    public LocalDate getPlacedOn() {
        return placedOn;
    }

    public String getDeliveryState() {
        return deliveryState;
    }
}
