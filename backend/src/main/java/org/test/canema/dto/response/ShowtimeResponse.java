package org.test.canema.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShowtimeResponse(Long ShowtimeId, LocalDateTime startTime, BigDecimal price,String hallName) {
}
