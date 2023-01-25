package batch.service;

import batch.domain.PlatformAccount;
import batch.repository.PlatformAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPlatformService {
    private final PlatformAccountMapper platformAccountMapper;

    public List<PlatformAccount>  getUserPlatformAccounts(){
        return platformAccountMapper.getPlatformAccounts();
    }
}
