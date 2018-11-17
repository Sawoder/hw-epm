package ru.sawoder.epam.cashmachine;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Service for creates and starts the threads of {@link CashMachine}
 * with using {@link ExecutorService}.
 *
 * @author Ilya Lebedev
 * @version 1.0
 * @since 1.8
 */
public class CashMachineService {
    /**
     * Start of execution of the interaction of cash machines and payment system.
     *
     * @param count Amount of {@link CashMachine}
     * @param pool Max threads for working
     * @param minTimes Minimum for times of loops
     * @param maxTimes Maximum for times of loops
     */
    public void start(int count, int pool, int minTimes, int maxTimes) {
        CashMachineService cashMachineService = new CashMachineService();
        try {
            BlockingQueue<Runnable> queue = cashMachineService.createCashMachineQueue(count, minTimes, maxTimes);
            ExecutorService executorService = cashMachineService.createThreadPoolExecutor(queue, pool);
            for (int i = 0; i < count; i++) {
                executorService.submit(queue.take());
            }
            executorService.shutdown();
            executorService.awaitTermination(2L, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates fixed blocking queue for executor service.
     * Gets random value for times of loop each cash machine.
     *
     * @param count Amount of {@link CashMachine}
     * @param minTimes Minimum for times of loops
     * @param maxTimes Maximum for times of loops
     * @return New blocking queue of runnable.
     * @throws InterruptedException If can't put new cash machine
     */
    private BlockingQueue<Runnable> createCashMachineQueue(int count, int minTimes, int maxTimes) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(count);
        for (int i = 0; i < count; i++) {
            queue.put(new CashMachine(i, ThreadLocalRandom.current().nextInt(minTimes, maxTimes)));
        }
        return queue;
    }

    /**
     * Creates {@link ThreadPoolExecutor} with {@link BlockingQueue<Runnable>} cash machines.
     *
     * @param queue Queue of runnable threads
     * @param pool Max threads for working
     * @return New instance of {@link ThreadPoolExecutor}
     */
    private ExecutorService createThreadPoolExecutor(BlockingQueue<Runnable> queue, int pool) {
        return new ThreadPoolExecutor(pool, pool, 0L, TimeUnit.MILLISECONDS, queue);
    }

    public static void main(String[] args) {
        CashMachineService service = new CashMachineService();
        service.start(10, 4, 1, 10);
    }
}
