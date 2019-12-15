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

public class TestCaseAlgorithm {
    private static final int THREADS_AMOUNT = 4 ;
    private AliaserImpl aliaser;
    public TestCaseAlgorithm(AliaserImpl aliaser){
        this.aliaser=aliaser;
    }
    DataBaseApiImpl dataBaseApi = new DataBaseApiImpl();
    private int dbTableSize=0;
    public void startTestCaseThreads() throws InterruptedException {
        TestCaseProducer testCaseProducer = new TestCaseProducer(dataBaseApi.getDataBaseTableSize("recipe"));
        //System.out.println(testCaseProducer.getTestCases().size());
        int singleListSize =Math.round(testCaseProducer.getTestCases().size()/4)+1;
        List<List<TestCase>> testCases=Lists.partition(testCaseProducer.getTestCases(),singleListSize);
        ThreadPoolExecutor thread_factory= (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS_AMOUNT);
        for(int i=0;i<THREADS_AMOUNT;i++){

            List<TestCase> testcases = testCases.get(i);
            thread_factory.submit(new Runnable() {
                @Override
                public void run() {
                    new AliaserThread(testcases).start2();
                }
            });
        }
        thread_factory.shutdown();
        thread_factory.awaitTermination(1, TimeUnit.DAYS);

        System.out.println(TestCasesBuffer.getInstance().getTestCases().size());;


    }
}
