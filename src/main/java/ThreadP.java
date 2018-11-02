/**
 * @author qiaoying
 * @date 2018/11/2 10:05
 */
public class ThreadP extends Thread {

    private RepastService service;

    public ThreadP(RepastService service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.set();
    }
}
