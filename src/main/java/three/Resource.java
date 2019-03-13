package three;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author qiaoying
 * @date 2019-03-13 21:14
 */
public class Resource {

    private BlockingQueue resourceQueue = new LinkedBlockingQueue(10);



    public  void remove() {

        try {
            resourceQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费者" + Thread.currentThread().getName() +
                        "消耗资源，当前线程池有" + resourceQueue.size());


    }

    public  void add () {

        try {
            resourceQueue.put(1);
            System.out.println(Thread.currentThread().getName() + "生产一件资源，当前资源池有"
                    + resourceQueue.size() + "个");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }
}
