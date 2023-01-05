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


    @Scheduled(cron = "0 15 2 * * ?")
    public void postingScheduler() {
        String activityList= postingService.findByPostedComplete();
        if(!activityList.isEmpty()){
            HashMap map = new HashMap<>();
            map.put("activityId",activityList );
            postingService.completePosting(map);
        }
    }

}
