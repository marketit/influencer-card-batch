package batch.repository;

import batch.domain.PlatformAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlatformAccountMapper {
    List<PlatformAccount> getPlatformAccounts();
}
