package inflma.opAdmin.service;

import inflma.opAdmin.dao.TransactionRequestMapper;
import inflma.opAdmin.dao.UserMapper;
import inflma.opAdmin.dto.TransactionRequestDto;
import inflma.opAdmin.result.ResultPage;
import inflma.opAdmin.util.CommonUtil;
import inflma.opAdmin.util.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionRequestServiceImpl {
    private final TransactionRequestMapper transactionRequestMapper;
    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final UserMapper userMapper;

    public List<HashMap<String,Object>> transactionRequestExcel(HashMap<String,Object> param){
        return transactionRequestMapper.transactionRequestExcel(param);
    }

    public void completeTransactionRequest(HashMap<String,Object> param) {
        String pushId = userMapper.findByUserPushId(param);

        transactionRequestMapper.completeTransactionRequest(param);
    }

    public void refusalTransactionRequest(HashMap<String, Object> param) throws IOException {
        String pushId = userMapper.findByUserPushId(param);

        transactionRequestMapper.refusalTransactionRequest(param);
    }

    public ResultPage withdrawal(TransactionRequestDto transactionRequestDto) {
        int page = transactionRequestDto.getPage();

        int pageOffset = (transactionRequestDto.getPage()-1) * transactionRequestDto.getPerPage();
        transactionRequestDto.setPageOffset(pageOffset);
        List<TransactionRequestDto> result = transactionRequestMapper.transactionRequestWithdrawalList(transactionRequestDto);
        int withdrawalListCnt = transactionRequestMapper.transactionRequestWithdrawalCount(transactionRequestDto);

        return CommonUtil.paginate(page,withdrawalListCnt,result);
    }
}
