package inflma.opAdmin.service;

import inflma.opAdmin.dao.MoneyMapper;
import inflma.opAdmin.dao.UserMapper;
import inflma.opAdmin.util.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyServiceImpl {
    private final MoneyMapper moneyMapper;
    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final UserMapper userMapper;

    public List<HashMap<String,Object>> transactionRequestExcel(HashMap<String,Object> param){
        return moneyMapper.transactionRequestExcel(param);
    }

    public void completeTransactionRequest(HashMap<String,Object> param) {
        String pushId = userMapper.findByUserPushId(param);

        moneyMapper.completeTransactionRequest(param);
    }

    public void refusalTransactionRequest(HashMap<String, Object> param) throws IOException {
        String pushId = userMapper.findByUserPushId(param);

        moneyMapper.refusalTransactionRequest(param);
    }
}
