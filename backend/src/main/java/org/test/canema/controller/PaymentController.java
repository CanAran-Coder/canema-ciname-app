package org.test.canema.controller;

import com.iyzipay.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.canema.dto.request.PaymentRequest;
import org.test.canema.dto.response.PaymentResponse;
import org.test.canema.service.impl.PaymentService;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> doPayment(@RequestBody PaymentRequest request){
        Payment payment = paymentService.processPayment(request);

        PaymentResponse paymentResponse = new PaymentResponse(payment.getPaymentId(),payment.getStatus());

        return ResponseEntity.ok(paymentResponse);

    }
}
