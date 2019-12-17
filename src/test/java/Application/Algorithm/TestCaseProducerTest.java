package Application.Algorithm;

import org.junit.Test;

public class TestCaseProducerTest {

    @Test
    public void shouldCreatePossiblyTestCases(){
        //given
        TestCaseProducer testCaseProducer;
        //when
        Integer dbSize =10;
        testCaseProducer = new TestCaseProducer(dbSize);
        testCaseProducer.createPossiblyTestCases();
        //then
        }

}
