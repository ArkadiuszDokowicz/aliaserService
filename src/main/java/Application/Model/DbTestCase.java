package Application.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DbTestCase implements Serializable {
    private TestCase testCases;

    public DbTestCase(TestCase testCases) {
        this.testCases = testCases;
    }

    public DbTestCase() {
    }

    public TestCase getTestCase() {
        return testCases;
    }

    public void setTestCases(TestCase testCases) {
        this.testCases = testCases;
    }
}
