package org.test.canema.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
@Data
@Builder

public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String error;
}
