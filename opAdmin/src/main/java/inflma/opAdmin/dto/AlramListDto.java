package inflma.opAdmin.dto;

import lombok.Data;

@Data
public class AlramListDto {
    private int requestPrice;
    private Long userId;
    private String message;
    private String pushId;
}
