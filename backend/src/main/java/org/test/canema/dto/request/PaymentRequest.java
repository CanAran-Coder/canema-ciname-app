package org.test.canema.dto.request;


public record PaymentRequest(String holderName,String cardNumber,String cardMonth,String cardYear,String cardCVV,Long movieId,Long userId,Long price) {




}
