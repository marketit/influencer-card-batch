package batch.service;

import com.facebook.ads.sdk.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class GraphAPIService {
    public ArrayList<IGMedia> getMediaWithCaption(String platformUid, String accessToken) throws APIException {
        return new ArrayList<>(new IGUser(platformUid, getContext(accessToken))
            .getMedia()
            .requestField("caption")
            .execute());
    }

    public List<String> getInstagramIdByAccessToken(String accessToken) {

        List<String> instagramIds = new ArrayList<>();
        APINodeList<Page> pages;
        try {
            pages = new User("me", getContext(accessToken))
                .getAccounts()
                .requestField("instagram_business_account,id")
                .execute();
            for (Page page : pages) {
                if (!Objects.isNull(page.getFieldInstagramBusinessAccount())) {
                    String instagramId = page.getFieldInstagramBusinessAccount().getId();
                    instagramIds.add(instagramId);
                }
            }
        } catch (APIException ignored) {

        }

        return instagramIds;
    }

    public IGMedia getMediaData(String mediaId, String accessToken) throws APIException {
        return IGMedia
            .fetchById(mediaId, getContext(accessToken));
    }


    private APIContext getContext(String accessToken) {
        return new APIContext(accessToken);
    }

}
