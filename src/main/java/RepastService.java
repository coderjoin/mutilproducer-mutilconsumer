import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qiaoying
 * @date 2018/11/2 09:46
 */
public class RepastService {

    volatile private Semaphore setSemaphore = new Semaphore(10);
    volatile private Semaphore getSemaphore = new Semaphore(20);
    volatile private ReentrantLock lock = new ReentrantLock();
    volatile private Condition setCondition = lock.newCondition();
    volatile private Condition getCondition = lock.newCondition();

    volatile private Object[] producePostion = new Object[4];

    private boolean isEmpty(){
        boolean isEmpty = true;
        for (int i = 0; i < producePostion.length; i++){
            if (producePostion[i] != null){
                isEmpty = false;
                break;
            }
        }
        if (isEmpty == true){
            return true;
        } else {
            return false;
        }
    }

    private boolean isFull(){
        boolean isFull = true;
        for (int i = 0; i < producePostion.length; i++){
            if (producePostion[i] == null){
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public void set(){

        try {
            setSemaphore.acquire();
            lock.lock();
            while (isFull()){
                setCondition.await();
            }
            for (int i = 0; i < producePostion.length; i++){
                if (producePostion[i] == null){
                    producePostion[i] = "data";
                    System.out.println(Thread.currentThread().getName()
                    + " produced " + producePostion[i]);
                    break;
                }
            }
            getCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            setSemaphore.release();
        }
    }

    public void get(){

        try {
            getSemaphore.acquire();
            lock.lock();
            while (isEmpty()){
                getCondition.await();
            }
            for (int i = 0; i < producePostion.length; i++){
                if (producePostion[i] != null){

                    System.out.println(Thread.currentThread().getName()
                            + " comsumed " + producePostion[i]);
                    producePostion[i] = null;
                    break;
                }
            }
            setCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            getSemaphore.release();
        }
    }
}
