package az.ibrahimshirinov.jdbctemplatesample.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private UUID uuid;
    private Integer code;
    private String message;
    private LocalDateTime timestamp;
    private List<FieldError> fieldErrors = new ArrayList<>();

    public ErrorResponse(UUID uuid, Integer code, String message, LocalDateTime timestamp) {
        this.uuid = uuid;
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Data
    @AllArgsConstructor
    public static class FieldError {
        String field;
        String message;
    }
}
