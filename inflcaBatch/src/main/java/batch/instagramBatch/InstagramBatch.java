package batch.instagramBatch;

import batch.domain.PlatformAccount;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class InstagramBatch {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SqlSessionFactory sqlSessionFactory;
    private final JobLauncher jobLauncher;
    private final String JOB_NAME = "testJob";
    private final static int CHUNK_SIZE = 1000;
    private final InstagramWriter writer;

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES, zone = "GMT+09:00")
    public JobExecution execute() throws Exception {
       return jobLauncher.run(
            runJob(),
            new JobParametersBuilder()
                .addString("datetime", LocalDateTime.now().toString())
                .toJobParameters()
        );

    }


    @Bean(JOB_NAME + "_job")
    private Job runJob() {
        return jobBuilderFactory.get(JOB_NAME + "Job")
            .start(step())
            .build();
    }

    @Bean(JOB_NAME + "_step")
    @JobScope
    private Step step() {
        return stepBuilderFactory.get(JOB_NAME + "Step")
            .<PlatformAccount, PlatformAccount>chunk(CHUNK_SIZE)
            .reader(reader())
            .writer(writer)
            .build();
    }

    @Bean(JOB_NAME + "_reader")
    @StepScope
    private MyBatisPagingItemReader<PlatformAccount> reader() {
        Map<String, Object> parameterValues = new HashMap<>();
        return new MyBatisPagingItemReaderBuilder<PlatformAccount>()
            .pageSize(CHUNK_SIZE)
            .sqlSessionFactory(sqlSessionFactory)
            .queryId("batch.repository.PlatformAccountMapper.getPlatformAccounts")
            .parameterValues(parameterValues)
            .build();
    }
}
