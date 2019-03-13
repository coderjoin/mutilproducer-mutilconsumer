package two;

/**
 * @author qiaoying
 * @date 2019-03-13 21:06
 */
public class Producer extends Thread{
    private Resource resource;

    public Producer(Resource resource){
        this.resource = resource;
        }

        public void run() {

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.add();
            }
        }
}
