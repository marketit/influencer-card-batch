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
    public void activitiesFixedPeriodLock() {
        postingMapper.activitiesFixedPeriodLock();
    }
}
