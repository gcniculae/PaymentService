package com.payment.restcontroller;

import com.payment.converter.PaymentConverter;
import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {

    private final PaymentService paymentService;
    private final PaymentConverter paymentConverter;

    @Autowired
    public PaymentRestController(PaymentService paymentService, PaymentConverter paymentConverter) {
        this.paymentService = paymentService;
        this.paymentConverter = paymentConverter;
    }
}
