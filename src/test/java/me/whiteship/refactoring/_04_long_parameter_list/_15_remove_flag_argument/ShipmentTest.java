package me.whiteship.refactoring._04_long_parameter_list._15_remove_flag_argument;

import me.whiteship.refactoring._04_long_parameter_list.after._15_remove_flag_argument.Order;
import me.whiteship.refactoring._04_long_parameter_list.after._15_remove_flag_argument.Shipment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentTest {

    @Test
    void deliveryDate() {
        LocalDate placedOn = LocalDate.of(2021, 12, 15);
        Order orderFromWA = new me.whiteship.refactoring._04_long_parameter_list.after._15_remove_flag_argument.Order(placedOn, "WA");

        me.whiteship.refactoring._04_long_parameter_list.after._15_remove_flag_argument.Shipment shipment = new Shipment();
        assertEquals(placedOn.plusDays(1), shipment.rushDeliveryDate(orderFromWA));
        assertEquals(placedOn.plusDays(2), shipment.regularDeliveryDate(orderFromWA));
    }

}