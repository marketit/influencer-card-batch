package inflma.opAdmin.dao;

import inflma.opAdmin.dto.TransactionRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TransactionRequestMapper {
    List<HashMap<String, Object>> transactionRequestExcel(HashMap<String, Object> param);

    void completeTransactionRequest(HashMap<String, Object> param);

    void refusalTransactionRequest(java.util.HashMap<java.lang.String,java.lang.Object> param);

    List<TransactionRequestDto> transactionRequestWithdrawalList(TransactionRequestDto transactionRequestDto);

    int transactionRequestWithdrawalCount(TransactionRequestDto transactionRequestDto);

    List<HashMap<String, Object>> transactionRequestMonth(HashMap<String, Object> param);
}
