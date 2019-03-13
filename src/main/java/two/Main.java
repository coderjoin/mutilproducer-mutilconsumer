package two;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qiaoying
 * @date 2019-03-13 20:51
 */
public class Main {


    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();


        Resource resource = new Resource(lock, producerCondition, consumerCondition);

        Producer p1 = new Producer(resource);
        Producer p2 = new Producer(resource);
        Producer p3 = new Producer(resource);

        Consumer c1 = new Consumer(resource);


        p1.start();
        p2.start();
        p3.start();
        c1.start();
    }
}
