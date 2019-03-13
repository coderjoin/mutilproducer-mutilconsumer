package one;

/**
 * @author qiaoying
 * @date 2019-03-13 21:13
 */
public class Consumer extends Thread {
    private Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    public void run() {
        while(true){
             try {
               Thread.sleep(1000);
             } catch (InterruptedException e) {
                e.printStackTrace();
             }
            resource.remove();
        }
    }
}
