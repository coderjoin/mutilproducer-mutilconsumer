/**
 * @author qiaoying
 * @date 2018/11/2 10:03
 */
public class Run {

    public static void main(String[] args) throws Exception{
        RepastService repastService = new RepastService();
        ThreadP[] arrayP = new ThreadP[60];
        ThreadC[] arrayC = new ThreadC[60];

        for (int i = 0; i < 60; i++){
            arrayP[i] = new ThreadP(repastService);
            arrayC[i] = new ThreadC(repastService);
        }

        Thread.sleep(2000);

        for (int i = 0; i < 60; i++){
            arrayP[i].start();
            arrayC[i].start();
        }
    }
}
