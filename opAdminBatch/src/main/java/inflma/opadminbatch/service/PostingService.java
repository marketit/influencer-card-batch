package inflma.opadminbatch.service;

import inflma.opadminbatch.dao.PostingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostingService {

    List<Long> findByPostedComplete();
}
