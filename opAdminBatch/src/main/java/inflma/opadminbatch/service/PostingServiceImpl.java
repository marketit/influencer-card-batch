package inflma.opadminbatch.service;

import inflma.opadminbatch.dao.PostingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {
    private final PostingMapper postingMapper;

    @Override
    public String findByPostedComplete() {
        return postingMapper.findByPostedComplete();
    }

    @Override
    public void completePosting(HashMap map) {
        postingMapper.completePosting(map);
    }

    @Override
    public String activitiesFixedPeriodLock() {
        return postingMapper.activitiesFixedPeriodLock();
    }

    @Override
    public String activitiesPeriodLock() {
        return postingMapper.activitiesPeriodLock();
    }

    @Override
    public void activitiesLock(HashMap map) {
        postingMapper.activitiesLock(map);
    }
}
