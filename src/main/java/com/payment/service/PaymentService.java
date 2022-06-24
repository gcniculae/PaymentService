package com.payment.service;

import com.payment.entity.Payment;
import com.payment.exception.NotFoundException;
import com.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment findPaymentById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if (optionalPayment.isPresent()) {
            return optionalPayment.get();
        } else {
            throw new NotFoundException("No such payment found.", "payment.not.found");
        }
    }

    public Payment findPaymentByClientId(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findByClientId(id);

        if (optionalPayment.isPresent()) {
            return optionalPayment.get();
        } else {
            throw new NotFoundException("No such payment found.", "payment.not.found");
        }
    }

    public List<Payment> findPaymentsByClientFirstName(String firstName) {
        return paymentRepository.findByClientFirstName(firstName);
    }

    public List<Payment> findPaymentsByClientLastName(String lastName) {
        return paymentRepository.findByClientLastName(lastName);
    }

    public Payment updatePayment(Long id, Payment payment) {
        Payment paymentById = findPaymentById(id);
        payment.setId(paymentById.getId());

        return savePayment(payment);
    }

    public void deletePaymentById(Long id) {
        Payment paymentById = findPaymentById(id);

        paymentRepository.deleteById(paymentById.getId());
    }

    public void deletePaymentByClientId(Long id) {
        Payment paymentByClientId = findPaymentByClientId(id);

        paymentRepository.deleteByClientId(paymentByClientId.getClient().getId());
    }
}
