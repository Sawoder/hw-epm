package ru.sawoder.epam.task06;

import java.math.BigDecimal;

/**
 * Singleton payment system for interaction with a wallet.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class PaymentSystem {
    private volatile BigDecimal wallet;

    private static volatile PaymentSystem instance;

    /**
     * Gets instance of singleton with double checked locking and volatile.
     *
     * @return Singleton instance
     */
    public static PaymentSystem getInstance() {
        PaymentSystem localInstance = instance;
        if (localInstance == null) {
            synchronized (PaymentSystem.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PaymentSystem();
                }
            }
        }
        return localInstance;
    }

    /**
     *  Creates default payment system with an initial wallet of 100.
     */
    private PaymentSystem() {
        this.wallet = BigDecimal.valueOf(100L);
    }

    /**
     * Synchronized withdraw money from {@code wallet} and gets status.
     *
     * @param money Amount of withdrawing
     * @return Status success or failure withdraw
     */
    public synchronized boolean withdraw(BigDecimal money) {
        checkMoney(money);
        if (wallet.compareTo(money) >= 0) {
            wallet = wallet.subtract(money);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Synchronized deposit money to {@code wallet} and gets new value.
     *
     * @param money Amount of depositing
     * @return {@code wallet} after deposit
     */
    public synchronized BigDecimal deposit(BigDecimal money) {
        checkMoney(money);
        wallet = wallet.add(money);
        return wallet;
    }

    /**
     * Checks new money on correctness.
     * Money can't be less or equal 0.
     *
     * @param money Money for operations
     */
    private void checkMoney(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Money must be greater than 0");
        }
    }

    public BigDecimal getWallet() {
        return wallet;
    }
}
