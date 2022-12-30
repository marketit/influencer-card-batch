package inflma.opadminbatch.scheduler;

import inflma.opadminbatch.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class PostingScheduler {
    private final PostingService postingService;

    // 지정일자에 대한 락 배치
    @Scheduled(cron = "0 45 11 * * ?")
    public void activitiesFixedPeriodLock(){
        postingService.activitiesFixedPeriodLock();
    }


    // 배송완료일로 부터 락 배치


    @Scheduled(cron = "0 45 11 * * ?")
    public void postingScheduler() {
        String activityList= postingService.findByPostedComplete();
        if(!activityList.isEmpty()){
            HashMap map = new HashMap<>();
            map.put("activityId",activityList );
            postingService.completePosting(map);
        }
    }
}
