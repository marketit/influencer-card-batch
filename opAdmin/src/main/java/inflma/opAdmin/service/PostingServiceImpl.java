package inflma.opAdmin.service;

import inflma.opAdmin.dao.PostingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl {

    private final PostingMapper postingMapper;

    public List<Long> findByPostedComplete() {
        return postingMapper.findByPostedComplete();
    }
}
