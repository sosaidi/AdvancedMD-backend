package com.springjwt;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SpringBootSecurityJwtApplicationTests {

    @Test
    public void contextLoads() {

    }
    @Test
    public void incorrectWeight() {
        ShippingService shippingService = new ShippingService();
        assertThrows(IllegalStateException.class, () -> shippingService.calculateShippingFee(-1));
    }

    @Test
    public void firstRangeWeight() {
        ShippingService shippingService = new ShippingService();
        assertEquals(5, shippingService.calculateShippingFee(1));
    }

}
