package inflma.adminbatch.scheduler;

import inflma.adminbatch.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class PostingScheduler {
    private final PostingService postingService;

    // 지정일자에 대한 락 배치
    @Scheduled(cron = "0 5 2 * * ?")
    public void activitiesFixedPeriodLock(){
        String activitiesList = postingService.activitiesFixedPeriodLock();
        activityLock(activitiesList);

    }

    // 배송완료일로 부터 락 배치
    @Scheduled(cron = "0 10 2 * * ?")
    public void activitiesPeriodLock(){
        String activitiesList = postingService.activitiesPeriodLock();
        activityLock(activitiesList);
    }


    @Scheduled(cron = "0 15 2 * * ?")
    public void postingScheduler() {
        String activityList= postingService.findByPostedComplete();
        if(!activityList.isEmpty()){
            HashMap map = new HashMap<>();
            map.put("activityId",activityList );
            postingService.completePosting(map);
        }
    }

    private void activityLock(String activityList){
        if(!activityList.isEmpty()){
            HashMap map = new HashMap<>();
            map.put("activityId",activityList );
            postingService.activitiesLock(map);
        }
    }
}
