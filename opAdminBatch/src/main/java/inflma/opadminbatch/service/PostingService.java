package inflma.opadminbatch.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface PostingService {

    String findByPostedComplete();

    void completePosting(HashMap map);

    void activitiesFixedPeriodLock();
}
