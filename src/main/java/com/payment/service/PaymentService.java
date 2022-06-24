package com.payment.service;

import com.payment.dto.PaymentDto;
import com.payment.entity.Payment;
import com.payment.exception.NotFoundException;
import com.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ClientService clientService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, ClientService clientService) {
        this.paymentRepository = paymentRepository;
        this.clientService = clientService;
    }

    public Payment savePayment(Payment payment, Long clientId) {
        payment.setClient(clientService.findClientById(clientId));

        return paymentRepository.save(payment);
    }

    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> findPaymentsById(List<Long> ids) {
        return ids.stream()
                .map(this::findPaymentById)
                .collect(Collectors.toList());
    }

    public List<Payment> findPaymentsByClientFirstName(String firstName) {
        return paymentRepository.findByClientFirstName(firstName);
    }

    public List<Payment> findPaymentsByClientLastName(String lastName) {
        return paymentRepository.findByClientLastName(lastName);
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

    public Payment updatePayment(Long id, Payment payment, Long clientId) {
        Payment paymentById = findPaymentById(id);
        payment.setId(paymentById.getId());

        return savePayment(payment, clientId);
    }

    public void deletePaymentById(Long id) {
        Payment paymentById = findPaymentById(id);

        paymentRepository.deleteById(paymentById.getId());
    }
}
