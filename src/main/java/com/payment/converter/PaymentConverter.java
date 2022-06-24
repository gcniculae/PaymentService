package com.payment.converter;

import com.payment.dto.PaymentDto;
import com.payment.entity.Payment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter extends BaseConverter<Payment, PaymentDto> {

    @Override
    public PaymentDto convertEntityToDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        BeanUtils.copyProperties(payment, paymentDto);

        return paymentDto;
    }

    @Override
    public Payment convertDtoToEntity(PaymentDto paymentDto) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDto, payment);

        return payment;
    }
}
