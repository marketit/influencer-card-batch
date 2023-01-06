package inflma.opAdmin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransactionRequestExcelDto{
    private String bank;
    private String account;
    private String name;
    private int actualRefund;
    private String message;
    private Long requestId;
}
