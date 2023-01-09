package inflma.opAdmin.service;

import inflma.opAdmin.dao.TransactionRequestMapper;
import inflma.opAdmin.dto.AlramListDto;
import inflma.opAdmin.dto.TransactionRequestDto;
import inflma.opAdmin.dto.TransactionRequestReportDto;
import inflma.opAdmin.result.ResultPage;
import inflma.opAdmin.util.CommonUtil;
import inflma.opAdmin.util.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionRequestServiceImpl {
    private final TransactionRequestMapper transactionRequestMapper;
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    public List<TransactionRequestDto> transactionRequestExcel(HashMap<String,Object> param){
        return transactionRequestMapper.transactionRequestExcel(param);
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

    @Transactional(rollbackFor = IOException.class)
    public int requestComplete(HashMap<String, Object> param) {

        // param 문자열 처리
        String requestIdStr = (String) param.get("requestId");
        requestIdStr = requestIdStr.substring(0, requestIdStr.length() - 1);
        String[] requestIds = requestIdStr.split(",");

        for(String requestId : requestIds){
            AlramListDto requestInfo = transactionRequestMapper.findByRequestId(requestId);
            requestInfoNullCheck(requestInfo);
        }
        // 완료 처리
        return transactionRequestMapper.transactionRequestComplete(requestIdStr);
    }

    private void requestInfoNullCheck(AlramListDto requestInfo) {
        if(requestInfo != null){
            String message = "[환급 완료] 요청하신 환급("+requestInfo.getRequestPrice()+") 처리가 완료되었습니다.";
            requestInfo.setMessage(message);
            // 로그 쌓기
            transactionRequestMapper.requestLog(requestInfo);

            // push 알림 보내기
            pushMessage(requestInfo.getPushId(),"환급완료 알림",message);
        }
    }



    public void refusalTransactionRequest(HashMap<String, Object> param) {
    }

    private void pushMessage(String targetToken, String title, String message) {
        try{
            firebaseCloudMessageService.sendMessageTo(
                    targetToken,
                    title,
                    message
            );
        }catch(IOException e){
            e.getMessage();
        }
    }
}
