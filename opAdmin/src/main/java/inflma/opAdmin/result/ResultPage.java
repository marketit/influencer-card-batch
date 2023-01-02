package inflma.opAdmin.result;

import inflma.opAdmin.result.page.PageData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultPage {
    private boolean result;

    private PageData data;
}
