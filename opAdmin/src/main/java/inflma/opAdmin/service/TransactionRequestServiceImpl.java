package inflma.opAdmin.service;

import inflma.opAdmin.dao.TransactionRequestMapper;
import inflma.opAdmin.dao.UserMapper;
import inflma.opAdmin.dto.AlramListDto;
import inflma.opAdmin.dto.TransactionRequestDto;
import inflma.opAdmin.dto.TransactionRequestReportDto;
import inflma.opAdmin.result.ResultPage;
import inflma.opAdmin.util.CommonUtil;
import inflma.opAdmin.util.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionRequestServiceImpl {
    private final TransactionRequestMapper transactionRequestMapper;
    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final UserMapper userMapper;

    public List<TransactionRequestDto> transactionRequestExcel(HashMap<String,Object> param){
        return transactionRequestMapper.transactionRequestExcel(param);
    }

    public void refusalTransactionRequest(HashMap<String, Object> param) {
        String pushId = userMapper.findByUserPushId(param);

        transactionRequestMapper.refusalTransactionRequest(param);
    }

    public ResultPage withdrawal(TransactionRequestDto transactionRequestDto) {
        if(transactionRequestDto.getState().equals("2")){
            transactionRequestDto.setState("0,1,-2");
        }

        int page = transactionRequestDto.getPage();
        int pageOffset = (transactionRequestDto.getPage()-1) * transactionRequestDto.getPerPage();

        transactionRequestDto.setPageOffset(pageOffset);
        List<TransactionRequestDto> result = transactionRequestMapper.transactionRequestWithdrawalList(transactionRequestDto);
        int withdrawalListCnt = transactionRequestMapper.transactionRequestWithdrawalCount(transactionRequestDto);

        return CommonUtil.paginate(page,withdrawalListCnt,result);
    }

    public List<TransactionRequestReportDto> transactionRequestMonth(HashMap<String, Object> param) {
        return transactionRequestMapper.transactionRequestMonth(param);
    }

    public int requestComplete(HashMap<String, Object> param) {

        // 완료 처리
        String requestIdStr = (String) param.get("requestId");
        String[] requestIds = requestIdStr.split(",");

        // 로그 쌓아
        for(String requestId : requestIds){
            AlramListDto requestInfo = transactionRequestMapper.findByRequestId(requestId);
            String message = "[환급 완료] 요청하신 환급("+requestInfo.getRequestPrice()+") 처리가 완료되었습니다.";
            requestInfo.setMessage(message);
            transactionRequestMapper.requestLog(requestInfo);
        }
        // push 알림 보내


//        requestIds = requestIds.substring(0, requestIds.length() - 1);
        return transactionRequestMapper.transactionRequestComplete(requestIdStr);
    }
}
