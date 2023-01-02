package inflma.opAdmin.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransactionRequestDto extends BaseInformation{
    private Long request_id;
    private String name;
    private int actual_refund;
    private String account;
    private String state;
}
