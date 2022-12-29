package inflma.opadminbatch.scheduler;

import groovy.util.logging.Slf4j;
import inflma.opadminbatch.service.PostingService;
import inflma.opadminbatch.service.PostingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostingScheduler {
    private final PostingService postingService;

    // "검수완료" 태그
    // post_status 정상 여부 판단
    // tag_status 정상 여부 판단
    // 캠페인 유지기간을 지켰는지

    // 위에 조건을 충족했다면 duty_complete_at
    @Scheduled(cron = "0 32 21 * * ?")
    public void postingScheduler() {
        List<Long> activityList= postingService.findByPostedComplete();
        System.out.println(activityList.toString());
    }
}
