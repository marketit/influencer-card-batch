package batch.instagramBatch;

import batch.domain.PlatformAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InstagramWriter implements ItemWriter<PlatformAccount> {

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void write(List<? extends PlatformAccount> items) {
        System.out.println(items.size());
    }
}
