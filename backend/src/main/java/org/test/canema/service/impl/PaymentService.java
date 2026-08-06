package org.test.canema.service.impl;

import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.test.canema.dto.request.PaymentRequest;
import org.test.canema.dto.response.PaymentResponse;
import org.test.canema.entity.*;
import org.test.canema.exception.error.ResourceNotFoundException;
import org.test.canema.repository.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final Options iyzipayOptions;
    private final UserRepository userRepository;
    private final HallRepository hallRepository;
    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;
    private final TicketRepository ticketRepository;

   /* public Payment processPayment(PaymentRequest payment) {
        Showtime showtime = showtimeRepository.findById(payment.showTimeId())
                .orElseThrow(() -> new RuntimeException("Gösterim saati (Showtime) bulunamadı. ID: " + payment.showTimeId()));

        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId(String.valueOf(System.currentTimeMillis()));
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(1);
        request.setBasketId("B" + System.currentTimeMillis());
        request.setPaymentChannel(PaymentChannel.WEB.name());
        request.setPaymentGroup(PaymentGroup.PRODUCT.name());


        BigDecimal unitPrice = showtime.getPrice().setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(payment.seatNumbers().size())).setScale(2, RoundingMode.HALF_UP);

        request.setPrice(totalPrice);
        request.setPaidPrice(totalPrice);


        String month = payment.cardMonth() != null ? payment.cardMonth().trim() : "";
        if (month.length() == 1) {
            month = "0" + month;
        }

        String year = payment.cardYear() != null ? payment.cardYear().trim() : "";
        if (year.length() == 2) {
            year = "20" + year;
        }

        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName(payment.holderName());
        paymentCard.setCardNumber(payment.cardNumber() != null ? payment.cardNumber().replaceAll("\\s+", "") : "");
        paymentCard.setExpireMonth(month);
        paymentCard.setExpireYear(year);
        paymentCard.setCvc(payment.cardCVV() != null ? payment.cardCVV().trim() : "");
        paymentCard.setRegisterCard(0);
        request.setPaymentCard(paymentCard);

        // 3. Buyer ve Adres
        Buyer buyer = new Buyer();
        buyer.setId("BY-" + System.currentTimeMillis());
        buyer.setName("Musteri");
        buyer.setSurname("User");
        buyer.setGsmNumber("+905555555555");
        buyer.setEmail((payment.userMail() != null && !payment.userMail().isBlank()) ? payment.userMail() : "bilet@canema.com");
        buyer.setIdentityNumber("11111111111");
        buyer.setRegistrationAddress("Istanbul");
        buyer.setIp("127.0.0.1");
        buyer.setCity("Istanbul");
        buyer.setCountry("Turkey");
        request.setBuyer(buyer);

        Address address = new Address();
        address.setContactName(payment.holderName());
        address.setCity("Istanbul");
        address.setCountry("Turkey");
        address.setAddress("Sinema Salonu");
        request.setShippingAddress(address);
        request.setBillingAddress(address);


        List<BasketItem> basketItems = new ArrayList<>();
        BasketItem item = new BasketItem();
        item.setId("BI-" + showtime.getId() + "-" + System.currentTimeMillis());
        item.setName("Sinema Bileti x" + payment.seatNumbers().size());
        item.setCategory1("Sinema");
        item.setItemType(BasketItemType.VIRTUAL.name());
        item.setPrice(totalPrice);
        basketItems.add(item);
        request.setBasketItems(basketItems);


        Payment response = Payment.create(request, iyzipayOptions);


        System.out.println("\n----------------- IYZICO YANIT DETAYI -----------------");
        System.out.println("Status       : " + response.getStatus());
        System.out.println("ErrorCode    : " + response.getErrorCode());
        System.out.println("ErrorMessage : " + response.getErrorMessage());
        System.out.println("ErrorGroup   : " + response.getErrorGroup());
        System.out.println("-------------------------------------------------------\n");


        if (!Status.SUCCESS.getValue().equals(response.getStatus())) {
            log.error("İyzipay Ödeme Hatası: KOD[{}] - MESAJ[{}]", response.getErrorCode(), response.getErrorMessage());
            throw new RuntimeException("Ödeme başarısız: " + response.getErrorMessage());
        }


        Optional<User> user = userRepository.findByEmail(payment.userMail());
        Hall hall = hallRepository.findHallByName(payment.hallName());

        if (hall == null) {
            throw new RuntimeException("Salon bulunamadı: " + payment.hallName());
        }

        List<Seat> seats = seatRepository.findAllByHallIdAndSeatNumberIn(hall.getId(), payment.seatNumbers());

        for (Seat seat : seats) {
            Ticket ticket = new Ticket();
            ticket.setCreatedAt(LocalDateTime.now());
            ticket.setPaymentId(response.getPaymentId());
            ticket.setPrice(unitPrice);
            user.ifPresent(ticket::setUser);
            ticket.setSeat(seat);
            ticket.setShowtime(showtime);

            ticketRepository.save(ticket);
        }

        return response;
    }

   */

    public PaymentResponse normalPayment(PaymentRequest request) {
        List<String> seats = request.seatNumbers();
        Optional<Showtime> showtime = showtimeRepository.findById(request.showTimeId());
        Optional<User> user = userRepository.findByEmail(request.userMail());
        PaymentResponse paymentResponse;

        for (String item : seats) {
            Optional<Seat> seat = seatRepository.findBySeatNumberAndHallId(Integer.valueOf(item),showtime.get().getHall().getId());

            Ticket ticket = new Ticket();
            if (user.isPresent()) {
                ticket.setUser(user.get());
            } else {
                throw new ResourceNotFoundException("User Not Found!");

            }
            ticket.setPaymentId(String.valueOf(LocalDateTime.now()));
            if (showtime.isPresent()){
                ticket.setPrice(showtime.get().getPrice());
                ticket.setShowtime(showtime.get());
            }else{
                throw new  ResourceNotFoundException("Showtime not found!");
            }


            if (seat.isPresent()) {
                ticket.setSeat(seat.get());
            }
            else{
                throw new ResourceNotFoundException("Seat Not Found!");
            }
            var response = ticketRepository.save(ticket);
            log.info("Ticket Added Successfully:{}", ticket.getPaymentId());


        }
        paymentResponse = new PaymentResponse(String.valueOf(user.get().getId()),true);
        return paymentResponse;
    }
}