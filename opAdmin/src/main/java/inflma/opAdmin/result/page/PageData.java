package inflma.opAdmin.result.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageData {
    private List<?> contents;

    private Pagination pagination;
}
