package ru.sawoder.epam.cashmachine;

import java.math.BigDecimal;

/**
 * Cash machine for multithreading and working with {@link PaymentSystem}.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class CashMachine implements Runnable {
    /**
     * To identify a cash machine.
     */
    private int id;

    /**
     * How many loops will be
     */
    private int times;

    public CashMachine(int id, int times) {
        this.id = id;
        this.times = times;
    }

    /**
     * Gets a instance of {@link PaymentSystem} and {@code times} times
     * try to withdraw or (if not much money) deposit by a random money from 1 to 100.
     * Each loop has sleep 100 mills.
     */
    @Override
    public void run() {
        PaymentSystem paymentSystem = PaymentSystem.getInstance();
        for (int i = 0; i < times; i++) {
            BigDecimal money = generateRandomBigDecimalFromRange(BigDecimal.ONE, BigDecimal.valueOf(100L));
            synchronized (PaymentSystem.class) {
                if (paymentSystem.withdraw(money)) {
                    System.out.println("Person has withdrawn " + money + " from ATM #" + id + " and now has " + paymentSystem.getWallet());
                } else {
                    BigDecimal deposited = paymentSystem.deposit(money);
                    System.out.println("Person has deposited " + money + " to ATM #" + id + " and now has " + deposited);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ATM #" + id + " has been stopped. Times: " + times);
    }

    /**
     * Generates a random {@code BigDecimal} in range from min to max.
     *
     * @param min Minimum in range
     * @param max Maximum in range
     * @return new a random {@code BigDecimal} value
     */
    private BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
