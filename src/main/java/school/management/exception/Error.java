package school.management.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    private Integer status;
    private Long timestamp;
    private String details;
    private List<String> errors;
}
