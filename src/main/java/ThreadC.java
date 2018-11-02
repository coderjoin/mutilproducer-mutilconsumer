/**
 * @author qiaoying
 * @date 2018/11/2 10:05
 */
public class ThreadC extends Thread {

    private RepastService service;

    public ThreadC(RepastService service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.get();
    }
}
