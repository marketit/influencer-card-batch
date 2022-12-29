package inflma.opadminbatch.service;

import inflma.opadminbatch.dao.PostingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {
    private final PostingMapper postingMapper;

    public List<Long> findByPostedComplete() {
        return postingMapper.findByPostedComplete();
    }
}
