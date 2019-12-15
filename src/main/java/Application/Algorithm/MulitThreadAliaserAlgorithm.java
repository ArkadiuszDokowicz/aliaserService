package Application.Algorithm;

import Application.Model.Alias;
import Application.Model.TestCase;
import Application.Services.Aliaser.AliaserImpl;
import Application.api.databaseAPi.DataBaseApiImpl;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MulitThreadAliaserAlgorithm {
    private int THREADS_AMOUNT ;
    private AliaserImpl aliaser;
    public MulitThreadAliaserAlgorithm(AliaserImpl aliaser,int threads){
        THREADS_AMOUNT=threads;
        this.aliaser=aliaser;
    }
    DataBaseApiImpl dataBaseApi = new DataBaseApiImpl();
    private int dbTableSize=0;
    public void startAliaserThreads() throws InterruptedException {
        dbTableSize=dataBaseApi.getDataBaseTableSize("recipe");
        if(dbTableSize!=0 && dbTableSize>100){
            int period = (int) Math.floor(dbTableSize/THREADS_AMOUNT);
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
