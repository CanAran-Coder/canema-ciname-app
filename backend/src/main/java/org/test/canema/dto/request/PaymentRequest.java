package org.test.canema.dto.request;


import jakarta.validation.constraints.*;
import org.test.canema.entity.Seat;

import java.util.List;

public record PaymentRequest(
        @NotBlank(message = "Holder Name Cannot Pass Empty!")  String holderName,
        @NotBlank(message = "Card Number Cannot Pass Empty!") @Size(min = 16 , max = 16) String cardNumber,
        @NotBlank(message = "Month Cannot Pass Empty!") @Size(min = 2 ,max = 2) String cardMonth,
        @NotBlank(message = "Year Cannot Pass Empty!") @Size(min = 4,max = 4) String cardYear,
        @NotBlank(message = "Security Code Cannot Pass Empty!") @Size(min = 3,max = 3) String cardCVV,
        @NotBlank(message = "Email Cannot Pass Empty!") @Email String userMail,
        @NotBlank(message = "Hall Name Cannot Pass Empty!")String hallName,
        @NotNull(message = "SeatList Cannot Pass Null!") @NotEmpty(message = "Seatlist Cannot Pass Empty!") List<@NotBlank(message = "Seats Cannot Pass Empty!") String> seatNumbers,
        @NotNull(message = "ShowtimeId Cannot Pass Null!") Long showTimeId ){




}
