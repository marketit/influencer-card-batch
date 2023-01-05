package inflma.opAdmin.dto;

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
    private String created_at;
    private String paid_at;
}
