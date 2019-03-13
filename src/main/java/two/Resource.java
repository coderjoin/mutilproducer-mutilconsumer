package two;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author qiaoying
 * @date 2019-03-13 21:14
 */
public class Resource {

    private int num = 0;

    private int size = 10;

    private Lock lock;

    private Condition producerCondition;

    private Condition consumerCondition;

    public Resource(Lock lock, Condition producerCondition, Condition consumerCondition) {
        this.lock = lock;
        this.consumerCondition = consumerCondition;
        this.producerCondition = producerCondition;
    }

    public  void remove() {
        lock.lock();
        try {
            if (num > 0) {
                num--;
                System.out.println("消费者" + Thread.currentThread().getName() +
                        "消耗资源，当前线程池有" + num);
                producerCondition.signalAll();
            } else {
                try {
                    consumerCondition.await();
                    System.out.println("消费者" + Thread.currentThread().getName() +
                            "线程进入等待状态");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }

    }

    public  void add () {
        lock.lock();
        try {
            if (num < size) {
                num++;
                System.out.println(Thread.currentThread().getName() + "生产一件资源，当前资源池有"
                        + num + "个");
                consumerCondition.signalAll();
            } else {
                try {
                    consumerCondition.await();
                    System.out.println(Thread.currentThread().getName()+"线程进入等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }

    }
}
