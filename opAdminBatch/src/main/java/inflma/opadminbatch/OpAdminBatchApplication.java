package inflma.opadminbatch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class OpAdminBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpAdminBatchApplication.class, args);
    }

}
