package org.test.canema.service.impl;

import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.test.canema.dto.request.PaymentRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final Options iyzipayOptions;


    public Payment processPayment(PaymentRequest payment) {

        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId(String.valueOf(System.currentTimeMillis()));
        request.setPrice(BigDecimal.valueOf(payment.price()));
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(1);
        request.setBasketId("B" + System.currentTimeMillis());
        request.setPaymentChannel(PaymentChannel.WEB.name());
        request.setPaymentGroup(PaymentGroup.PRODUCT.name());

        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName(payment.holderName());
        paymentCard.setCardNumber(payment.cardNumber().replaceAll("\\s+", ""));
        paymentCard.setExpireMonth(payment.cardMonth());
        paymentCard.setExpireYear(payment.cardYear());
        paymentCard.setCvc(payment.cardCVV());
        paymentCard.setRegisterCard(0);

        request.setPaymentCard(paymentCard);


        Buyer buyer = new Buyer();
        buyer.setId("BY-" + System.currentTimeMillis());
        buyer.setName("Musteri");
        buyer.setSurname("User");
        buyer.setGsmNumber("+905555555555");
        buyer.setEmail("bilet@sinema.com");
        buyer.setIdentityNumber("11111111111");
        buyer.setRegistrationAddress("İstanbul");
        buyer.setIp("127.0.0.1");
        buyer.setCity("Istanbul");
        buyer.setCountry("Turkey");
        request.setBuyer(buyer);


        Address address = new Address();
        address.setContactName("Musteri User");
        address.setCity("Istanbul");
        address.setCountry("Turkey");
        address.setAddress("Sinema Salonu");
        request.setShippingAddress(address);
        request.setBillingAddress(address);

        List<BasketItem> basketItems = new ArrayList<>();
        BasketItem item = new BasketItem();
        item.setId("BI-01");
        item.setName("Sinema Bileti");
        item.setCategory1("Sinema");
        item.setItemType(BasketItemType.VIRTUAL.name());
        item.setPrice(BigDecimal.valueOf(payment.price()));
        basketItems.add(item);
        request.setBasketItems(basketItems);
        return Payment.create(request, iyzipayOptions);

    }

}
