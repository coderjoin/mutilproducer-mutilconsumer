package one;

/**
 * @author qiaoying
 * @date 2019-03-13 20:51
 */
public class Main {

    public static void main(String[] args) {
        Resource resource = new Resource();

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
