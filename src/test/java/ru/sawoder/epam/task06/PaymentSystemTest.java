package ru.sawoder.epam.task06;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Testing {@link PaymentSystem} class.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class PaymentSystemTest {
    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        Field instance = PaymentSystem.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void withdrawTest() {
        PaymentSystem paymentSystem = PaymentSystem.getInstance();

        paymentSystem.withdraw(BigDecimal.ONE);
        assertEquals(BigDecimal.valueOf(99), paymentSystem.getWallet());
        paymentSystem.withdraw(BigDecimal.ONE);
        assertEquals(BigDecimal.valueOf(98), paymentSystem.getWallet());
        paymentSystem.withdraw(BigDecimal.valueOf(91L));
        assertEquals(BigDecimal.valueOf(7), paymentSystem.getWallet());
        paymentSystem.withdraw(BigDecimal.valueOf(7L));
        assertEquals(BigDecimal.ZERO, paymentSystem.getWallet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawTest_IllegalArgumentExceptionThrown() {
        PaymentSystem.getInstance().withdraw(BigDecimal.ZERO);
    }

    @Test
    public void depositTest() {
        PaymentSystem paymentSystem = PaymentSystem.getInstance();

        paymentSystem.deposit(BigDecimal.ONE);
        assertEquals(BigDecimal.valueOf(101L), paymentSystem.getWallet());
        paymentSystem.deposit(BigDecimal.ONE);
        assertEquals(BigDecimal.valueOf(102L), paymentSystem.getWallet());
        paymentSystem.deposit(BigDecimal.valueOf(100L));
        assertEquals(BigDecimal.valueOf(202L), paymentSystem.getWallet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositTest_IllegalArgumentExceptionThrown() {
        PaymentSystem.getInstance().deposit(BigDecimal.ZERO);
    }
}
