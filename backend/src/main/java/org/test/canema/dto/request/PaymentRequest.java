package org.test.canema.dto.request;


import org.test.canema.entity.Seat;

import java.util.List;

public record PaymentRequest(String holderName, String cardNumber, String cardMonth, String cardYear, String cardCVV, String userMail, String hallName,
                             List<String> seatNumbers,Long showTimeId ){




}
