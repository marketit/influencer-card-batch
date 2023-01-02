package inflma.opAdmin.result.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private int page;
    private int totalCount;
}
