package inflma.opAdmin.scheduler;

import inflma.opAdmin.service.PostingServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class postingScheduler {
    private PostingServiceImpl postingService;

    // "검수완료" 태그
    // post_status 정상 여부 판단
    // tag_status 정상 여부 판단
    // 캠페인 유지기간을 지켰는지

    // 위에 조건을 충족했다면 duty_complete_at
    @Scheduled(cron = "0 20 17 * * ?")
    public void postingScheduler(PostingServiceImpl postingService) {
        List<Long> activityList= postingService.findByPostedComplete();
        System.out.println(activityList.toString());
    }
}
