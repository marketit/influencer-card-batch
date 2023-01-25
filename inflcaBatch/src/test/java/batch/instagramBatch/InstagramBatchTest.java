package batch.instagramBatch;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringBatchTest
class InstagramBatchTest {

    @Autowired
    private InstagramBatch batch;

    @Test
    void BatchTest() throws Exception {
        JobExecution result = batch.execute();
        assertEquals(result.getExitStatus(), ExitStatus.COMPLETED);
    }
}