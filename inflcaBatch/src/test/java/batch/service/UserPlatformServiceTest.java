package batch.service;

import batch.domain.PlatformAccount;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.IGMedia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserPlatformServiceTest {

    @Autowired
    private UserPlatformService platformService;
    @Autowired
    private GraphAPIService graphAPIService;

    @Test
    @DisplayName("액세스토큰으로 GraphAPI 통신하는 테스트")
    void getAccessTokensAndTestWithGraphAPI() throws APIException {
        // given: platformAccount ( 3200번대에 데이터가 token 만료가 안됬음) - 01.25.2023 7:54 기준
        PlatformAccount platformAccount = platformService.getUserPlatformAccounts().get(1200);

        // when: media 리스트 조회
        ArrayList<IGMedia> result = graphAPIService.getMediaWithCaption(platformAccount.getPlatformUid(),platformAccount.getAccessToken());

        // then: empty가 아니면 true
        Assertions.assertFalse(result.isEmpty());
    }
}