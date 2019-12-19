package Application.Services;

import Application.Model.TestCase;
import org.springframework.stereotype.Service;

@Service
public class TestCaseService {
    private TestCase testCase;

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }
}
