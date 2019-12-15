package Application.Algorithm;

import Application.Services.Aliaser.AliaserImpl;
import Application.api.databaseAPi.DataBaseApiImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MulitThreadAliaserAlgorithm {
    private static final int THREADS_AMOUNT = 4 ;
    private AliaserImpl aliaser;
    public MulitThreadAliaserAlgorithm(AliaserImpl aliaser){
        this.aliaser=aliaser;
    }
    DataBaseApiImpl dataBaseApi = new DataBaseApiImpl();
    private int dbTableSize=0;
    public void startThreads() throws InterruptedException {
        dbTableSize=dataBaseApi.getDataBaseTableSize("recipe");
        if(dbTableSize!=0 && dbTableSize>100){
            int period = (int) Math.floor(dbTableSize/4);
            ThreadPoolExecutor thread_factory= (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS_AMOUNT);
                int first=0,last=0;
            for(int i=0;i<THREADS_AMOUNT;i++){
               if(i==0){
                   first=1;last=period;
               }
               if(i==1){
                   first=period*i+1;
                   last=first+period;
               }
               if(i==2){
                   first=period*i+2;
                   last=first+period;
               }
               if(i==3){
                   first=period*i+3;
                   last=dbTableSize;
               }

                int finalFirst = first;
                int finalLast = last;
                System.out.println(finalFirst + " " + finalLast);
                thread_factory.submit(new Runnable() {
                    @Override
                    public void run() {
                     new AliaserThread(finalFirst, finalLast,aliaser).start();
                    }
                });
            }
            thread_factory.shutdown();
            thread_factory.awaitTermination(1, TimeUnit.DAYS);

        }
    }
}
