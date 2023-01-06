package inflma.opAdmin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransactionRequestDto extends BaseInformation{
    private Long requestId;
    private String name;
    private int actualRefund;
    private String account;
    private Long userId;
    private String state;
    private String createdAt;
    private String paidAt;


    public void setState(String state) {
        this.state = state;
    }
}
